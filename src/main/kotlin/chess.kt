import kotlin.system.exitProcess

fun chessCommands(board: Board,command:String):Map<>{
   return mapOf(
      "OPEN" to {open(gameId,board)},
      "JOIN" to {join(gameId,board)},
      "PLAY" to {play(move,board)},
      "REFRESH" to {refresh(board)},
      "MOVES" to {moves(board)},
      "EXIT" to {exit()}
   )
}

private fun open(gameId: String,board: Board) {
   //TODO -> Create conditions for DB later
   if (board.movesList.isEmpty()){
      Board()
   }else{
      //TODO -> Function to make board to the state && WHITES start
   }
}

private fun join(gameId: String,board: Board) {
   //TODO -> Create conditions for DB later ?: throw error
   if (board.movesList.isEmpty()){
      //TODO -> throw error
   }else{
      //TODO -> Function to make board to the state && BLACK start
   }
}

private fun play(move: String,board: Board) {
  board.makeMove(move)
}

private fun refresh(board: Board) {
      BoardConsoleDraw(board).draw()
   }

private fun moves(board: Board) {
   println("----------MOVES-----------")
   board.movesList.forEach { println(" Play Nº${it.key + 1}: ${it.value.team} -> ${it.value.play}") }
   println("--------------------------")
}

private fun exit() {
      exitProcess(0)
      //TODO -> With DB implementation, make secure exit
}
