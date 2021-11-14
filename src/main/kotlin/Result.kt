
sealed interface Result
object Encounter : Result
object InvalidMovement : Result
object SameTeam : Result
object ValidMovement : Result
object EndGameCond : Result
object ValidCommand: Result
object InvalidCommand : Result
object InvalidGame : Result


fun handleResult(result: Result):String = when(result){
   Encounter -> "Piece in the way"
   InvalidMovement -> "Invalid Movement.."
   InvalidCommand -> "Invalid Command.."
   else -> "nada"
}