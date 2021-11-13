import kotlin.system.exitProcess

typealias Command = (String?) -> Unit
//Como se fosse a nossa database da rasca
var listOfGames= mutableMapOf<Int, Board>()

fun chessCommands(board: Board, gameId: GameId):Map<String,Command>{
   return mapOf(
      "OPEN" to {open(board,gameId)},
      "JOIN" to {join(board,gameId)},
      "PLAY" to {play(board,it)},
      "REFRESH" to {refresh(board)},
      "MOVES" to {moves(board)},
      "EXIT" to {exit()}
   )
}

private fun open(board: Board, gameId: GameId) {
    //TODO -> Create conditions for DB later
    //Call board.gameId
    if (listOfGames[gameId.gameId] == null) {
        listOfGames.put(gameId.gameId, board)
        Board()
        println("$gameId opened..")
    }
    else {
            listOfGames[gameId.gameId]
            println("$gameId was created, opening..")
        }
}

private fun join(board: Board, gameId:GameId) {
   //TODO -> Create conditions for DB later ?: throw error
   //TODO -> Call board.gameId
   if (listOfGames[gameId.gameId] == null){
      println("${gameId.gameId}: wasn´t created yet")
   }else{
      getboardstate(board.getMoveList(),Team.BLACK)
   }
}

private fun play(board: Board,move:String?) {
    if (listOfGames.isEmpty()){
        return println("You have to open the game first")
    }

    if (move != null ){
       if(board.turnToplay(Move(move),teamTurn(board.getMoveList()))) {
               //val movePrepared = prepareTheMove(board, move)
               board.makeMove(Move(move), teamTurn(board.getMoveList()))
               draw(board)
           }
   }
    else{
        println("For play you need to write something...")
   }
}
/*
fun prepareTheMove(board: Board,move: String):String {
    val piece= Pieces.valueOf()
}

 */

private fun refresh(board: Board) {
   //TODO() ATUALIZAR JOGO CONFORME DB
    if (listOfGames.isEmpty()) { return println("You have to open the game first")}
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

fun teamTurn(moves: MutableList<PlayMade>):Team{
   return if (moves.isEmpty() || moves.size%2 == 0) Team.WHITE
            else Team.BLACK
}

