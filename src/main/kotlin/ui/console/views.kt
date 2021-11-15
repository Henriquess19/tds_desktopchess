package ui.console

import com.mongodb.client.model.changestream.UpdateDescription
import domain.*


typealias View = (input: Any?) -> Unit

fun getViews():Map<String,View>{
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
      if (success == OpenedGame) {
         println( "Game opened..")
         draw(board)
      }
      else println("Something went wrong..")
}

fun playView(input: Any?) {
   println(
      when(input) {
         ValidMovement -> draw(board)
         InvalidMovement -> "Movement Invalid.."
         InvalidCommand -> "Command Invalid.. "
         ClosedGame -> "Game not opened.."
         else -> "Something went wrong.."
      }
   )
}

fun refreshView(input: Any?) {
      if (input == UpdatedGame) {
         println( "Game updated..")
         draw(board)
      }
      else  println("Something went wrong..")
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

/**
 * Fun the receives the [Board] and draw the information
 */
fun draw(board: Board) {
   val boards= board.toString()
   println("    a b c d e f g h ")
   println("   -----------------")
   var idx = 0
   for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
      print("${i + 1} | ")
      for (k in Columns.CA.ordinal..Columns.CH.ordinal) {
         val piece = boards[idx]
         if(piece != ' ') {
            print(piece)
            print(" ")
         }
         else print("  ")
         idx++
      }
      println("| ")
   }
   println("   -----------------\n")
}

/**
 * Show the user that the game ended and close the game
 * @param team who won
 */

fun endGame(team: Team?){
   println("${team}:${ValueResult(EndGameCond)}")
   OPEN_GAME = false
}

