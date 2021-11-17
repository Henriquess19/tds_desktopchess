package ui.console

import domain.*


typealias View = (input: Any?) -> Unit

fun getViews(boardState: BoardState):Map<String,View>{
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
         draw(boardState)
      }
      else println("Something went wrong..")
}

fun playView(input: Any?) {
   println(
      when(input) {
         ValidMovement -> draw(boardState)
         NeedPromotion -> "Indicate your promotion.."
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
         draw(boardState)
      }
      else  println("Something went wrong..")
}

fun movesView(input: Any?, board: Board) {
   val openedgame = input as OpenedGame
   if (input==openedgame) {
      var idx = 0
      val list = board.moveList()
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
 * Fun the receives the [BoardState] and draw the information
 */
fun draw(board: BoardState) {
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

fun endGame(board:BoardState,team: Team?){
   draw(board)
   println("\n${team}:${ValueResult(EndGameCond)}")
   OPEN_GAME = false
}

