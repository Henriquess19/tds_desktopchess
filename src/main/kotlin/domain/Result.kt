package domain

sealed class Result

object ExitResult : Result()

object GameNotExists

object InvalidCommand

object OpenedGame

object ClosedGame

object UpdatedGame

object EndGameCond

object SameTeam

object Encounter

object ValidMovement

object InvalidMovement


class ValueResult<T>(val data: T) : Result()