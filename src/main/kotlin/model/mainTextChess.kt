import model.domain.*
import model.storage.ChessDBAccessException
import model.storage.DbMode
import model.storage.MongoDbChess
import model.storage.getDBConnectionInfo
import model.storage.mongodb.createMongoClient
import model.ui.console.readChessCommand
import model.ui.console.gameDraw

fun main(){

   val dbConnection = getDBConnectionInfo()
   val driver =
      if (dbConnection.mode == DbMode.REMOTE)
         createMongoClient(dbConnection.connectionString)
      else createMongoClient()

   try {
      val chess= Board(BoardState(MovesList("-1", mutableListOf())),MongoDbChess(driver.getDatabase(dbConnection.dbName)))
      val dispatcher = buildCommandsHandler(chess)

      while (true){
         val(command,parameter) = readChessCommand(chess)
         val handler = dispatcher[command.uppercase()]
         if (handler == null) println("Invalid Command")
         else{
            val result = handler.action(parameter)
            when (result){
               is ExitResult -> break
               is ValueResult<*>-> handler.display(result.data)
            }

            gameDraw(result ,chess)
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