// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import UI.BoardView
import UI.getmovement
import UI.movesView
import UI.positionTostring
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.domain.*


@Composable
@Preview
fun App() {
   DesktopMaterialTheme {
      val board = remember{ mutableStateOf(Board()) }
      Row{
         BoardView(
            board.value,
            onTileSelected = { Piece: Piece?, Positions: Positions ->
               val move = getmovement(Piece,Positions)
               println()
               if(move != null){
                  val moves=move.split(',')
                  board.value.playMove(Move(moves[1]),Team.valueOf(moves[0]))
                  println(board.value.moveList())
                  board.value = Board(BoardState(board.value.moveList()))
                  //board.value = Board(BoardState(board.value.moveList()))
                  //println(board.value.moveList())
                  //Chamar board.playmove
               }
            }
         )
         movesView(board = board.value)
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
 /*
  val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()
    try {
        val chess= Board(BoardState(MovesList("-1", mutableListOf())),
            MongoDbChess(driver.getDatabase(dbConnection.dbName))
        )
        val dispatcher = buildCommandsHandler(chess)

    }


*/