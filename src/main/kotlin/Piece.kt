/**
 * All the the seven different pieces, for each team, that game of chess have
 */
enum class TypeOfPieces{ R,N,B,Q,K,P }

/**
 * Represent the piece itself
 * @property team which team the piece is
 * @property typeOfPiece the type of the piece
 * @property representation the way is gonna be presented to the user
 */
interface Pieces{
    fun toPromotion(piecePromotion:Char):Piece
}
data class Piece(val team: Team, val typeOfPiece: TypeOfPieces, val representation:Char):Pieces{
    override fun toPromotion(piecePromotion: Char):Piece {
        return Piece(this.team, TypeOfPieces.valueOf(piecePromotion.toString()), piecePromotion)
    }
}

