import domain.Board
import domain.ExitResult
import domain.ValueResult
import domain.buildchessCommands
import isel.leic.tds.mongodb.createMongoClient
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
       val chess: Board = MongoDbChess(driver.getDatabase(dbConnection.dbName)) //TODO()
       val dispatcher = buildchessCommands(chess)
       val views = getViews()

      while (true){
         val(command,parameter) = readChessCommand(chess)
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