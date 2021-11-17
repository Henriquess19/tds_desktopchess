package domain

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
 * Extension function that converts this string to an [Move] instance.
 * @return  the [Move] instance
 * @throws  IllegalArgumentException if this string is not a valid author identifier.
 */
fun String.toMove() = Move(this)

/**
 * Checks whether the given string is a valid move identifier.
 * @param   move  the string to be checked
 * @return  true if [move] can be used as an move identifier, false otherwise
 */
private fun isAValidCommandMove(move:String) = move.isNotEmpty() && (move.length in (SMALLEST_MOVE_CMD until  BIGGEST_MOVE_CMD))