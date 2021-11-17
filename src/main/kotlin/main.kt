import domain.*
import isel.leic.tds.mongodb.createMongoClient
import storage.BoardDB
import storage.DbMode
import storage.MongoDbChess
import storage.getDBConnectionInfo
import ui.console.getViews
import ui.console.readChessCommand

fun main(){

    val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

   try {
       val chess= Board(BoardState(),MongoDbChess(driver.getDatabase(dbConnection.dbName)))
       val dispatcher = buildchessCommands(chess)
       val views = getViews()

      while (true){
         val(command,parameter) = readChessCommand(chess.localboard)
         val action = dispatcher[command.uppercase()]
         if (action == null) println("Invalid Command")
         else{
            val result = action(parameter)
            when (result){
               is ExitResult -> break
               is ValueResult<*> -> views[command]?.invoke(result.data)
            }
         }
      }
   }
   finally {
      println("Closing driver ...")
      driver.close()
   }
}
