// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import UI.*
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.domain.*
import model.storage.*
import model.storage.mongodb.*
import org.intellij.lang.annotations.JdkConstants
import org.jetbrains.skija.paragraph.Alignment
import org.litote.kmongo.MongoOperator

/*
fun main(){
   val dbConnection = getDBConnectionInfo()
   val driver =
      if (dbConnection.mode == DbMode.REMOTE)
         createMongoClient(dbConnection.connectionString)
      else createMongoClient()
   try {
      var localBoard = BoardState(openBoard = false)
      val mongoDB = MongoDbChess(driver.getDatabase(dbConnection.dbName))
      while (true){
         val dispatcher = buildCommandsHandler(localBoard = localBoard, dbBoard = mongoDB)
         val(command,parameter) = readChessCommand(localBoardState = localBoard)
         val handler = dispatcher[command.uppercase()]
         if (handler == null) println("Invalid Command")
         else{
            when (val result = handler.action(parameter)){
               is ExitResult -> break
               is ValueResult<*>->{
                  val resulted = result.data as toReturn
                  localBoard = resulted.boardState
                  handler.display(result.data)
               }
            }
         }
      }
   }
   catch (e: ChessDBAccessException) {
      println(
         "An unknown error occurred while trying to reach the database. " +
                 if (dbConnection.mode == DbMode.REMOTE) "Check your network connection."
                 else "Is your local database started?"
      )
   }
   finally {
      println("Closing driver ...")
      driver.close()
   }
}
*/

@Composable
fun TextDemo() {
    Column(Modifier.padding(16.dp)) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        var id: String = "-1"

        TextField(
            value = textState.value,
            maxLines = 1,
            label= {Text("GameID")},
            onValueChange = { textState.value = it }
        )

        Button(
            onClick = {
                id = textState.value.text
                println(id)
            }
        ){       }
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

@Composable
fun App(mongoDB: MongoDbChess) {
    DesktopMaterialTheme {
        val board = remember {
            mutableStateOf(
                Pair<BoardState, MoveVerity>(
                    BoardState(openBoard = true),
                    MoveVerity(mutableListOf(), ValidMovement)
                )
            )
        }
        val movement = remember { mutableStateOf(Move("dummy")) }
        val team = remember { mutableStateOf(Team.WHITE) }

        TextDemo()

        //openGameView(mongoDB = mongoDB, ids = "4",onSelected = { println(it)})
        /*val piecesChecking = board.value.first.verifyCheck(board.value.first.getTeam())
        var possiblecheckmate = mutableMapOf<Location, MoveVerity>()

        if(piecesChecking.isNotEmpty()) {
            possiblecheckmate = board.value.first.verifyCheckmate(piecesChecking)
            if (possiblecheckmate.isEmpty()){
                /* TODO(ENDGAME) */
                println("Acabou")
            }
        }

        Row {
            BoardView(
                board = board.value.first,
                onTileSelected = { piece: Piece?, positions: Positions ->
                    val move = getmovement(piece, positions)
                    if (move != null) {
                        val moves = move.split(',')
                        movement.value = Move(move = moves[1])
                        team.value = moves[0].toTeam()

                        if (possiblecheckmate.isEmpty()) {
                            board.value = board.value.first.makeMove(move = movement.value, team = team.value)

                        }else{
                            val possiblemoves = checkconditionsToMove(possiblecheckmate)
                            if (movement.value in possiblemoves){
                                board.value = board.value.first.makeMove(move = movement.value, team = team.value)
                            }
                        }
                    }
                }
            )
            movesView(board = board.value.first)
            if(board.value.second.result != ValidMovement) board.value = composingResults(board =  board.value, team = team.value, movement = movement.value )
        }
    }

         */
    }
}


fun main(){
    val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()
    val mongoDB = MongoDbChess(driver.getDatabase(dbConnection.dbName))
    application {
        Window(
            state = WindowState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
            onCloseRequest = ::exitApplication
        ) {
            App(mongoDB)
        }
    }
}