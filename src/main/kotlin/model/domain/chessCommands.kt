package model.domain

import UI.endgame
import model.storage.BoardDB
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
 * To help to return both the UI.board and the result of the operation
 * @property boardState The current state of the UI.board
 * @property result The result of the command made invalid, valid, sameTeam etc.
 */
data class toReturn (val boardState:BoardState,val result: Result)
/**
 * Open command that will check if game with the id passed is opened and if it open it, else will create with that id
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Open(private val localBoard: BoardState,private val dbBoard: BoardDB): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null) {
                return if (dbBoard.gamesIDList().contains(element = parameter)) {
                    val movesList = dbBoard.findgamebyId(id = parameter)
                    val playsMade = movesList!!.content
                    val team = if (playsMade.isEmpty()) Team.WHITE else playsMade.last().team.other
                    val board = BoardState(id = parameter, turn = team,movesList = movesList)
                    ValueResult(toReturn(
                        boardState = board,
                        result = OpenedGame))
                } else {
                    dbBoard.createID(id = parameter)
                    dbBoard.createGame(MovesList(_id = parameter))
                    val board = BoardState(id = parameter, movesList = MovesList(_id = parameter))
                    ValueResult(toReturn(
                        boardState = board,
                        result = OpenedGame))
                }
            }
        return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
    }
}
/**
 * Join command that will permit to one people to join that game with black pieces
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Join(private val localBoard: BoardState,private val dbBoard: BoardDB): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
            return if (!dbBoard.gamesIDList().contains(element = parameter)) {
                ValueResult(toReturn(boardState = localBoard, result = GameNotExists))
            } else {
                val movesList = dbBoard.findgamebyId(id = parameter)
                if(movesList!!.content.last().team == Team.WHITE){
                return ValueResult(toReturn (boardState= BoardState(id = parameter,
                    turn = Team.BLACK,movesList = movesList),result = OpenedGame))
                }
                else{
                    ValueResult(toReturn (boardState= BoardState(id = parameter,
                        turn = Team.BLACK,movesList = movesList),result = OpenedGame))
                    }
                }
            }
            return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
        }
    }

/**
 * Play command that will execute the moved passed
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Play(
    private val localBoard: BoardState,
    private val dbBoard: BoardDB): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
            val move = parameter.split(',')
            val movement = Move(move = move[1])
            val team = move[0].toTeam()

            val piecesChecking = localBoard.verifyCheck(team)
            var possiblecheckmate = mutableMapOf<Location, MoveVerity>()

            if(piecesChecking.isNotEmpty()) {
                possiblecheckmate = localBoard.verifyCheckmate(piecesChecking)
                if (possiblecheckmate.isEmpty()){
                    //endgame(team)
                    /* TODO(NAO DEIXAR JOGAR MAIS) */
                }
            }

            var play = Pair(BoardState(),MoveVerity())
            if (movement.move[0] == 'k' || movement.move[0] == 'K') {
                val notInCheck = stillValidMove(movement, team,localBoard)
                if (notInCheck == ValidMovement)
                    play = localBoard.makeMove(move = movement, team = team)
            } else {
                if (possiblecheckmate.isEmpty()) {
                    play = localBoard.makeMove(move = movement, team = team)

                } else {
                    val possiblemoves = checkconditionsToMove(possiblecheckmate)
                    if (movement in possiblemoves) {
                        play =  localBoard.makeMove(move = movement, team = team)
                    }
                }
            }
            return when (play.second.result) {
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
                else -> ValueResult(toReturn(boardState = localBoard, result = play.second.result))
            }
        } else {
            return ValueResult(toReturn(boardState = localBoard, result = InvalidCommand))
        }
    }
}
/**
 * Refresh command that will refresh the UI.board to the actual state
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Refresh(private val localBoard: BoardState, private val dbBoard: BoardDB): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        val db = dbBoard.findgamebyId(localBoard.movesList._id)
        val lastMove = db!!.content.last()
        val otherPlayerMove = localBoard.makeMove(move = lastMove.play, team = lastMove.team)
        return when (otherPlayerMove.second.result){
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
        return ValueResult(toReturn(  boardState = localBoard, result = MovesGame))
    }
}
/**
 * It will close the game when called
 */
class Exit: ChessCommands {
    override fun execute(parameter: String?) = ExitResult
}
