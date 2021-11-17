package ui.console

import domain.*

typealias View = (input:Any?) -> Unit

private var moveListToDraw:MovesList = MovesList(null, mutableListOf())

fun getViews(): Map<String,View> {
   return mapOf(
      "OPEN" to ::gameView,
      "JOIN" to ::gameView,
      "PLAY" to ::playView,
      "REFRESH" to ::refreshView,
      "MOVES" to ::movesView,
      "EXIT" to { },
   )
}

fun gameView(input:Any?) {
      if (input == OpenedGame) {
         println( "Game opened..")
      }
      else println("Something went wrong..")
}

fun playView(input:Any?) {
   println(
      when(input) {
         NeedPromotion -> "Indicate your promotion.."
         InvalidMovement -> "Movement Invalid.."
         InvalidCommand-> "Command Invalid.. "
         ClosedGame -> "Game not opened.."
         else -> "Something went wrong.."
      }
   )
}

fun refreshView(input:Any?) {
      if (input == UpdatedGame) {
         println( "Game updated..")
      }
      else println("Something went wrong..")
}

fun movesView(input: Any?) {     /** TODO **/
   if (input == MovesGame) {
      var idx = 0
     val list = moveListToDraw
      println("----------MOVES-----------")
     while (idx != list.content.size - 1 && list.content.isNotEmpty()) {
         val play = list.content[idx]
         println("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
         idx++

      }
      println("--------------------------")
   }else{
      println("Something went wrong..")
   }
}


fun gameDraw(result:ValueResult<*>,board: Board){
   val possibleResultsDraw = arrayOf(ValidMovement,OpenedGame,UpdatedGame)
   if (result.data in possibleResultsDraw ){
      draw(board.localboard)
      updatemovedraw(board)
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

fun updatemovedraw(board: Board){
   moveListToDraw = board.moveList()
}

/**
 * Show the user that the game ended and close the game
 * @param team who won
 */

fun endGame(board:BoardState,team: Team?){
   draw(board)
   println("\n${team}:You won!")
   OPEN_GAME = false
}

