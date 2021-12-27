package model.domain
/**
 * Sum type used to represent the execution result of the existing commands
 */
sealed class Result
/**
 * Result produced when the command execution determines that the application should terminate.
 */
object ExitResult: Result()

class ValueResult<T>(val data: T) : Result()
/**
 * Result when the game don´t exists
 */
object GameNotExists: Result()
/**
 * Result when the game need the moves
 */
object MovesGame: Result()
/**
 * Result when the game is opened
 */
object OpenedGame: Result()
/**
 * Result when the game is closed
 */
object ClosedGame: Result()

/**
 * Result when the game is updated
 */
object UpdatedGame: Result()
/**
 * Result produced when the game open is invalid.
 */
object InvalidCommand: Result()
/**
 * Result produced when the move execution is valid.
 */
object ValidMovement: Result()
/**
 * Result produced when the game open is invalid.
 */
object InvalidMovement: Result()
/**
 * Result produced when the pieces from the same team
 */
object SameTeam: Result()
/**
 * Result produced when the move execution encounter a piece of the same team.
 */
object Encounter: Result()
/**
 * Result produced when the piece needs promotion
 */
object NeedPromotion: Result()
/**
 * Result produced when the is encounter the end game position (one of the kings dies)
 */
object EndedGame: Result()
/**
 * Result produced when the piece doesnt correspond to the team
 */
object DifferentTeamPiece:Result()