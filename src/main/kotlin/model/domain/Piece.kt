package model.domain
/**
 * All the the seven different pieces, for each team, that game of chess have
 */
enum class TypeOfPieces(val char: Char){ R('r'),
    N('n'),B('b'),Q('q'),K('k'),P('p') }

fun Char.toPiece(team: Team?):Piece? {
    if(team == null) return null
    return Piece(team = team,
        typeOfPiece = TypeOfPieces.valueOf("${this.uppercaseChar()}"),
        representation = this)
}
fun Char.teamCheck():Team?{
    if(this == ' ') return null
    return if(this.isLowerCase()) Team.BLACK
    else Team.WHITE
}


/**
 * Contract of the piece
 */
interface Pieces{
    /**
     * Promotes the piece to another piece
     * @param piecePromotion the corresponding representation of the piece we want upgrade to
     * @return [Piece] the piece it self
     */
    fun toPromotion(piecePromotion:Char): Piece
}
/**
 * Represent the piece itself
 * @property team which team the piece is
 * @property typeOfPiece the type of the piece
 * @property representation the way is gonna be presented to the user
 */
data class Piece(val team: Team, val typeOfPiece: TypeOfPieces, val representation:Char): Pieces {
    /**
     * Promotes the piece to another piece
     * @param piecePromotion the corresponding representation of the piece we want upgrade to
     * @return [Piece] the piece it self
     */
    override fun toPromotion(piecePromotion: Char): Piece {
        val pieceRepresentation = if(team == Team.WHITE) piecePromotion.uppercaseChar() else piecePromotion.lowercaseChar()
        return Piece(this.team, TypeOfPieces.valueOf(piecePromotion.uppercaseChar().toString()), pieceRepresentation)
    }
}

