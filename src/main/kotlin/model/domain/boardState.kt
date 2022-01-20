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
 * Initial game UI.board format
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
 * @property currentState the state of the UI.board representaded in a string
 * @property content  The various plays
 */
data class MovesList(var currentState:String = INITIAL_BOARD  ,var content: MutableList<PlayMade> = mutableListOf())

/**
 * Represents the play that was made
 * @property team  The team where the piece was
 * @property play  The play itself
 */
data class PlayMade(val team: Team, val play: Move)

/**
 * @property side size of the UI.board itself
 * @property turn who is making the play, or null when ended
 * @property board the UI.board itself with the pieces
 * @property id the current id of the UI.board
 */
data class BoardState internal constructor(
    val side: Int = BOARD_SIDE,
    val turn: Team? = Team.WHITE,
    private val board: MutableMap<Position, Piece> = mutableMapOf(),
    val movesList: MovesList = MovesList(),
    var id: String = "",
)
{

    /**
     * Init the UI.board putting the pieces on corresponding initial positions
     */
    init {
        var k = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            val type = movesList.currentState.ifEmpty { INITIAL_BOARD }
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val key = type[k++]
                val whichTeam = if(key.isLowerCase()) Team.BLACK else Team.WHITE
                if (key != ' ') {
                    board[Position(Lines.values()[i], Columns.values()[j])] = Piece(whichTeam, TypeOfPieces.valueOf(key.uppercase()), key)
                }
            }
        }
    }
    /**
     * Make the piece move, if its valid
     * @param move the move to be made
     * @param team the team who is making the move
     * @return The new UI.board and what type of result, valid, invalid etc.
     */
    fun makeMove(move: Move ,team: Team): Pair<BoardState, MoveVerity> {
        val oldPosition = Position(line = move.move[2].toLine(), column = move.move[1].toColumn())
        val newPosition = Position(line =move.move[4].toLine(), column = move.move[3].toColumn())
        var piece = board[oldPosition] ?: return Pair(BoardState(id = id, board = board, movesList = movesList, turn = turn), MoveVerity(mutableListOf(),InvalidMovement))
        if(piece.team != getTeam()) return Pair(BoardState(id = id, board = board, movesList = movesList, turn = turn), MoveVerity(mutableListOf(),DifferentTeamPiece))
        val verification = movePieceVerity(piece, oldPosition, newPosition, this)
        if (verification.result == ValidMovement) {
            if (verifyPromotion(piece, newPosition, piece.team) == ValidMovement) {
                if (move.length() > 5 && move.move[5] == '=' && move.move[6].uppercaseChar() != 'K') {
                    piece = piece.toPromotion(move.move[6])
                } else {
                    return Pair(BoardState(id = id, board = board, movesList = movesList, turn = turn), MoveVerity(mutableListOf(),NeedPromotion))
                }
            }
            val change = changePiecesPlaces(oldPosition, newPosition, piece)
            val newBoard= BoardState(
                board = change ,
                turn = team.other,
                movesList = MovesList(
                    currentState = change.mapToString(),
                    content = addANewMove(PlayMade(team = team, play= move))),
                id = id
            )
            return Pair(newBoard,verification)
        }else return Pair(BoardState(id = id, board = board, movesList = movesList, turn = turn), verification)
    }
    /**
     * Verify if the position contains a piece
     * @param position position where the piece should be
     * @return if contains return true else false
     */
    fun containsPiece(position: Position): Boolean {
        return board.containsKey(position)
    }

    /**
     * Add a new entry the list of plays
     * @param play the play that was made
     * @return The newlist of plays with the new entry
     */

    private fun addANewMove(play :PlayMade):MutableList<PlayMade>{
        val newList = movesList.content.toMutableList()
        newList.add(play)
        return newList
    }
    /**
     * Verify if the king of the team is in check and see what pieces are
     * @param team who´s kings can be in check
     * @return a map of the pieces who are putting in check [Location] and the moves that can be made to avoid that [MoveVerity]
     */
    fun verifyCheck(team:Team): MutableMap<Location, MoveVerity> {
        val piecesChecking = mutableMapOf<Location, MoveVerity>()
        val king = 'K'.toTeamRepresentation(team).toPiece(team)
        val kingPosition = board.filter{ it.value == king }.keys.first()
        val oppositionpieces = board.filterValues { it.team == team.other }
        oppositionpieces.forEach {
            val validcheck = movePieceVerity(it.value,it.key,kingPosition,this)
            if(validcheck.result == ValidMovement) {
                piecesChecking[Location(it.value,it.key)] = validcheck
            }
        }
        return piecesChecking
    }
    /**
     * Receives the pieces putting in check, and see if its possible to still save the king
     * @param piecesChecking the pieces who are putting the king in check
     * @return A map with the possibles ways, if there are any, that the king can be saved
     */
    fun verifyCheckmate(piecesChecking:MutableMap<Location, MoveVerity>): MutableMap<Location, MoveVerity>{
        val possibleMovements = mutableMapOf<Location, MoveVerity>()
        val validMovements = mutableMapOf<Location, MoveVerity>()

        val treatingpiecetiles = piecesChecking.values
        val teampieces = board.filterValues { it.team == getTeam() }

        teampieces.forEach { piece ->
            val tiles = mutableListOf<Position>()
            treatingpiecetiles.forEach { moveverity ->

                /** Verify king moves */
                if (piece.value.typeOfPiece == TypeOfPieces.K) {
                    getAllKingMovements(piece.key).forEach { position ->
                        if (position !in moveverity.tiles) {
                            val getAway = movePieceVerity(piece.value, piece.key, position, this)
                            if (getAway.result == ValidMovement) {
                                tiles.add(position)
                            }
                        }
                    }
                }

                /** Verify other pieces moves */
                else{
                    moveverity.tiles.forEach { position ->
                        val killTreat = movePieceVerity(piece.value, piece.key, position, this)
                        if (killTreat.result == ValidMovement) {
                            tiles.add(position)
                        }
                    }
                }
            }
            if (tiles.isNotEmpty()) possibleMovements[Location(piece.value,piece.key)] = MoveVerity(tiles,ValidMovement)
        }


        possibleMovements.forEach { init ->
            val pieceType = init.key.piece.typeOfPiece.char
            val initpos = init.key.position.toStr()
            val team = init.key.piece.team
            val possiblePositions = mutableListOf<Position>()
            init.value.tiles.forEach { end ->
                val endpos = end.toStr()
                val move = pieceType + initpos + endpos
                val board = BoardState(id = id, board = board, movesList = movesList, turn = turn)
                if(stillValidMove(Move(move),team,board) == ValidMovement){
                    possiblePositions.add(end)
                }
            }
            if (possiblePositions.isNotEmpty()) validMovements[init.key] = MoveVerity(possiblePositions,ValidMovement)
        }
        return validMovements
    }

    /**
     * Receives the king position and returns all the possible moves for the king.
     * @param position value position of the king
     * @return List of positions that the king can go to
     */
    private fun getAllKingMovements(position:Position):List<Position>{
        val validPositions = mutableListOf<Position>()

        if (position.line != Lines.L8){
            validPositions.add(Position((position.line.ordinal +1).toLine(),position.column))
            if (position.column != Columns.CA){
                validPositions.add(Position((position.line.ordinal +1).toLine(),(position.column.ordinal -1).toColumn()))
            }
            if (position.column != Columns.CH){
                validPositions.add(Position((position.line.ordinal +1).toLine(),(position.column.ordinal +1).toColumn()))
            }
        }

        if (position.column != Columns.CA){
            validPositions.add(Position(position.line,(position.column.ordinal +1).toColumn()))
        }

        if (position.column != Columns.CH){
            validPositions.add(Position(position.line,(position.column.ordinal -1).toColumn()))
        }

        if (position.line != Lines.L1){
            validPositions.add(Position((position.line.ordinal -1).toLine(),position.column))
            if (position.column != Columns.CA){
                validPositions.add(Position((position.line.ordinal -1).toLine(),(position.column.ordinal -1).toColumn()))
            }
            if (position.column != Columns.CH){
                validPositions.add(Position((position.line.ordinal -1).toLine(),(position.column.ordinal +1).toColumn()))
            }
        }

        return validPositions
    }


    /**
     * Gets the piece specified
     * @param position position where the piece should be
     * @return the piece itself
     */
    fun getPiece(position: Position): Piece? {
        return board[position]
    }
    /**
     * Overwrites the function string to transform the UI.board in something readble
     * @return the UI.board in form of a string
     */
    override fun toString(): String {
        var strboard = ""
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val boardPiece = board[Position(Lines.values()[i], Columns.values()[j])]?.representation
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
     * Change the piece place, and do the eating process by removing the other piece from the map
     * @param oldPosition the initial position of the piece
     * @param newPosition where the piece is gonna be placed
     * @param piece the piece itself
     * @param move the move made
     */
    private fun changePiecesPlaces(oldPosition: Position, newPosition: Position, piece: Piece): MutableMap<Position, Piece>{
        val newBoard:MutableMap<Position, Piece> =  board.toMutableMap()
        newBoard[newPosition] = piece
        newBoard.remove(oldPosition)
        return newBoard
    }
}

/**
 * Fake make move, to check if after the play made, its still in check
 * @param move to be made
 * @param team who is making the play
 * @return if its valid or not
 */
fun stillValidMove(move: Move, team: Team,board:BoardState):Result{
    val new = board.makeMove(move,team)
    return if (new.first.verifyCheck(team).isEmpty()) ValidMovement
    else InvalidMovement
}
/**
 * To compute all the possibles moves that can be made
 * @param checks the pieces and their location and the various moves that are computed
 * @return a list of several moves
 */
fun checkconditionsToMove(checks:MutableMap<Location, MoveVerity>):List<Move>{
    val possibleMoves = mutableListOf<Move>()
    checks.forEach {
        val piece = it.key.piece.representation
        val initPos = it.key.position.toStr()
        it.value.tiles.forEach {
            val endpos = it.toStr()
            val move = piece + initPos + endpos
            possibleMoves.add(Move(move))
        }
    }
    return possibleMoves
}




/**
 * Verify if the promotion move is valid
 * @param piece the piece being moved
 * @param location the location where the piece is promoted(L1, L8)
 * @param team team who is making the play
 * @return [Result] if is valid or not
 */
private fun verifyPromotion(piece: Piece, location: Position, team: Team): Result {
    if(piece.representation.uppercaseChar() != 'P') return InvalidCommand
    return if (team== Team.WHITE && location.line== Lines.L8
        || team== Team.BLACK && location.line== Lines.L1
    ){
        ValidMovement
    } else InvalidMovement
}
/**
 * Transform a map of pieces and location to a string
 * @return the new string with the state of the board
 */
fun MutableMap<Position, Piece>.mapToString(): String {
    var strboard = ""
    for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
        for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
            val boardPiece = this[Position(Lines.values()[i], Columns.values()[j])]?.representation
            if (boardPiece != null) {
                strboard += boardPiece
            } else {
                strboard += " "
            }
        }
    }
    return strboard
}