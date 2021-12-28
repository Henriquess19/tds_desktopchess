package model.domain

/**
 * The two teams that are playable
 */
enum class Team{WHITE,BLACK;

    /**
     * The other Team to the one who call it
     */
    val other: Team
        get() = if(this ==WHITE) BLACK else WHITE
}

/**
 * Turning a string to a team
 * @return the team who is being representated
 */
fun String.toTeam():Team {
    return if(this.lowercase() == "white") Team.WHITE
    else  Team.BLACK
}
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

/**
 * Represents the list of moves identified by an id
 * @property _id  The id that contains the Plays
 * @property currentState the state of the board representaded in a string
 * @property content  The various plays
 */
data class MovesList(var _id:String = "-1", val currentState:String = INITIAL_BOARD  ,var content: MutableList<PlayMade> = mutableListOf())

/**
 * Represents the play that was made
 * @property team  The team where the piece was
 * @property play  The play itself
 */
data class PlayMade(val team: Team, val play: Move)

/**
 * @property side size of the board itself
 * @property turn who is making the play, or null when ended
 * @property board the board itself with the pieces
 * @property id the current id of the board
 * @property openBoard if the board is open or not
 */
data class BoardState internal constructor
    (val side: Int = BOARD_SIDE,
     val turn: Team? = Team.WHITE,
     private val board: MutableMap<Positions, Piece> = mutableMapOf(),
     val movesList: MovesList = MovesList(),
     var id: Int = -1,
     var openBoard: Boolean
)
{

    /**
     * Init the board putting the pieces on corresponding initial positions
     */
    init {
        var k = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            val type = movesList.currentState.ifEmpty { INITIAL_BOARD }
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val key = type[k++]
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
     * @param team the team who is making the move
     * @return The new board and what type of result, valid, invalid etc.
     */
    fun makeMove(move: Move ,team: Team): Pair<BoardState, MoveVerity> {
        val oldPosition = Positions(line = move.move[2].toLine(), column = move.move[1].toColumn())
        val newPosition = Positions(line =move.move[4].toLine(), column = move.move[3].toColumn())
        var piece = board[oldPosition] ?: return Pair(BoardState(id = this.id,openBoard = true, board = this.board, movesList = this.movesList, turn = this.turn), MoveVerity(emptyList(),InvalidMovement))
        if(piece.team != getTeam()) return Pair(BoardState(id = this.id,openBoard = true, board = this.board, movesList = this.movesList, turn = this.turn), MoveVerity(emptyList(),DifferentTeamPiece))
        val verification = movePieceVerity(piece, oldPosition, newPosition, this)
        if (verification.result == ValidMovement) {
            if (verifyPromotion(piece, newPosition, piece.team) == ValidMovement) {
                if (move.length() > 5 && move.move[5] == '=' && move.move[6].uppercaseChar() != 'K') {
                    piece =piece.toPromotion(move.move[6])
                } else {
                    return Pair(BoardState(id = this.id,openBoard = true, board = this.board, movesList = this.movesList, turn = this.turn), MoveVerity(emptyList(),NeedPromotion))
                }
            }
            val newBoard= BoardState(
                board = changePiecesPlaces(oldPosition, newPosition, piece),
                turn = team.other,
                movesList = MovesList(_id = movesList._id ,
                    currentState = this.toString(),
                    content = addANewMove(PlayMade(team = team, play= move))),
                id = id,openBoard = true
                )
            return Pair(newBoard,verification)
        }else return Pair(BoardState(id = this.id,openBoard = true, board = this.board, movesList = this.movesList, turn = this.turn), verification)
    }
    /**
     * Verify if the position contains a piece
     * @param positions position where the piece should be
     * @return if contains return true else false
     */
    fun containsPiece(positions: Positions): Boolean {
        return board.containsKey(positions)
    }

    /**
     * Add a new entry the list of plays
     * @param play the play that was made
     * @return The newlist of plays with the new entry
     */

    private fun addANewMove(play :PlayMade):MutableList<PlayMade>{
        val newList = movesList.content
        newList.add(play)
        return newList
    }

    fun verifyCheck(): MutableMap<Piece, MoveVerity> {
        val piecesChecking = mutableMapOf<Piece, MoveVerity>()
        val king = turn?.let { 'K'.toTeamRepresentation(it).toPiece(it) }
        val kingPosition = board.filter{ it.value == king }.keys.first()
        val oppositionpieces = board.filterValues { it.team == turn?.other }
        oppositionpieces.forEach {
            val validcheck = movePieceVerity(it.value,it.key,kingPosition,this)
            if(validcheck.result == ValidMovement) {
                piecesChecking[it.value] = validcheck
            }
        }
        return piecesChecking
    }

    fun verifyCheckmate(piecesChecking:MutableMap<Piece, MoveVerity>): MutableMap<Piece, MoveVerity> /** PiecePosition to Position **/{
        val validMovements = mutableMapOf<Piece, MoveVerity>()
        /** Verify if king can move */



        /** Verify if can take the attacker or block check  TODO("(Verify too if you move that piece the king continues check)") **/

        val treatingpiecetiles = piecesChecking.values
        val teampieces = board.filterValues { it.team == turn }

        treatingpiecetiles.forEach { moveverity ->
            moveverity.tiles.forEach { position ->
               teampieces.forEach { piece ->
                   val killTreat = movePieceVerity(piece.value,piece.key,position,this)
                   if(killTreat.result == ValidMovement) {
                       validMovements[piece.value] = MoveVerity(position,ValidMovement)
                   }
               }
           }
        }

        return validMovements

    }
    /**
     * Gets the piece specified
     * @param positions position where the piece should be
     * @return the piece itself
     */
    fun getPiece(positions: Positions): Piece? {
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
     * From the last entry on the move list catches the team who made it
     * @return the team who made the move
     */
    fun getTeam():Team{
        val content = movesList.content
        return if(content.isEmpty()) Team.WHITE else content.last().team.other
    }

    /**
     * From the string cathes the piece thats its being move, or null if its empty space
     * @param move the move itself
     * @return The piece itself or null if its empty
     */
    fun getPiece(move:String):Piece?{
        val oldColumn = move[0].toColumn()
        val oldLine = move[1].toLine()
        val position = Positions(line = oldLine, column = oldColumn)
        return getPiece(positions = position)
    }

    /**
     * Change the piece place, and do the eating process by removing the other piece from the map
     * @param oldPosition the initial position of the piece
     * @param newPosition where the piece is gonna be placed
     * @param piece the piece itself
     * @param move the move made
     */
    private fun changePiecesPlaces(oldPosition: Positions, newPosition: Positions, piece: Piece): MutableMap<Positions, Piece>{
        val newBoard:MutableMap<Positions, Piece> =  board
        newBoard[newPosition] = piece
        newBoard.remove(oldPosition)
        return newBoard
    }
}
/**
 * Verify if the promotion move is valid
 * @param piece the piece being moved
 * @param location the location where the piece is promoted(L1, L8)
 * @param team team who is making the play
 * @return [Result] if is valid or not
 */
private fun verifyPromotion(piece: Piece, location: Positions, team: Team): Result {
    if(piece.representation.uppercaseChar() != 'P') return InvalidCommand
    return if (team== Team.WHITE && location.line== Lines.L8
        || team== Team.BLACK && location.line== Lines.L1
    ){
        ValidMovement
    } else InvalidMovement
}
