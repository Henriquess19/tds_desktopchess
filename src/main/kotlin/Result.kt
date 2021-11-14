
sealed interface Result
object ValidMovement : Result
object ValidCommand: Result
object Encounter : Result
object SameTeam : Result
object InvalidCommand : Result
object InvalidGame : Result
object InvalidMovement : Result
object EndGameCond : Result

fun handleResult(result: Result):String = when(result){
   Encounter -> "Piece in the way..\n"
   SameTeam -> "Encounter with your piece team..\n"
   InvalidCommand -> "Invalid Command..\n"
   InvalidGame -> "Game not found..\n"
   InvalidMovement -> "Invalid Movement..\n"
   EndGameCond ->"You won!!\n"
   else -> ""
}