package domain

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

fun buildchessCommands(board:Board):Map<String,ChessCommands>{
    return mapOf(
        "OPEN" to Open(board),
        "JOIN" to Join(board),
        "PLAY" to Play(board),
        "REFRESH" to Refresh(board),
        "MOVES" to Moves(),
        "EXIT" to Exit(),
    )
}


private class Open(private val board: Board):ChessCommands{

    override fun execute(parameter: String?): Result {
        if (parameter!= null){
            if(board.openGame(parameter)){
                BoardState()
            }else{
                getboardstate(board.moveList(), Team.WHITE)
            }
            OPEN_GAME = true
            return ValueResult(OpenedGame)
        }
        return ValueResult(InvalidCommand)
    }
}

private class Join(private val board: Board):ChessCommands {
    //TODO -> Create conditions for DB later ?: throw error

    override fun execute(parameter: String?): Result {
        if (board.moveList().isEmpty()) {
            return ValueResult(GameNotExists)
        }
        else {
            /** TODO() VERIFY ID IN DB **/
            getboardstate(board.moveList(),Team.BLACK)
            OPEN_GAME = true
        }
        return ValueResult(OpenedGame)
    }

}

private class Play(private val board: Board):ChessCommands {
    override fun execute(parameter: String?)= ValueResult(
        if(OPEN_GAME){
            if (parameter != null ){
                val playSide= board.pieceTeam(Move(stringPrepared(parameter)),teamTurn(board.moveList(),null))
                if(playSide == ValueResult(ValidMovement)) {
                   board.playMove(Move(stringPrepared(parameter)),teamTurn(board.moveList(),null))
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
    override fun execute(parameter: String?): Result {
        board.updateMoves()
        val otherplayermove = board.playMove(Move(board.moveList().last().toString()), teamTurn(board.moveList(),null))
        return ValueResult(otherplayermove)
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

private fun getboardstate(moves:List<PlayMade>, team:Team){
    val boardState= BoardState()
    moves.forEach{
        boardState.makeMove(it.play, teamTurn(moves,null))
    }
    teamTurn(moves,team)
}
private fun stringPrepared(move:String) =  if (move.length == 4) "P$move" else move

fun teamTurn(moves: List<PlayMade>,team: Team?):Team{
    return team ?: if (moves.isEmpty() || moves.size%2 == 0) Team.WHITE
    else Team.BLACK
}