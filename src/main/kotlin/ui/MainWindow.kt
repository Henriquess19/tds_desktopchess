package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.BoardDB

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

   fun openGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).open(mongoRepository,Team.WHITE,id)
   }

   fun joinGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).open(mongoRepository,Team.BLACK,id)
   }

   fun refresh(){
      gameState.value = (currentGameState as GameStarted).refresh()
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
         onPossibleMove = {move-> gameState.value = currentGameState.makeMove(move)},
         composingResultValue = {result -> gameResult.value = ValueResult(result) },
         onPromotionNeeded= {move ->  promotionNeeded.value= move},
         onGameEnded={team -> gameTeamEnded.value = team}
         )
   }

   if(currentGameTeamEnded != null){
      endGameDialog(
         team = currentGameTeamEnded,
         onClose = {gameTeamEnded.value = null; gameState.value = GameNotStarted }
      )
   }


   if(currentPromotionNeeded != null){
      promotionDialog(
         movement = currentPromotionNeeded,
         onPieceGiven = {move-> promotionNeeded.value = null; gameState.value = (currentGameState as GameStarted).promotionMove(move)},
         onClose = {promotionNeeded.value = currentPromotionNeeded}
      )
   }

   if(currentgameResult != null){
      resultDialog() { gameResult.value = null; gameState.value = (currentGameState as GameStarted).updateVerity() }
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

   if (board.second.result != ValidMovement) {
      if (board.second.result == NeedPromotion){
         onPromotionNeeded(move.value)
      }else if (board.second.result == EndedGame) {
            onGameEnded(board.first.movesList.content.last().team)
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

