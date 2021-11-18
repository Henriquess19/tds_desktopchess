package domain

sealed class Result

object ExitResult:Result()

object GameNotExists

object InvalidCommand

object MovesGame

object OpenedGame

object ClosedGame

object UpdatedGame

object SameTeam

object Encounter

object ValidMovement

object InvalidMovement

object NeedPromotion

object EndedGame

class ValueResult<T>(val data: T) : Result()