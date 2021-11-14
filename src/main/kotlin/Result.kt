/*enum class Result {
    ENCOUNTER, SAME_TEAM, VALID, INVALID
}*/

sealed interface Result
object Encounter : Result
object InvalidMovement : Result
object SameTeam : Result
object ValidMovement : Result
object EndGameCond : Result
object ValidCommand: Result
object InvalidCommand : Result
object InvalidGame : Result