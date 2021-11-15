import ChessCommands


sealed class Result

object ExitResult : Result()

class ValueResult<T>(val data: T) : Result()

object GameNotExists

object InvalidCommand

object OpenedGame

object ClosedGame

object SameTeam

object Encounter

object ValidMovement

object InvalidMovement
