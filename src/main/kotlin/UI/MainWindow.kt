package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
   App()
}

/** ----------------------------------------------------------------------------**/

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
fun App() {

      val board = remember {
         mutableStateOf(
            Pair<BoardState, MoveVerity>(
               BoardState(),
               MoveVerity(mutableListOf(), ValidMovement)
            )
         )
      }
      val movement = remember { mutableStateOf(Move("dummy")) }
      val team = remember { mutableStateOf(Team.WHITE) }


      //val piecesChecking = board.value.first.verifyCheck(board.value.first.getTeam())
      var possiblecheckmate = mutableMapOf<Location, MoveVerity>()

      /*if(piecesChecking.isNotEmpty()) {
         possiblecheckmate = board.value.first.verifyCheckmate(piecesChecking)
         if (possiblecheckmate.isEmpty()){
            endgame(team.value)
            /* TODO(NAO DEIXAR JOGAR MAIS) */
         }
      }*/

      Row {
         BoardView(
            board = board.value.first,
            onTileSelected = { piece: Piece?, positions: Positions ->
               println(positions)
               makePlay(board,movement,team,piece,positions,possiblecheckmate)
            }
         )
         movesView(board = board.value.first)
         if(board.value.second.result != ValidMovement) board.value = composingResults(board =  board.value, team = team.value, movement = movement.value )
      }
}

fun makePlay(board:MutableState<Pair<BoardState, MoveVerity>>,
             movement:MutableState<Move>,team:MutableState<Team>,
             piece:Piece?,positions:Positions,
             possiblecheckmate:MutableMap<Location, MoveVerity>){

   val move = getmovement(piece, positions)
   if (move != null) {
      val moves = move.split(',')
      movement.value = Move(move = moves[1])
      team.value = moves[0].toTeam()
      if (movement.value.move[0] == 'k' || movement.value.move[0] == 'K') {
         val notInCheck = stillValidMove(movement.value, team.value, board.value.first)
         if (notInCheck == ValidMovement)
            board.value = board.value.first.makeMove(move = movement.value, team = team.value)
      } else {
         if (possiblecheckmate.isEmpty()) {
            board.value = board.value.first.makeMove(move = movement.value, team = team.value)

         } else {
            val possiblemoves = checkconditionsToMove(possiblecheckmate)
            if (movement.value in possiblemoves) {
               board.value =
                  board.value.first.makeMove(move = movement.value, team = team.value)
            }
         }
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
               val movesList = mongoDB.findgamebyId(id = id.value)
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

