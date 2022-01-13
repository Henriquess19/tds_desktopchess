package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.BoardDB
import kotlinx.coroutines.delay
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

private typealias GameAction = (GameId) -> Unit

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

   val gameResult = remember{mutableStateOf<ValueResult<*>?>(null)}
   val currentgameResult = gameResult.value

   val promotionNeeded = remember{mutableStateOf<String?>(null)}
   val currentPromotionNeeded = promotionNeeded.value

   val gameTeamEnded = remember{mutableStateOf<Team?>(null)}
   val currentGameTeamEnded = gameTeamEnded.value

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

   /* TODO(VER ONDE POR) */
   /*fun endGame(){
      endGameDialog(
         onClose = {/*gameState.value = GameNotStarted*/}
      )
   }*/


   MainWindowMenu(currentGameState,
      onOpenRequest = {gameAction.value = ::openGame},
      onJoinRequest = {gameAction.value = ::joinGame},
      onRefreshRequested = {refresh()}
   )

   when(currentGameState){
      is GameNotStarted -> GameNotStartedContent()
      is GameStarted -> GameStartedContent(
         currentGameState,
         onPossibleMove = {move-> coroutineScope.launch { gameState.value = currentGameState.makeMove(move)}},
         composingResultValue = {result -> coroutineScope.launch { gameResult.value = ValueResult(result) }},
         onPromotionNeeded= {move -> coroutineScope.launch { promotionNeeded.value= move}},
         onGameEnded={team -> coroutineScope.launch {gameTeamEnded.value = team}}
         )
   }

   if(currentGameTeamEnded != null){
      println("acabou")
      endGameDialog(
         team = currentGameTeamEnded,
         onClose = {gameTeamEnded.value = null; gameState.value = GameNotStarted }
      )
   }


   if(currentPromotionNeeded != null){
      promotionDialog(
         movement = currentPromotionNeeded,
         onPieceGiven = {move-> promotionNeeded.value = null; coroutineScope.launch { gameState.value = (currentGameState as GameStarted).promotionMove(move)}},
         onClose = {promotionNeeded.value = currentPromotionNeeded}
      )
   }

   if(currentgameResult != null){
      resultDialog() { gameResult.value = null; coroutineScope.launch { gameState.value = (currentGameState as GameStarted).updateVerity()}}
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
   onRefreshRequested:(GameStarted) -> Unit
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
   }
}

@Composable
private fun GameNotStartedContent(){
   Column{
      Row {
         BoardView(BoardState(), onTileSelected = {_,_ -> })
         movesView(BoardState())
      }
   }
}


@Composable
private fun GameStartedContent(
   currentGame:GameStarted,
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
         BoardView(board = board.first,onTileSelected = possibleMovement)
         movesView(board = board.first)
      }
   }
}

