

import domain.Board
import isel.leic.tds.mongodb.createMongoClient
import storage.DbMode
import storage.MongoDbChess
import storage.getDBConnectionInfo
import ui.console.readChessCommand

fun main(){

    val dbConnection = getDBConnectionInfo()
    val driver =
        if (dbConnection.mode == DbMode.REMOTE)
            createMongoClient(dbConnection.connectionString)
        else createMongoClient()

   try {
       val chess: ChessCommands = MongoDbChess(driver.getDatabase(dbConnection.dbName))
       val dispatcher = chessCommands(chess)

      while (true){
         val(command,parameter) = readChessCommand(board)
         val action = dispatcher[command.uppercase()]
         if (action == null) println(handleResult(InvalidCommand))
         else{
            val result = action(parameter)
            TODO("when(result)")
         }
      }
   }
   finally {
      println("Closing driver ...")
      driver.close()
   }
}


/*
private const val ENV_DB_NAME = "MONGO_DB_NAME"
private const val ENV_DB_CONNECTION = "MONGO_DB_CONNECTION"

private enum class DbMode { LOCAL, REMOTE }

private fun checkEnvironment(): DbMode {
    requireNotNull(System.getenv(ENV_DB_NAME)) {
        "You must specify the environment variable $ENV_DB_NAME"
    }

    return if (System.getenv(ENV_DB_CONNECTION) != null) DbMode.REMOTE
    else DbMode.LOCAL
}
*/