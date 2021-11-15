package domain

import domain.Board
import storage.ChessInterface

var OPEN_GAME = false

fun interface ChessCommands{
    /**
     * Executes this command passing it the given parameter
     * @param parameter the commands parameter, or null, if no parameter has been passed
     */
    fun execute(parameter: String?): Result

    /**
     * Overload of invoke operator, for convenience.
     */
    operator fun invoke(parameter: String? = null) = execute(parameter)
}

fun buildchessCommands(board: Board):Map<String,ChessCommands>{
    return mapOf(
        "OPEN" to Open(board,it),
        "JOIN" to Join(board),
        "PLAY" to Play(board,it),
        "REFRESH" to Refresh(board),
        "MOVES" to Moves(),
        "EXIT" to Exit(),
    )
}


private class Open(private val board: Board,private val id:String?):ChessCommands{

    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?): Result {
        val debbugg = 1
        if(debbugg==1 /** TODO() VERIFY ID IN DB **/){
            Board()
            OPEN_GAME = true
        }else{
            getboardstate(board.getMoveList(), Team.WHITE)
        }
        return ValueResult(OpenedGame)
    }
}

private class Join(private val board: Board):ChessCommands {
    //TODO -> Create conditions for DB later ?: throw error

    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?): Result {
        if (board.getMoveList().isEmpty()) {
            return ValueResult(GameNotExists)
        }
        else {
            /** TODO() GET ID IN DB **/
            getboardstate(board.getMoveList(),Team.BLACK)
            OPEN_GAME = true
        }
        return ValueResult(OpenedGame)
    }

}

private class Play(private val board: Board,private val move:String?):ChessCommands {
    /** TODO(VER COMO TRATAR A FUNCAO) **/
    override fun execute(parameter: String?)= ValueResult(
        if(OPEN_GAME){
            if (move != null ){
                val playSide= board.pieceTeamCheck(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null))
                if(playSide == ValueResult(ValidMovement)) {
                    board.makeMove(Move(stringPrepared(move)),teamTurn(board.getMoveList(),null)).second
                }else{
                    InvalidMovement
                }
            }else{
                 InvalidCommand
            }
        }else {
            ClosedGame
        }
    )
}

private class Refresh(private val board: Board) :ChessCommands{
    //TODO() UPDATE MOVES & JOGO CONFORME DB
    override fun execute(parameter: String?): Result {
        return ValueResult(UpdatedGame)
        TODO("Not yet implemented")
    }

}

private class Moves: ChessCommands{
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