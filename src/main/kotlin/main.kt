fun main(){
    /*
    val driver =
        if (checkEnvironment() == DbMode.REMOTE)
            createMongoClient(System.getenv(ENV_DB_CONNECTION))
        else createMongoClient()

    val board: Board = MongoDbChess(driver.getDatabase(System.getenv(ENV_DB_NAME)))
    */
   val board = Board()
   //TODO -> ARRANJAR MELHOR MANEIRA DE COMEÇAR
   val dispatcher = chessCommands(board)

   while (true){

      val(command,parameter) = readChessCommand(board)
      val action = dispatcher[command.uppercase()]
      if (action == null) println(InvalidCommand) //TODO Print do result
      else action(parameter)
   }
}

private fun readChessCommand(board:Board):Pair<String,String?>{
   val game = getGameId(board)
   val teamTurn = teamTurn(board.getMoveList())
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}

private fun readln() = readLine()!!


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