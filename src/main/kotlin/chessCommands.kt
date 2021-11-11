import kotlin.system.exitProcess

typealias Command = (String?) -> Unit

fun chessCommands(board: Board):Map<String,Command>{
   return mapOf(
      "OPEN" to {open(board)},
      "JOIN" to {join(board)},
      //"PLAY" to {play(board,move)},//TODO -> get it
      "REFRESH" to {refresh(board)},
      "MOVES" to {moves(board)},
      "EXIT" to {exit()}
   )
}

private fun open(board: Board) {
   //TODO -> Create conditions for DB later
   //Call board.gameId
   if (board.getMoveList().isEmpty()){
      Board()
      println("${board.gameId} opened..")
   }else{
      getboardstate(board.getMoveList(),Colors.WHITE)
   }
}

private fun join(board: Board) {
   //TODO -> Create conditions for DB later ?: throw error
   //TODO -> Call board.gameId
   if (board.getMoveList().isEmpty()){
      println("${board.gameId}: error")
   }else{
      getboardstate(board.getMoveList(),Colors.BLACK)
   }
}

private fun play(board: Board,move:String) {
   //TODO
  //board.makeMove(move)
   println("Playtest")
}

private fun refresh(board: Board) {
      boardConsoleDraw(board)
   }

private fun moves(board: Board) {
   var idx = 0
    val list = board.getMoveList()
   println("----------MOVES-----------")
   while(idx != list.size-1) {
       val play = list[idx]
           println(" Play Nº${idx + 1}: ${play.team} -> ${play.play}")
           idx++

   }
   println("--------------------------")
}

private fun exit() {
      exitProcess(0)
      //TODO -> With DB implementation, make secure exit
}

private fun getboardstate(moves:MutableList<PlayMade>,team:Colors){
      val board= Board()
      moves.forEach{
         board.makeMove(it.play)
      }
      //TODO() -> SET NEXT PLAYER BE 'TEAM'
}
