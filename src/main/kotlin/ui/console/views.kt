package ui.console

import com.mongodb.client.model.changestream.UpdateDescription
import domain.Board


typealias View = (input: Any?) -> Unit

fun getViews(): Map<String,View>{
   return mapOf(
      "OPEN" to ::gameView,
      "JOIN" to ::gameView,
      "PLAY" to ::playView,
      "REFRESH" to ::refreshView,
      "MOVES" to ::movesView,
      "EXIT" to { },
   )
}

fun gameView(input: Any?) {
   val success = input as OpenedGame
   println(
      if (success == OpenedGame) "Game opened.."
      else "Something went wrong.."
   )
}

fun playView(input: Any?) {
   val occurrence = input as /** Algo **/
   println(
      when(occurrence) {
         InvalidMovement -> "Movement Invalid.."
         InvalidCommand -> "Command Invalid.. "
         ClosedGame -> "Game not opened.."
         else -> "Something went wrong.."
      }
   )
}

fun refreshView(input: Any?) {
   val updated = input as UpdatedGame
   println(
      if (updated == UpdatedGame) "Game updated.."
      else "Something went wrong.."
   )
}
fun movesView(input: Any?,board: Board) {
   val openedgame = input as OpenedGame
   if (input==openedgame) {
      var idx = 0
      val list = board.getMoveList()
      println("----------MOVES-----------")
      while (idx != list.size - 1 && list.isNotEmpty()) {
         val play = list[idx]
         println(" Play Nº${idx + 1}: ${play.team} -> ${play.play}")
         idx++

      }
      println("--------------------------")
   }else{
      println("Something went wrong..")
   }
}
