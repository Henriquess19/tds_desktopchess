// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import model.domain.*
import model.storage.*
import model.storage.mongodb.*
import model.ui.console.readChessCommand

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


/*
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