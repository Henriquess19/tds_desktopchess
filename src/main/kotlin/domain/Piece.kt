/**
 * All the the seven different pieces, for each team, that game of chess have
 */
enum class TypeOfPieces{ R,N,B,Q,K,P }


/**
 * Contract of the piece
 */
interface Pieces{
    /**
     * Promotes the piece to another piece
     * @param piecePromotion the corresponding representation of the piece we want upgrade to
     * @return [Piece] the piece it self
     */
    fun toPromotion(piecePromotion:Char):Piece
}
/**
 * Represent the piece itself
 * @property team which team the piece is
 * @property typeOfPiece the type of the piece
 * @property representation the way is gonna be presented to the user
 */
data class Piece(val team: Team, val typeOfPiece: TypeOfPieces, val representation:Char):Pieces{
    /**
     * Promotes the piece to another piece
     * @param piecePromotion the corresponding representation of the piece we want upgrade to
     * @return [Piece] the piece it self
     */
    override fun toPromotion(piecePromotion: Char):Piece {
        return Piece(this.team, TypeOfPieces.valueOf(piecePromotion.toString()), piecePromotion)
    }
}

