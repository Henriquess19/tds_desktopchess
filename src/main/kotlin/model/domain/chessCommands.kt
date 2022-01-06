package model.domain

import model.storage.BoardDB

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
data class toReturn (val board:Pair<BoardState,MoveVerity>,val result: Result)
/**
 * Open command that will check if game with the id passed is opened and if it open it, else will create with that id
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Open(private val localBoard: BoardState,private val dbBoard: BoardDB): ChessCommands {
    override fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null) {
            val state = dbBoard.getGame(id = parameter)
            return if (state == null) {
                dbBoard.updateGame(id = parameter, movesList = MovesList(), positions = mutableListOf<Positions>())
                val board = BoardState(id = parameter)
                ValueResult(toReturn(
                    board= Pair(board,MoveVerity()),
                    result = OpenedGame))
            } else {
                val team = state.first.content.last().team.other
                val board = BoardState(id = parameter, turn = team,movesList = state.first)
                ValueResult(toReturn(
                    board = Pair(board,MoveVerity(state.second,OpenedGame)),
                    result = OpenedGame))
            }
        }
    return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand))
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
            val state = dbBoard.getGame(id = parameter)
            if (state == null) {
                return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = GameNotExists))
            } else {
                return ValueResult(
                    toReturn(
                        board = Pair(BoardState(
                            id = parameter,
                            turn = state.first.content.last().team.other,
                            movesList = state.first), MoveVerity(state.second,OpenedGame)
                        ), result = OpenedGame
                    )
                )
            }
        }
        return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand))
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
            val movement = Move(move = parameter)
            val team = localBoard.getTeam()


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
                    dbBoard.updateGame(id = localBoard.id, movesList = play.first.movesList, positions = play.second.tiles)
                    ValueResult(
                        toReturn(board = play, result = ValidMovement)
                    )
                }
                EndedGame -> {
                    dbBoard.updateGame(id = localBoard.id,movesList = play.first.movesList, positions = play.second.tiles)
                    ValueResult(
                        toReturn(board = play, result = EndedGame)
                    )
                }
                else -> ValueResult(toReturn(board = Pair(localBoard,play.second), result = play.second.result))
            }
        } else {
            return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand))
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
        if(localBoard.id !="") {
            val dbGame = dbBoard.getGame(localBoard.id)
            return if(dbGame != null) {
                val lastMove = dbGame.first.content.last()
                val otherPlayerMove = localBoard.makeMove(move = lastMove.play, team = lastMove.team)
                println(otherPlayerMove.second.result)
                when (otherPlayerMove.second.result) {
                    ValidMovement -> ValueResult(toReturn(board = Pair(otherPlayerMove.first,MoveVerity(dbGame.second,ValidMovement)), result = UpdatedGame))
                    EndedGame -> ValueResult(toReturn(board = Pair(otherPlayerMove.first,MoveVerity(dbGame.second,ValidMovement)), result = EndedGame))
                    else -> ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand))
                }
            } else{
                return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = UpdatedGame))
            }
        }
        else{
            return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = CannotRefresh))

        }
    }
}
/**
 * Will show the moves made at that point
 */
class Moves(private val localBoard: BoardState): ChessCommands {
    override fun execute(parameter: String?):ValueResult<*>{
        return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = MovesGame))
    }
}
/**
 * It will close the game when called
 */
class Exit: ChessCommands {
    override fun execute(parameter: String?) = ExitResult
}
