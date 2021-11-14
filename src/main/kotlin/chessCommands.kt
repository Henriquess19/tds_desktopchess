import kotlin.system.exitProcess

typealias Command = (String?) -> Unit

var OPEN_GAME = false

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
    //TODO -> Call board.gameId
    //Call board.gameId
    if (board.getMoveList().isEmpty()) {
        Board()
        println("${board.gameId} opened..\n")
        OPEN_GAME = true
        draw(board)
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
        println(handleResult(InvalidGame))
    }
    else {
        getboardstate(board.getMoveList(),Team.BLACK)
        println("${board.gameId} joined..\n")
        OPEN_GAME = true
        draw(board)
    }
}

private fun play(board: Board,move:String?) {
    if(OPEN_GAME){
        if (move != null ){
            val playSide= board.turnToPlay(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null))
            if(playSide == ValidMovement) {
                //val movePrepared = prepareTheMove(board, move)
                board.makeMove(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null))
                draw(board)
            }else{
                println(handleResult(playSide) + "That´s not your piece")
            }
        }else{
            println(handleResult(InvalidCommand) + "Try to write something")
        }
    }else{
        println(handleResult(ClosedGame))
    }
}

private fun refresh(board: Board) {
    //TODO() UPDATE MOVES & JOGO CONFORME DB
    draw(board)
}

fun moves(board: Board) {
    if (OPEN_GAME) {
        var idx = 0
        val list = board.getMoveList()
        println("----------MOVES-----------")
        while (idx != list.size - 1 && list.isNotEmpty()) {
            val play = list[idx]
            println(" Play Nº${idx + 1}: ${play.team} -> ${play.play}")
            idx++

        }
        println("--------------------------")
    }else{
        println(handleResult(ClosedGame))
    }
}

private fun exit() {
    exitProcess(0)
    //TODO -> With DB implementation, make secure exit
}

private fun getboardstate(moves:MutableList<PlayMade>,team:Team){
    val board= Board()
    moves.forEach{
        board.makeMove(it.play, teamTurn(moves,null))
    }
    teamTurn(moves,team)
}
private fun stringPrepared(move:String) =  if (move.length == 4) "P$move" else move

fun getGameId(board: Board):String{
    return board.gameId
}

fun teamTurn(moves: MutableList<PlayMade>,team: Team?):Team{
    return team ?: if (moves.isEmpty() || moves.size%2 == 0) Team.WHITE
    else Team.BLACK
}