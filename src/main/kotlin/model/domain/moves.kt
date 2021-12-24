package model.domain

/**
 * The biggest command size possible
 */
private const val BIGGEST_MOVE_CMD = 7
/**
 * The smallest command size possible
 */
private const val SMALLEST_MOVE_CMD = 2

/**
 * Represent the move to be made
 * @property move the move
 */
data class Move(val move: String){
    init {
        require(isAValidCommandMove(move))
    }

    /**
     * The size of the string
     * @return the integer correspondent to the string size
     */
    fun length() = move.length
}

/**
 * Checks whether the given string is a valid move identifier.
 * @param   move  the string to be checked
 * @return  true if [move] can be used as an move identifier, false otherwise
 */
private fun isAValidCommandMove(move:String) = move.isNotEmpty() && (move.length in (SMALLEST_MOVE_CMD ..  BIGGEST_MOVE_CMD))

/**
 * If the string dont have the piece it will add a P, to function without problems
 * @param move the move to be made not completed
 * @return the string prepared
 */
fun stringPrepared(move:String,piece: Piece?, team: Team):String {
    if(move.length == 5 || move.length == 7) return move
    if(piece == null){
        return "D$move"
    }else {
        val representation = if(team == Team.WHITE) piece.representation.uppercase() else piece.representation.lowercase()
            return "${representation}$move"
    }
}