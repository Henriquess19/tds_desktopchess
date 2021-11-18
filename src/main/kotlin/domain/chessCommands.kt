package domain


var OPEN_GAME = false
var TEAM_TURN = Team.WHITE

fun interface ChessCommands{
    /**
     * Executes this command passing it the given parameter
     * @param parameter the commands parameter, or null, if no parameter has been passed
     */
    fun execute(parameter: String?): ValueResult<*>

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

    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null){
            if(parameter in board.dbboard.gamesIDList()){
                board.openGame(parameter)
                getboardstate(board, Team.WHITE)
            }else{
                board.createGame(parameter)
            }
            OPEN_GAME = true
          return ValueResult(OpenedGame)
        }
        return ValueResult(InvalidCommand)
    }
}

private class Join(private val board: Board):ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
                if (parameter !in board.dbboard.gamesIDList()) {
                return ValueResult(GameNotExists)
            }
            else {
                board.openGame(parameter)
                getboardstate(board,Team.BLACK)
                OPEN_GAME = true
            }
            return ValueResult(OpenedGame)
        }
        return ValueResult(InvalidCommand)
    }
}

private class Play(private val board: Board):ChessCommands {
    override fun execute(parameter: String?):ValueResult<*> {
        return if (OPEN_GAME) {
            if (parameter != null) {
                val playSide = board.pieceTeam(Move(stringPrepared(parameter)), teamTurn(null))
                if (playSide.data == ValidMovement) {
                     board.playMove(Move(stringPrepared(parameter)), teamTurn(null))
                } else {
                    ValueResult(InvalidMovement)
                }
            } else {
                ValueResult(InvalidCommand)
            }
        } else {
            ValueResult(ClosedGame)
        }
    }
}

private class Refresh(private val board: Board):ChessCommands{
    override fun execute(parameter: String?): ValueResult<*> {
        board.updateMoves()
        val otherplayermove = board.playMove(Move(board.moveList().content.last().play.move), teamTurn(null))
        return if (otherplayermove.data == ValidMovement) ValueResult(UpdatedGame)
            else ValueResult(InvalidCommand)
    }
}

private class Moves(): ChessCommands{
    override fun execute(parameter: String?)= ValueResult(MovesGame)
}

private class Exit:ChessCommands {
    override fun execute(parameter: String?) = ValueResult(ExitResult)
}

private fun getboardstate(board: Board, team:Team){
    val newBoard=BoardState(MovesList(board.GAMEID, mutableListOf()))
    board.moveList().content.forEach{ newBoard.makeMove(it.play, teamTurn(it.team))}
    board.localboard = newBoard
    teamTurn(team)
}

fun teamTurn(team: Team?):Team{
    TEAM_TURN = team ?: if (TEAM_TURN == Team.BLACK) Team.WHITE
    else Team.BLACK
    return TEAM_TURN
}

fun nextTeam(): Team {
    return TEAM_TURN
}