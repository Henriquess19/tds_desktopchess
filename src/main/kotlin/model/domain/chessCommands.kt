package model.domain

import model.storage.MongoDbChess

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
 * To help to return both the board and the result of the operation
 * @property boardState The current state of the board
 * @property result The result of the command made invalid, valid, sameTeam etc.
 */
data class toReturn (val boardState:BoardState,val result: Result)
/**
 * Open command that will check if game with the id passed is opened and if it open it, else will create with that id
 * @param localBoard The board being played currently
 * @param dbBoard The state of the board stored in db
 */
class Open(private val localBoard: BoardState,private val dbBoard: MongoDbChess): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null) {
            if (parameter.toInt() >= 0) {
                return if (dbBoard.gamesIDList().contains(element = parameter)) {
                    val movesList = dbBoard.findgamebyId(id = parameter)
                    val playsMade = movesList.content
                    val team = if (playsMade.isEmpty()) Team.WHITE else playsMade.last().team.other
                    val board = BoardState(id = parameter.toInt(), turn = team,movesList = movesList, openBoard = true)
                    ValueResult(toReturn(
                        boardState = board,
                        result = OpenedGame))
                } else {
                    dbBoard.createID(id = parameter)
                    dbBoard.createGame(MovesList(_id = parameter))
                    val board = BoardState(id = parameter.toInt(), movesList = MovesList(_id = parameter), openBoard = true)
                    ValueResult(toReturn(
                        boardState = board,
                        result = OpenedGame))
                }
            }
            return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
        }
        return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
    }
}
/**
 * Join command that will permit to one people to join that game with black pieces
 * @param localBoard The board being played currently
 * @param dbBoard The state of the board stored in db
 */
class Join(private val localBoard: BoardState,private val dbBoard: MongoDbChess ): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
            return if (!dbBoard.gamesIDList().contains(element = parameter)) {
                ValueResult(toReturn(boardState = localBoard, result = GameNotExists))
            } else {
                val movesList = dbBoard.findgamebyId(id = parameter)
                if(movesList.content.last().team == Team.WHITE){
                return ValueResult(toReturn (boardState= BoardState(id = parameter.toInt(),
                    turn = Team.BLACK,movesList = movesList, openBoard = true),result = OpenedGame))
                }
                else{
                    ValueResult(toReturn (boardState= BoardState(id = parameter.toInt(),
                        turn = Team.BLACK,movesList = movesList, openBoard = true),result = OpenedGame))
                    }
                }
            }
            return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
        }
    }

/**
 * Play command that will execute the moved passed
 * @param localBoard The board being played currently
 * @param dbBoard The state of the board stored in db
 */
class Play(
    private val localBoard: BoardState,
    private val dbBoard: MongoDbChess): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (!localBoard.openBoard) return ValueResult(toReturn(boardState = localBoard, result = ClosedGame))
        if (parameter != null) {
            val team = localBoard.getTeam()
            val piece = localBoard.getPiece(move = parameter)
            val play = localBoard.makeMove(move = Move(stringPrepared(move = parameter, piece = piece, team = team)), team = team)
            return when (play.second) {
                ValidMovement -> {
                    dbBoard.updateGame(moveslist = play.first.movesList)
                    ValueResult(
                        toReturn(boardState = play.first, result = ValidMovement)
                    )
                }
                EndedGame -> {
                    dbBoard.updateGame(moveslist = play.first.movesList)
                    ValueResult(
                        toReturn(boardState = play.first, result = EndedGame)
                    )
                }
                else -> ValueResult(toReturn(boardState = localBoard, result = play.second))
            }
        } else {
            return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
        }
    }
}
/**
 * Refresh command that will refresh the board to the actual state
 * @param localBoard The board being played currently
 * @param dbBoard The state of the board stored in db
 */
class Refresh(private val localBoard: BoardState, private val dbBoard: MongoDbChess): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if(!localBoard.openBoard) return ValueResult(toReturn(boardState = localBoard,result =ClosedGame))
        val db = dbBoard.findgamebyId(localBoard.movesList._id)
        val lastMove = db.content.last()
        val otherPlayerMove = localBoard.makeMove(move = lastMove.play, team = lastMove.team)
        return when (otherPlayerMove.second){
            ValidMovement -> ValueResult(toReturn(boardState = otherPlayerMove.first,result = UpdatedGame))
            EndedGame -> ValueResult(toReturn(boardState = otherPlayerMove.first,result =EndedGame))
            else -> ValueResult(toReturn(boardState = localBoard,result =InvalidMovement))
        }
    }
}
/**
 * Will show the moves made at that point
 */
class Moves(private val localBoard: BoardState): ChessCommands {
    override fun execute(parameter: String?):ValueResult<*>{
        return if(!localBoard.openBoard) ValueResult(toReturn(  boardState = localBoard, result = ClosedGame))
        else ValueResult(toReturn(  boardState = localBoard, result = MovesGame))
    }
}
/**
 * It will close the game when called
 */
class Exit: ChessCommands {
    override fun execute(parameter: String?) = ExitResult
}
