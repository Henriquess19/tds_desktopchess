package model.domain

import model.storage.BoardDB
import model.storage.GameInfo

/**
 * To show on the end of the game, show the various plays that were made
 */
var storageOfBoards = mutableListOf<BoardState>()

/**
 * When opened a game, or joined one, compute all the state of the board, with all the moves made, and store
 * it on storageOfBoards
 * @param id the id of the game being worked
 * @param listOfPlays the list of the plays made until that time, from both teams
 */
fun storeMovesAlreadyMade(id:String, dbstuff: MutableList<PlayMade>){
    var tempBoard = BoardState(id = id)
    storageOfBoards.add(tempBoard)
    dbstuff.forEach { playMade ->
        tempBoard = tempBoard.makeMove(playMade.play, playMade.team).first
        storageOfBoards.add(tempBoard)
    }
}

fun interface ChessCommands{
    /**
     * Executes this command passing it the given parameter
     * @param parameter the commands parameter, or null, if no parameter has been passed
     */
    suspend fun execute(parameter: String?): Result

    /**
     * Overload of invoke operator, for convenience.
     */
    suspend operator fun invoke(parameter: String? = null) = execute(parameter)
}
/**
 * To help to return both the UI.board and the result of the operation
 * @property board The current state of the UI.board
 * @property result The result of the command made invalid, valid, sameTeam etc.
 */
data class toReturn (val board:Pair<BoardState,MoveVerity>,val result: Result,val endedGame:Boolean)
/**
 * Open command that will check if game with the id passed is opened and if it open it, else will create with that id
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Open(private val localBoard: BoardState, private val dbBoard: BoardDB): ChessCommands {
    override suspend fun execute(parameter: String?): ValueResult<*> {
        if (parameter!= null) {
            val state = dbBoard.getGame(id = parameter)
            return if (state == null) {
                dbBoard.updateGame(GameInfo(_id = parameter, moves = MovesList(), positions = mutableListOf<Position>(),false))
                val board = BoardState(id = parameter)
                storageOfBoards.add(board)
                ValueResult(toReturn(
                    board= Pair(board,MoveVerity()),
                    result = OpenedGame,
                    endedGame = false))
            } else {
                val team = state.first.content.last().team.other
                val board = BoardState(id = parameter, turn = team,movesList = state.first)
                storeMovesAlreadyMade(id=parameter, dbstuff = state.first.content)
                val ended = state.third
                ValueResult(toReturn(
                    board = Pair(board,MoveVerity(state.second,OpenedGame)),
                    result = OpenedGame,
                    endedGame = ended))
            }
        }
        return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand, endedGame = false))
    }
}

/**
 * Join command that will permit to one people to join that game with black pieces
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Join(private val localBoard: BoardState,private val dbBoard: BoardDB): ChessCommands {
    override suspend fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
            val state = dbBoard.getGame(id = parameter)
            if (state == null) {
                return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = GameNotExists, endedGame= false))
            } else {
                storeMovesAlreadyMade(id=parameter, dbstuff = state.first.content)
                println(storageOfBoards)
                return ValueResult(
                    toReturn(
                        board = Pair(BoardState(
                            id = parameter,
                            turn = state.first.content.last().team.other,
                            movesList = state.first), MoveVerity(state.second,OpenedGame)
                        ),
                        result = OpenedGame,
                        endedGame= state.third
                    )
                )
            }
        }
        return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand,endedGame = false))
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
    override suspend fun execute(parameter: String?): ValueResult<*> {
        if (parameter != null) {
            var play = Pair(localBoard,MoveVerity())
            val movement = Move(move = parameter)
            val team = localBoard.getTeam()


            val piecesChecking = localBoard.verifyCheck(team)
            var possiblecheckmate = mutableMapOf<Location, MoveVerity>()

            if(piecesChecking.isNotEmpty()) {
                possiblecheckmate = localBoard.verifyCheckmate(piecesChecking)
                if (possiblecheckmate.isEmpty()){
                    dbBoard.updateGame(GameInfo(_id = localBoard.id, moves = play.first.movesList, positions = play.second.tiles,true))
                    return ValueResult(
                        toReturn(board = play, result = EndedGame,endedGame=true)
                    )
                }
            }

            val notInCheck = stillValidMove(movement, team,localBoard)
            if (movement.move[0] == 'k' || movement.move[0] == 'K') {
                if (notInCheck == ValidMovement)
                    play = localBoard.makeMove(move = movement, team = team)
            } else {
                if (possiblecheckmate.isEmpty() && notInCheck == ValidMovement) {
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
                    storageOfBoards.add(play.first)
                    println(storageOfBoards)
                    dbBoard.updateGame(GameInfo(_id = localBoard.id, moves = play.first.movesList, positions = play.second.tiles,false))
                    ValueResult(
                        toReturn(board = play, result = ValidMovement,endedGame= false)
                    )

                }
                EndedGame -> {
                    storageOfBoards.add(play.first)
                    dbBoard.updateGame(GameInfo(_id = localBoard.id,moves = play.first.movesList, positions = play.second.tiles,true))
                    ValueResult(
                        toReturn(board = play, result = EndedGame,endedGame= true)
                    )
                }
                else -> ValueResult(toReturn(board = Pair(localBoard,play.second), result = play.second.result,endedGame= false))
            }
        } else {
            return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = InvalidCommand,endedGame= false))
        }
    }
}
/**
 * Refresh command that will refresh the UI.board to the actual state
 * @param localBoard The UI.board being played currently
 * @param dbBoard The state of the UI.board stored in db
 */
class Refresh(private val localBoard: BoardState, private val dbBoard: BoardDB): ChessCommands {
    override suspend fun execute(parameter: String?): ValueResult<*> {
        val refresh = dbBoard.getGame(localBoard.id)
        return if (refresh != null) {
            if(refresh.first.currentState != storageOfBoards.last().movesList.currentState) {
                storageOfBoards.add(BoardState(movesList = refresh.first, id = localBoard.id))
            }
            ValueResult(
                toReturn(
                    Pair(
                        BoardState(movesList = refresh.first, id = localBoard.id),
                        MoveVerity(refresh.second, ValidMovement)
                    ), ValidMovement, refresh.third
                )
            )
        } else ValueResult(InvalidCommand)
    }
}
/**
 * Will show the moves made at that point
 */
class Moves(private val localBoard: BoardState): ChessCommands {
    override suspend fun execute(parameter: String?):ValueResult<*>{
        return ValueResult(toReturn(board = Pair(localBoard,MoveVerity()), result = MovesGame,endedGame = false))
    }
}
/**
 * It will close the game when called
 */
class Exit: ChessCommands {
    override suspend fun execute(parameter: String?) = ExitResult
}
