package ui.console

import domain.*

typealias View = (input:Any?) -> Unit

private var moveListToDraw:MovesList = MovesList(null, mutableListOf())


fun gameView(input:Any?) {
      if (input == OpenedGame) {
         println( "Game opened..")
      }
      else println("Something went wrong..")
}

fun playView(input:Any?) {
   println(
      when(input) {
         ValidMovement -> ""
         EndedGame -> "You won!"
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

fun updateMoveDraw(board: Board){
   moveListToDraw = board.moveList()
}
/*
/**
 * Show the user that the game ended and close the game
 * @param team who won
 */

fun endGame(team: Team?){
   println("\n${team}:You won!")
   OPEN_GAME = false
}
 */