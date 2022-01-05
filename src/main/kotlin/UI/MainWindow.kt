package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import model.domain.*
import model.storage.BoardDB
import model.storage.MongoDbChess

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

   fun openGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).start(mongoRepository,Team.WHITE,id)
   }

   fun joinGame(id:GameId){
      gameState.value = (currentGameState as GameNotStarted).start(mongoRepository,Team.BLACK,id)
   }

   MainWindowMenu(currentGameState,
      onOpenRequest = {gameAction.value = ::openGame},
      onJoinRequest = {gameAction.value = ::joinGame},
   )

   when(currentGameState){
      is GameNotStarted -> GameNotStartedContent()
      is GameStarted -> GameStartedContent()
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
) = MenuBar{

   data class MenuState(val open:Boolean,val join:Boolean)
   val menuState = MenuState(
      open = state is GameNotStarted,
      join = state is GameNotStarted
   )

   Menu("Game"){
      Item("Open", enabled= menuState.open, onClick = onOpenRequest)
      Item("Join", enabled= menuState.join, onClick = onJoinRequest)
   }
}

@Composable
private fun GameNotStartedContent() = BoardView(BoardState(), onTileSelected = {_,_ -> })

@Composable
private fun GameStartedContent(){
   val board = remember {
      mutableStateOf(
         Pair(
            BoardState(),
            MoveVerity(mutableListOf(), ValidMovement)
         )
      )
   }
   val movement = remember { mutableStateOf(Move("dummy")) }
   val team = remember { mutableStateOf(Team.WHITE) }


   Row {
      BoardView(
         board = board.value.first,
         onTileSelected = { piece: Piece?, position: Positions ->
            TODO()
            val move = getmovement(piece, position)
            //GameStarted.makeMove(move)
         }
      )
      movesView(board = board.value.first)
      if(board.value.second.result != ValidMovement) board.value = composingResults(board =  board.value, team = team.value, movement = movement.value )
   }
}

/** ----------------------------------------------------------------------------**/
/* TODO("VERIFY WHERE TO PUT") */
@Composable
fun endgame(team:Team){
   Box {
      val popupWidth = 200.dp
      val popupHeight = 50.dp
      val cornerSize = 16.dp

      Popup() {
         Box(
            Modifier
               .size(popupWidth, popupHeight)
               .background(Color.White, RoundedCornerShape(cornerSize))
         )
         Text("$team WON!!!", modifier= Modifier.padding(12.dp))
      }
   }
}



@Composable
fun chooseGame(mongoDB: MongoDbChess, type:String) {
   Column {
      val text = remember { mutableStateOf("-1") }

      TextField(
         value = text.value,
         onValueChange = { text.value = it },
         label = { Text("GameID") }
      )

      if(text.value != "-1"){
         //openGameView(mongoDB,text.value,onSelected = { println(it)})
      }
   }
}
/*
@Composable
fun openGameView(mongoDB: MongoDbChess, ids:String,onSelected:(MovesList) -> Unit){
   Column {
      val id = remember{ mutableStateOf("-1")}
      val result = remember{ mutableStateOf(MovesList(_id = id.value))}
      Text(text = "Open a Game ", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, modifier= Modifier.background(color = Color.Gray) )
      Button(onClick = {
         id.value = ids

      }){
         if(id.value.toInt() > 0) {
            if (mongoDB.gamesIDList().contains(element = id.value)) {
               val movesList = mongoDB.getGame(id = id.value)
               //val playsMade = movesList.content
               //val team = if (playsMade.isEmpty()) Team.WHITE else playsMade.last().team.other
               result.value = movesList

            } else {
               mongoDB.createID(id = id.value)
               mongoDB.createGame(MovesList(_id = id.value))
               result.value = MovesList(_id = id.value)
            }
         }
         onSelected(result.value)
      }
   }
}
*/
