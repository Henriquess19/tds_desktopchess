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
import ui.dialogs.endGameDialog

private typealias GameAction = (GameId) -> Unit

/**
 * Values that define some changes in the game
 */
data class Changers(val result: ValueResult<*>? =null,val promotion: String?=null,val teamWinner:Team?=null)

/**
 * Composable that defines the application's main window. The application state is represented by the
 * [mongoRepository].
 *
 * @param mongoRepository  The games' repository
 * @param onCloseRequest The function to be called when the user intends to close the window
 */
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

   val gameReviewIndex = remember{mutableStateOf<Int>(0)}
   val currentReviewIndex = gameReviewIndex.value

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
         gameState.value = (currentGameState as GameNotStarted).join(mongoRepository, Team.BLACK, id)
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

   /** The diferent options for the gameStates **/
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
      is GameEnded -> {
         reviewDialog(
            onPlayIndexGiven = { gameReviewIndex.value = it },
            onClose = { gameState.value = GameNotStarted }
         )
         GameEndedContent(
            currentGameState,
            currentReviewIndex,
            currentTheme
         )
      }
   }

   /** GameChangers Application (Promotion,InvalidMovements & EndingGame) **/
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
            onReview = {gameChangers.value = null;gameState.value = GameEnded},
            onClose = {gameChangers.value = null;gameState.value = GameNotStarted})
      }
   }

   /** Getting the dialog for obtain the gameId **/
   if(curentGameAction != null){
      getGameID(
         onGameIdGiven = {curentGameAction.invoke(it); gameAction.value = null},
         onClose = { gameAction.value = null}
      )
   }
}

/**
 * The [MainWindow] menu
 */
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

/**
 * The @composable for when the game is not started
 */
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

/**
 * The @composable for when the game is being played
 */
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

   if (board.second.result != OpenedGame && board.second.result != ValidMovement && board.second.result != EndedGame) {
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

/**
 * The @composable for when the game has ended
 */
@Composable
private fun GameEndedContent(
   currentGame: GameEnded,
   currentIndex:Int,
   currentTheme: Theme,
){
   val board = currentGame.getBoardPlay(currentIndex)
   BoardView(board,currentTheme,onTileSelected = { _, _ -> })
}
