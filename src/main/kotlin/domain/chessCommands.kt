package domain

var OPEN_GAME = false

var TEAM_TURN = Team.WHITE

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

/**
 * Open command that will check if game with the id passed is opened and if it open it, else will create with that id
 * @param board the that will be worked
 */

class Open(private val board: Board):ChessCommands{

    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null){
            if(parameter in board.dbBoard.gamesIDList()){
                board.openGame(parameter)
                getBoardState(board, Team.WHITE)
            }else{
                board.createGame(parameter)
            }
            OPEN_GAME = true
          return ValueResult(OpenedGame)
        }
        return ValueResult(InvalidCommand)
    }
}
/**
 * Join command that will permit to one people to join that game with black pieces
 * @param board the that will be worked
 */
class Join(private val board: Board):ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
                if (parameter !in board.dbBoard.gamesIDList()) {
                return ValueResult(GameNotExists)
            }
            else {
                board.openGame(parameter)
                getBoardState(board,Team.BLACK)
                OPEN_GAME = true
            }
            return ValueResult(OpenedGame)
        }
        return ValueResult(InvalidCommand)
    }
}
/**
 * Play command that will execute the moved passed
 * @param board the that will be worked
 */
class Play(private val board: Board):ChessCommands {
    override fun execute(parameter: String?):ValueResult<*> {
        return if (OPEN_GAME) {
            if (parameter != null) {
                val playSide = board.correctPieceTeam(Move(stringPrepared(parameter)), teamTurn(board.moveList(),null))
                if (playSide.data == ValidMovement) {
                     board.playMove(Move(stringPrepared(parameter)), teamTurn(board.moveList(),null))
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
/**
 * Refresh command that will refresh the board to the actual state
 * @param board the that will be worked
 */
class Refresh(private val board: Board):ChessCommands{
    override fun execute(parameter: String?): ValueResult<*> {
        board.updateMoves()
        val otherPlayerMove = board.playMove(Move(board.moveList().content.last().play.move), teamTurn(board.moveList(),null))
        return if (otherPlayerMove.data == ValidMovement) ValueResult(UpdatedGame)
            else ValueResult(InvalidCommand)
    }
}
/**
 * Will show the user the moves until that time
 * @param board the that will be worked
 */
class Moves: ChessCommands{
    override fun execute(parameter: String?)= ValueResult(MovesGame)
}
/**
 * Will close the game if the user wants it
 * @param board the that will be worked
 */
class Exit:ChessCommands {
    override fun execute(parameter: String?) = ValueResult(ExitResult)
}
/**
 * Update the local board with the content on the db one
 * @param board the that will be worked
 * @param team who team is playing that turn
 */

private fun getBoardState(board: Board, team:Team){
    val newBoard=BoardState(MovesList(board.GAMEID, mutableListOf()))
    board.moveList().content.forEach{ newBoard.makeMove(it.play, teamTurn(board.moveList(),it.team))}
    board.localBoard = newBoard
    teamTurn(board.moveList(),team)
}
/**
 * The team who is suppose to playing based on the last play made. If don't have any play, team white will start the game
 * @param moves check who was the last one to play
 * @param team Force that team to play
 * @return [Team] to who is going to play
 */
private fun teamTurn(moves:MovesList,team: Team?):Team{
    TEAM_TURN = team ?: if (moves.content.isEmpty() || moves.content.size %2 == 0) Team.WHITE
    else Team.BLACK
    return TEAM_TURN
}
/**
 * Who is suppose to play next
 * @param board check who was the last one to play
 * @return [Team] next team playing
 */

fun nextTeam(board: Board): Team {
    return if (board.moveList().content.isEmpty()) Team.WHITE
     else switch(board.localBoard.moves.content.last().team)
}
/**
 * Switch the team with the other one
 * @param team who the original team
 * @return [Team] the other team
 */
private fun switch(team: Team):Team{
    return if (team == Team.WHITE) Team.BLACK
        else Team.WHITE
}