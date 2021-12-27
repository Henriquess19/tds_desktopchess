// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import UI.*
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.domain.*
import model.storage.*
import model.storage.mongodb.*
import model.ui.console.readChessCommand
import kotlin.Result

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
fun App() {
    DesktopMaterialTheme {
        val board = remember { mutableStateOf(Pair<BoardState, model.domain.Result>(BoardState(openBoard = true), ValidMovement)) }
        val movement = remember { mutableStateOf(Move("aaaa")) }
        val team = remember { mutableStateOf(Team.WHITE) }
        Row {
            BoardView(
                board = board.value.first,
                onTileSelected = { piece: Piece?, positions: Positions ->
                    val move = getmovement(piece, positions)
                    if (move != null) {
                        val moves = move.split(',')
                        movement.value = Move(move = moves[1])
                        team.value = moves[0].toTeam()
                        board.value = board.value.first.makeMove(move = movement.value, team = team.value)
                    }
                }
            )
            movesView(board = board.value.first)
            if(board.value.second != ValidMovement) board.value = composingResults(board =  board.value, team = team.value, movement = movement.value )
        }
    }
}

fun main() = application {
    Window(
        state = WindowState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
