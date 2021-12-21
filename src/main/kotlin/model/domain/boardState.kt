package model.domain

import BoardStateInterface
import movePieceVerity

/**
 * Representation of the columns in a board of chess
 */
enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}

const val BOARD_SIDE = 8


fun isValid(value: Int) = value in 0 until BOARD_SIDE

fun Int.toColumn():Columns {
    require(isValid(this))
    return Columns.values()[this]
}
/**
 * Representation of the line in a board of chess
 */
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}

fun Int.toLine(): Lines {
    require(isValid(this))
    return Lines.values()[this]
}

/**
 * The two teams that are playable
 */
enum class Team{WHITE,BLACK}

/**
 * Represents the position of the piece
 * @property lines  The line where the piece is
 * @property column  The column where the piece is
 */
data class Positions(val line: Lines, val column: Columns)
/**
 * Represents the play that was made
 * @property team  The team where the piece was
 * @property play  The play itself
 */
data class PlayMade(val team: Team, val play: Move)
/**
 * Represents the list of moves identified by an id
 * @property _id  The id that contains the Plays
 * @property content  The various plays
 */
data class MovesList(var _id:String, val content:MutableList<PlayMade>)
/**
 * Initial game board format
 */
const val INITIAL_BOARD =
            "rnbqkbnr" +
            "pppppppp" +
            "        " +
            "        " +
            "        " +
            "        " +
            "PPPPPPPP" +
            "RNBQKBNR"

data class BoardState(val moves: MovesList) : BoardStateInterface {
    private val board = mutableMapOf<Positions, Piece>()
    var movesList = moves

    /**
     * Init the board putting the pieces on corresponding initial positions
     */
    init {
        var k = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val key = INITIAL_BOARD[k++]
                val whichTeam = if(key.isLowerCase()) Team.BLACK else Team.WHITE
                if (key != ' ') {
                    board[Positions(Lines.values()[i], Columns.values()[j])] = Piece(whichTeam, TypeOfPieces.valueOf(key.uppercase()), key)
                }
            }
        }
    }
    /**
     * Make the piece move, if its valid
     * @param move the move to be made
     * @return [BoardState] the game updated with the move
     */
    override fun makeMove(move: Move, team: Team): Pair<BoardState, ValueResult<*>> {
        val oldLine = move.move[2].toString().toInt() - 1
        val newline = move.move[4].toString().toInt() - 1
        val oldColumn = "C" + move.move[1].uppercaseChar()
        val newColumn = "C" + move.move[3].uppercaseChar()

        val oldPosition = Positions(Lines.values()[oldLine], Columns.valueOf(oldColumn))
        val newPosition = Positions(Lines.values()[newline], Columns.valueOf(newColumn))

        var piece = board[oldPosition] ?: return Pair(BoardState(this.movesList), ValueResult(InvalidMovement))

        val verification = movePieceVerity(piece, oldPosition, newPosition, this)
        if (verification.data == ValidMovement) {
            if (board[newPosition]?.typeOfPiece == TypeOfPieces.K){
                changePiecesPlaces(oldPosition, newPosition, piece, move)
                return Pair(BoardState(this.movesList), ValueResult(EndedGame))
            }
            if (verifyPromotion(piece, newPosition, piece.team).data == ValidMovement) {
                if (move.length() > 5 && move.move[5] == '=' && move.move[6].uppercaseChar() != 'K') {
                    piece =piece.toPromotion(move.move[6])
                } else {
                    return Pair(BoardState(this.movesList), ValueResult(NeedPromotion))
                }
            }
            changePiecesPlaces(oldPosition, newPosition, piece, move)
            return Pair(BoardState(this.movesList), ValueResult(ValidMovement))
        }else return Pair(BoardState(this.movesList), ValueResult(verification.data))
    }
    /**
     * Verify if the position contains a piece
     * @param positions position where the piece should be
     * @return if contains return true else false
     */
    override fun containsPiece(positions: Positions): Boolean {
        return board.containsKey(positions)
    }

    /**
     * Gets the piece specified
     * @param positions position where the piece should be
     * @return the piece itself
     */
    override fun getPiece(positions: Positions): Piece? {
        return board[positions]
    }
    /**
     * Overwrites the function string to transform the board in something readble
     * @return the board in form of a string
     */
    override fun toString(): String {
        var strboard = ""
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val boardPiece = board[Positions(Lines.values()[i], Columns.values()[j])]?.representation
                if (boardPiece != null) {
                    strboard += boardPiece
                } else {
                    strboard += " "
                }
            }
        }
        return strboard
    }

    /**
     * Based on the move made, and which team turn is, can see if the piece is their ones
     * @param move the move being made
     * @param teamTurn which team is making the move
     * @return [Result] if is a valid or a invalid movement
     */
    override fun pieceTeamCheck(move: Move, teamTurn: Team): ValueResult<*> {
        val line =  move.move[2].toString().toInt() - 1
        val column = "C" + move.move[1].uppercaseChar()
        val position = Positions(Lines.values()[line], Columns.valueOf(column))
        val piece = board[position] ?: return ValueResult(InvalidMovement)
        return if(teamTurn == piece.team) ValueResult(ValidMovement)
        else ValueResult(InvalidMovement)
    }

    /**
     * Change the piece place, and do the eating process by removing the other piece from the map
     * @param oldPosition the initial position of the piece
     * @param newPosition where the piece is gonna be placed
     * @param piece the piece itself
     * @param move the move made
     */
    private fun changePiecesPlaces(oldPosition: Positions, newPosition: Positions, piece: Piece, move: Move){
        board[newPosition] = piece
        board.remove(oldPosition)
        movesList.content.add(PlayMade(piece.team, move))
    }
}


/**
 * Verify if the promotion move is valid
 * @param piece the piece being moved
 * @param location the location where the piece is promoted(L1, L8)
 * @param team team who is making the play
 * @return [ValueResult] if is valid or not
 */
private fun verifyPromotion(piece: Piece, location: Positions, team: Team): ValueResult<*> {
    if(piece.representation != 'p'.uppercaseChar()) return ValueResult(InvalidCommand)
    return if (team== Team.WHITE && location.line== Lines.L8
        || team== Team.BLACK && location.line== Lines.L1
    ){
        ValueResult(ValidMovement)
    } else ValueResult(InvalidMovement)
}