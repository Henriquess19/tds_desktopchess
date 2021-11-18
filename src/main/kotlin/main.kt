import domain.*
import storage.DbMode
import storage.MongoDbChess
import storage.getDBConnectionInfo
import storage.mongodb.createMongoClient
import ui.console.draw
import ui.console.gameDraw
import ui.console.getViews
import ui.console.readChessCommand

fun main(){

    val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

   try {
       val chess= Board(BoardState(MovesList(null, mutableListOf())),MongoDbChess(driver.getDatabase(dbConnection.dbName)))
       val dispatcher = buildchessCommands(chess)
       val views = getViews()

      while (true){
         val(command,parameter) = readChessCommand(chess)
         val action = dispatcher[command.uppercase()]
         if (action == null) println("Invalid Command")
         else{
            val result = action(parameter)
            when (result.data){
               is ExitResult -> break
               else -> views[command.uppercase()]?.invoke(result.data)
            }
            gameDraw(ValueResult(result.data),chess)
         }
      }
   }
   finally {
      println("Closing driver ...")
      driver.close()
   }
}
