package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.BoardDB
import model.storage.MongoDbChess
import org.litote.kmongo.MongoOperator

private typealias GameAction = (GameId) -> Unit

@Composable
fun MainWindow(mongoRepository: BoardDB, onCloseRequest:() -> Unit) = Window(
   title= "Chess Dos Suspeitos",
   icon= painterResource(chooseImage(team = Team.BLACK ,piece = Piece(Team.BLACK, TypeOfPieces.K,'k'))),
   state= WindowState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   onCloseRequest= onCloseRequest,
   resizable = true
){
   val gameState = remember{mutableStateOf<Game>(GameNotStarted)}
   val currentGameState = gameState.value

   val gameAction = remember{mutableStateOf<GameAction?>(null)}
   val curentGameAction = gameAction.value

   fun openGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).open(mongoRepository,Team.WHITE,id)
   }

   fun joinGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).join(mongoRepository,Team.BLACK,id)
   }

   fun refresh(){
      gameState.value = (currentGameState as GameStarted).refresh()
   }

   /* TODO(VER ONDE POR) */
   fun endGame(){
      endGameDialog(
         onClose = {/*gameState.value = GameNotStarted*/}
      )
   }

   MainWindowMenu(currentGameState,
      onOpenRequest = {gameAction.value = ::openGame},
      onJoinRequest = {gameAction.value = ::joinGame},
      onRefreshRequested = {refresh()}
   )

   when(currentGameState){
      is GameNotStarted -> GameNotStartedContent()
      is GameStarted -> GameStartedContent(currentGameState,mongoRepository)
   }

   if(curentGameAction != null){
      getGameID(
         onGameIdGiven = {curentGameAction.invoke(it); gameAction.value = null},
         onClose = { gameAction.value = null }
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
private fun GameStartedContent(currentGame:GameStarted,mongoRepository: BoardDB){
   val board = remember { mutableStateOf(currentGame.board)}
   val movement = remember { mutableStateOf(Move("none")) }
   val team = remember { mutableStateOf(Team.WHITE) }

   Column {
      Row {
         BoardView(
            board = board.value.first,
            onTileSelected = { piece: Piece?, position: Position ->
               if (currentGame.localTurn == board.value.first.turn || currentGame.localTurn != board.value.first.turn) {
                  val moves = getmovement(piece, position)
                  if (moves != null) {
                     val move = moves.split(',')[1]
                     val id = currentGame.board.first.id.toGameIdOrNull()
                     if (id != null) {
                        movement.value = Move(move)
                        board.value = GameStarted(
                           repository = mongoRepository,
                           id = id,
                           localTurn = team.value,
                           board = board.value
                        ).makeMove(move).board
                     }
                  }
                  if (board.value.second.result == EndedGame){
                     TODO()
                  }
               }
            }
         )
         movesView(board = board.value.first)
         if (board.value.second.result != ValidMovement) board.value =
            composingResults(board = board.value, team = team.value, movement = movement.value)
      }
   }
}

