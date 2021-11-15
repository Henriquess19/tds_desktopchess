import domain.Board
import domain.PlayMade
import domain.Team
import kotlin.system.exitProcess

typealias Command = (String?) -> Unit

var OPEN_GAME = false

//TODO() APLICAR O NINETEENCOMMANDS.KT AQUI

fun interface ChessCommands{
    fun execute(parameter: String?):Result
}

fun buildchessCommands(board: Board):Map<String,ChessCommands>{
    return mapOf(
        "OPEN" to {open(board,it)},
        "JOIN" to {join(board)},
        "PLAY" to {play(board,it)},
        "REFRESH" to {refresh(board)},
        "MOVES" to {moves(board)},
        "EXIT" to Exit(),
    )
}


private class open(board: Board, id:String?):ChessCommands{

    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?): Result {
        val debbugg = 1
        if(debbugg==1 /** TODO() VERIFY ID IN DB **/){
            Board()
            OPEN_GAME = true
        }else{
            getboardstate(board.getMoveList(), Team.BLACK)
        }
        return ValueResult(OpenedGame)
    }
}

private class join(board: Board):ChessCommands {
    //TODO -> Create conditions for DB later ?: throw error

    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?): Result {
        if (board.getMoveList().isEmpty()) {
            return ValueResult(GameNotExists)
        }
        else {
            /** TODO() GET ID IN DB **/
            getboardstate(board.getMoveList(),Team.BLACK)
            println("${board.gameId} joined..\n")
            OPEN_GAME = true
            draw(board)
        }
        return ValueResult(OpenedGame)
    }

}

private class play(board: Board,move:String?):ChessCommands {
    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?): Result {
        if(OPEN_GAME){
            if (move != null ){
                val playSide= board.pieceTeamCheck(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null))
                if(playSide == ValueResult(ValidMovement)) {
                    board.makeMove(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null))
                    draw(board)
                    return ValueResult(ValidMovement)
                }else{
                    return ValueResult(InvalidMovement)
                }
            }else{
                return ValueResult(InvalidCommand)
            }
        }
        return ValueResult(ClosedGame)
    }
}

private class refresh(board: Board) :ChessCommands{
    //TODO() UPDATE MOVES & JOGO CONFORME DB
    override fun execute(parameter: String?): Result {
        draw(board)
        TODO("Not yet implemented")
    }

}

private class moves() :ChessCommands{
    override fun execute(parameter: String?): Result {
        return if (OPEN_GAME) {
            ValueResult("OpenedGame")
        }else{
           ValueResult("ClosedGame")
        }
    }
}

private class Exit:ChessCommands {
    override fun execute(parameter: String?) = ExitResult
    //TODO -> With DB implementation, make secure exit
}

private fun getboardstate(moves:MutableList<PlayMade>, team:Team){
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