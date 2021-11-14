/**
 * Sum type to represent the result of a move
 */
sealed interface Result
/**
 * Result produced when the move execution is valid.
 */
object ValidMovement : Result
/**
 * Result produced when command is valid.
 */
object ValidCommand: Result
/**
 * Result produced when the move execution encounter a piece.
 */
object Encounter : Result
/**
 * Result produced when the move execution encounter a piece of the same team.
 */
object SameTeam : Result
/**
 * Result produced when command is invalid.
 */
object InvalidCommand : Result
/**
 * Result produced when the game open is invalid.
 */
object InvalidGame : Result
/**
 * Result produced when the move execution is invalid.
 */
object InvalidMovement : Result
/**
 * Result produced when the is encounter the end game position (one of the kings is in check)
 */
object EndGameCond : Result
object ClosedGame : Result

/**
 * Base on the result given, produce a string to warn the user what went wrong
 * @param result the result that was achieve
 * @return [String] to warn the user
 */
fun handleResult(result: Result):String = when(result){
   Encounter -> "Piece in the way..\n"
   SameTeam -> "Encounter with your piece team..\n"
   InvalidCommand -> "Invalid Command..\n"
   InvalidGame -> "Game not found..\n"
   InvalidMovement -> "Invalid Movement..\n"
   EndGameCond ->"You won!!\n"
   ClosedGame -> "Game is not opened yet..\n"
   else -> ""
}