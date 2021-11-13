private const val BIGGEST_MOVE_CMD = 7
private const val SMALLEST_MOVE_CMD = 2

/**
 * Represent the move to be made
 * @property move the move
 */
data class Move(val move: String){
    init {
        require(isAValidCommandMove(move))
    }
}

/**
 * Extension function that converts this string to an [Move] instance.
 * @return  the [Move] instance
 * @throws  IllegalArgumentException if this string is not a valid author identifier.
 */
fun String.toMove() = Move(this)

//fun String.toMoveOrNull() = if (isAValidCommandMove(this)) Move(this) else null
/**
 * Checks whether the given string is a valid move identifier.
 * @param   move  the string to be checked
 * @return  true if [move] can be used as an move identifier, false otherwise
 */
private fun isAValidCommandMove(move:String) = move.isNotEmpty() && (move.length in (SMALLEST_MOVE_CMD .. BIGGEST_MOVE_CMD))

data class GameId(val gameId: Int){
    init {
        require(gameId > 0)
    }
}
