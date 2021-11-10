import kotlin.system.exitProcess

typealias Command = (String?) -> Unit

fun chessCommands(board: Board):Map<String,Command>{
   return mapOf(
      "OPEN" to {open(board)},
      "JOIN" to {join(board)},
      "PLAY" to {play(board,move)},//TODO -> get it
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
      getboardstate(board.movesList,Colors.WHITE)
   }
}

private fun join(board: Board) {
   //TODO -> Create conditions for DB later ?: throw error
   //TODO -> Call board.gameId
   if (board.movesList.isEmpty()){
      println("${board.gameId}: error")
   }else{
      getboardstate(board.movesList,Colors.BLACK)
   }
}

private fun play(board: Board,move:String) {
   //TODO
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

private fun getboardstate(moves:MutableMap<Int,PlayMade>,team:Colors){
      val board= Board()
      moves.forEach{
         board.makeMove(it.value.play)
      }
      //TODO() -> SET NEXT PLAYER BE 'TEAM'
}
