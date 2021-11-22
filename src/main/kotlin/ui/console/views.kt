package ui.console

import domain.*
/**
 * A command view is merely a function that renders the command execution result.
 */
typealias View = (input:Any?) -> Unit

/**
 * The list to be drawn
 */
private var moveListToDraw:MovesList = MovesList("-1", mutableListOf())

/**
 * Display the message if the board was open or not
 */
fun gameView(input:Any?) {
      if (input == OpenedGame) {
         println( "Game opened..")
      }
      else println("Something went wrong..")
}
/**
 * Display the messages after the play was made
 */
fun playView(input:Any?) {
   println(
      when(input) {
         ValidMovement -> ""
         EndedGame -> "You won!"
         NeedPromotion -> "Indicate your promotion.."
         InvalidMovement -> "Movement Invalid.."
         InvalidCommand-> "Command Invalid.. "
         SameTeam-> "That´s your own piece.. "
         ClosedGame -> "Game not opened.."
         Encounter -> "Another piece is on the way.."
         else -> "Something went wrong.."
      }
   )
}
/**
 * Display refresh messages
 */
fun refreshView(input:Any?) {
      when (input) {
          UpdatedGame -> println("Game updated..")
          EndedGame -> println("You lost")
          else -> println("Something went wrong..")
      }
}
/**
 * Display the list of moves
 */
fun movesView(input: Any?) {
   if (input == MovesGame) {
      var idx = 0
     val list = moveListToDraw
      println("----------MOVES-----------")
     while (idx != list.content.size  && list.content.isNotEmpty()) {
         val play = list.content[idx]
         println("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
         idx++

      }
      println("--------------------------")
   }else{
      println("Something went wrong..")
   }
}
/**
 * Verify if is possible to draw and then call the functions to do it
 * @param result the result of the command
 * @param board the board it self
 */
fun gameDraw(result:ValueResult<*>,board: Board){
   val possibleResultsDraw = arrayOf(ValidMovement,OpenedGame,UpdatedGame,EndedGame)
   if (result.data in possibleResultsDraw ){
      draw(board.localBoard)
      updateMoveDraw(board)
   }
}

/**
 * Fun the receives the [BoardState] and draw the information
 */
private fun draw(board: BoardState) {
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
 * Update the move loist  to be drawn
 * @param board the board itself
 */
private fun updateMoveDraw(board: Board){
   moveListToDraw = board.moveList()
}
