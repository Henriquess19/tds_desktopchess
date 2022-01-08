package model.domain

import kotlin.Result

/**
 * The biggest command size possible
 */
private const val BIGGEST_MOVE_CMD = 7
/**
 * The smallest command size possible
 */
private const val SMALLEST_MOVE_CMD = 2


val movesErrorsResults = arrayOf<ValueResult<*>>(ValueResult(InvalidMovement),ValueResult(Encounter),ValueResult(SameTeam),ValueResult(NeedPromotion))

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

private var movement = ""

/**
 * Based on clicks made on canvas, prepare a move if its valid
 * @param piece tried to be moved, if the position have the piece
 * @param position the current position of the click made
 * @return A string if the conjecture of the teo clicks its a possible move
 */
fun getmovement(piece: Piece?, position: Position):String?{
    if (movement.length == 11) {
        movement = ""
    }
    if(movement.isEmpty()){
        if (piece == null) return null
        movement += piece.team
        movement += ","
        movement += piece.representation
    }
    movement += position.toStr()
    if (movement.length == 11) {
        return movement
    }
    return null
}