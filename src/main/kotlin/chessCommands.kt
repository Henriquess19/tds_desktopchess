import kotlin.system.exitProcess

typealias Command = (String?) -> Unit

fun chessCommands(board: Board):Map<String,Command>{
   return mapOf(
      "OPEN" to {open(board)},
      "JOIN" to {join(board)},
      "PLAY" to {play(board)},
      "REFRESH" to {refresh(board)},
      "MOVES" to {moves(board)},
      "EXIT" to {exit()}
   )
}

private fun open(board: Board) {
   //TODO -> Create conditions for DB later
   //Call board.gameId
   if (board.movesList.isEmpty()){
      Board()
      println("${board.gameId} opened..")
   }else{
      //TODO -> Function to make board to the state && WHITES start

   }

}

private fun join(board: Board) {
   //TODO -> Create conditions for DB later ?: throw error
   //Call board.gameId
   if (board.movesList.isEmpty()){
      println("${board.gameId}: error")
   }else{
      //TODO -> Function to make board to the state && BLACK start
   }
}

private fun play(board: Board) {
  //board.makeMove(move)
   println("Playtest")
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
