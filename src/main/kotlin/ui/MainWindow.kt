package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.BoardDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.board.movesView

private typealias GameAction = (GameId) -> Unit

data class Changers(val result: ValueResult<*>? =null,val promotion: String?=null,val teamWinner:Team?=null)

@Composable
fun MainWindow(mongoRepository: BoardDB, onCloseRequest:() -> Unit) = Window(
   title= "Chess Dos Suspeitos",
   icon= painterResource(chooseImage(team = Team.BLACK ,piece = Piece(Team.BLACK, TypeOfPieces.K,'k'))),
   state= WindowState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   onCloseRequest= onCloseRequest,
   resizable = false
){
   val gameState = remember{mutableStateOf<Game>(GameNotStarted)}
   val currentGameState = gameState.value

   val gameAction = remember{mutableStateOf<GameAction?>(null)}
   val curentGameAction = gameAction.value

   val gameChangers= remember{mutableStateOf<Changers?>(null)}
   val currentgameChangers = gameChangers.value

   val gameTheme = remember{mutableStateOf<Theme>(Theme.CLASSIC)}
   val currentTheme = gameTheme.value

   val coroutineScope = rememberCoroutineScope()

   LaunchedEffect(currentGameState){
      while(true){
         if(currentGameState is GameStarted && !currentGameState.isLocalPlayerTurn()){
            gameState.value = (currentGameState as GameStarted).refresh()
         }
         delay(5_000)
      }
   }

   fun openGame(id:GameId){
      coroutineScope.launch {
         gameState.value = (currentGameState as GameNotStarted).open(mongoRepository, Team.WHITE, id)
      }
   }

   fun joinGame(id:GameId){
      coroutineScope.launch {
         gameState.value = (currentGameState as GameNotStarted).open(mongoRepository, Team.BLACK, id)
      }
   }

   fun refresh(){
      coroutineScope.launch {
         gameState.value = (currentGameState as GameStarted).refresh()
      }
   }

   MainWindowMenu(currentGameState,
      onOpenRequest = {gameAction.value = ::openGame},
      onJoinRequest = {gameAction.value = ::joinGame},
      onRefreshRequested = {refresh()},
      onThemeUpdateRequested = {gameTheme.value = it}
   )

   when(currentGameState){
      is GameNotStarted -> GameNotStartedContent(
         currentTheme
      )
      is GameStarted -> GameStartedContent(
         currentGameState,
         currentTheme,
         onPossibleMove = {move-> coroutineScope.launch { gameState.value = currentGameState.makeMove(move)}},
         composingResultValue = {result -> coroutineScope.launch { gameChangers.value = Changers(result= ValueResult(result)) }},
         onPromotionNeeded= {move -> coroutineScope.launch { gameChangers.value = Changers(promotion =  move)}},
         onGameEnded={team -> coroutineScope.launch {gameChangers.value = Changers(teamWinner =  team)}}
         )
   }

   if(currentgameChangers != null){

      if(currentgameChangers.promotion != null) {
         promotionDialog(
            movement = currentgameChangers.promotion,
            onPieceGiven = {move-> coroutineScope.launch { gameState.value = (currentGameState as GameStarted).promotionMove(move)}; gameChangers.value = null},
            onClose = {gameChangers.value = Changers(promotion =currentgameChangers.promotion)}
         )
      }

      if(currentgameChangers.result != null){
         resultDialog() { gameChangers.value = null; coroutineScope.launch { gameState.value = (currentGameState as GameStarted).updateVerity()}}
      }

      if(currentgameChangers.teamWinner != null){
         endGameDialog(
            team = currentgameChangers.teamWinner,
            onClose = {gameChangers.value = null; gameState.value = GameNotStarted })
      }
   }

   if(curentGameAction != null){
      getGameID(
         onGameIdGiven = {curentGameAction.invoke(it); gameAction.value = null},
         onClose = { gameAction.value = null}
      )
   }
}


@Composable
private fun FrameWindowScope.MainWindowMenu(
   state: Game,
   onOpenRequest:() -> Unit,
   onJoinRequest:() -> Unit,
   onRefreshRequested:(GameStarted) -> Unit,
   onThemeUpdateRequested:(Theme)-> Unit
) = MenuBar{

   data class MenuState(val open:Boolean,val join:Boolean,val refresh:Boolean)
   val menuState = MenuState(
      open = state is GameNotStarted,
      join = state is GameNotStarted,
      refresh = state is GameStarted
   )

   Menu("Game"){
      Item("Open", enabled= menuState.open, onClick = onOpenRequest)
      Item("Join", enabled= menuState.join, onClick = onJoinRequest)
   }
   Menu("Options"){
      Item("Refresh", enabled= menuState.refresh, onClick = {onRefreshRequested(state as GameStarted)})
      Menu("Themes"){
         Item("Classic", enabled=true, onClick = { onThemeUpdateRequested(Theme.CLASSIC)})
         Item("Classic Inverted", enabled=true, onClick = {onThemeUpdateRequested(Theme.INVERSECLASSIC)})
         Item("Gray", enabled=true, onClick = {onThemeUpdateRequested(Theme.GRAY)})
         Item("Psycho", enabled=true, onClick = {onThemeUpdateRequested(Theme.PSYCHO)})
         Item("Mono", enabled=true, onClick = {onThemeUpdateRequested(Theme.MONO)})
         Item("Ice", enabled=true, onClick = {onThemeUpdateRequested(Theme.ICE)})
         Item("Fire", enabled=true, onClick = {onThemeUpdateRequested(Theme.FIRE)})
         Item("Hardcore", enabled=true, onClick = {onThemeUpdateRequested(Theme.HARDCORE)})
      }
   }
}

@Composable
private fun GameNotStartedContent(
   currentTheme: Theme
){
   Column{
      Row {
         BoardView(BoardState(),currentTheme, onTileSelected = {_,_ -> })
         movesView(BoardState())
      }
   }
}


@Composable
private fun GameStartedContent(
   currentGame:GameStarted,
   currentTheme:Theme,
   onPossibleMove: (move:String) -> Unit,
   composingResultValue: (result: ValueResult<*>) -> Unit,
   onPromotionNeeded:(move:String) ->Unit,
   onGameEnded:(team:Team) -> Unit){

   val board = currentGame.board
   val move = remember{mutableStateOf<String>("")}

   if (board.third) onGameEnded(board.first.movesList.content.last().team) /** TODO(Verify bug, just have ended when black tries to play) **/

   val possibleMovement = { piece: Piece?, position: Position ->
      if (currentGame.isLocalPlayerTurn()) {
         val moves = getmovement(piece, position)
         if (moves != null) {
            val mover = moves.split(',')[1]
            val id = board.first.id.toGameIdOrNull()
            if (id != null) {
               move.value = mover
               onPossibleMove(move.value)
            }
         }
      }
   }

   if (!board.second.result.equals(OpenedGame) && !board.second.result.equals(ValidMovement) && !board.second.result.equals(EndedGame)) {
      println(board.second.result)
      if (board.second.result == NeedPromotion){
         onPromotionNeeded(move.value)
      }else{
         composingResultValue(ValueResult(board.second.result))
      }
   }

   Column {
      Row {
         BoardView(board = board.first,onTileSelected = possibleMovement,theme= currentTheme)
         movesView(board = board.first)
      }
   }
}

