import kotlin.system.exitProcess

typealias Command = (String?) -> Unit
//Como se fosse a nossa database da rasca
var listOfGames= mutableMapOf<Int, Board>()

fun chessCommands(board: Board):Map<String,Command>{
   return mapOf(
      "OPEN" to {open(board)},
      "JOIN" to {join(board)},
      "PLAY" to {play(board,it)},
      "REFRESH" to {refresh(board)},
      "MOVES" to {moves(board)},
      "EXIT" to {exit()}
   )
}

private fun open(board: Board) {
    //TODO -> Create conditions for DB later
    //Call board.gameId
    if (board.getMoveList().isEmpty()) {
        Board()
        println("${board.gameId} opened..")
    }
    else {
       getboardstate(board.getMoveList(),Team.BLACK)
    }
}

private fun join(board: Board) {
   //TODO -> Create conditions for DB later ?: throw error
   //TODO -> Call board.gameId
   //Call board.gameId
   if (board.getMoveList().isEmpty()) {
      println(InvalidGame) //TODO() Print do result
   }
   else {
      getboardstate(board.getMoveList(),Team.BLACK)
   }
}

private fun play(board: Board,move:String?) {

    if (move != null ){
       val playCommand = board.turnToplay(Move(move),teamTurn(board.getMoveList()))
       if(playCommand == ValidCommand) {
               //val movePrepared = prepareTheMove(board, move)
               board.makeMove(Move(move), teamTurn(board.getMoveList()))
               draw(board)
       }else{
          println(handleResult(playCommand))
       }
   }else {
       println(handleResult(InvalidCommand))
    }
}
/*
fun prepareTheMove(board: Board,move: String):String {
    val piece= Pieces.valueOf()
}

 */

private fun refresh(board: Board) {
   //TODO() UPDATE MOVES & JOGO CONFORME DB
   draw(board)
}

fun moves(board: Board) {
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

private fun getboardstate(moves:MutableList<PlayMade>,team:Team){
      val board= Board()
      moves.forEach{
         board.makeMove(it.play, teamTurn(moves))
      }
      //TODO() -> SET NEXT PLAYER BE 'TEAM'
}

fun getGameId(board: Board):String{
   return board.gameId
}

fun teamTurn(moves: MutableList<PlayMade>):Team{
   return if (moves.isEmpty() || moves.size%2 == 0) Team.WHITE
   else Team.BLACK
}

