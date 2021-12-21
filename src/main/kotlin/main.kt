// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import UI.BoardView
import UI.positionTostring
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.domain.*


@Composable
@Preview
fun App() {
   DesktopMaterialTheme {
      BoardView(BoardState(MovesList("-1",mutableListOf(PlayMade(team= Team.BLACK, play= Move("sdffs"))))), onTileSelected = {Piece: Piece?,Positions:Positions ->
         println(positionTostring(Positions))
      })
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