package model.ui.console

import model.domain.*
import model.domain.MovesList
import model.domain.ValidMovement
import model.domain.ValueResult

/**
 * A command view is merely a function that renders the command execution result.
 */
typealias View = (input:Any?) -> Unit

/**
 * Display the message if the UI.board was open or not
 */
fun gameView(input:Any?) {
    val result  = input as toReturn
    when (result.result) {
        OpenedGame -> {
            println( "Game opened..")
                draw(board = result.boardState.movesList.currentState)
            }
        InvalidCommand -> println("You have to say the number of the game..")
       else -> println("Something went wrong..")
    }
}
/**
 * Display the messages after the play was made
 */
fun playView(input:Any?) {
    val result  = input as toReturn
    when(result.result) {
      EndedGame -> println( "You won!")
      NeedPromotion -> println("Indicate your promotion..")
      InvalidMovement -> println("Movement Invalid..")
      InvalidCommand -> println("Command Invalid.. ")
      DifferentTeamPiece -> println("That´s not your piece..")
      SameTeam -> println("That´s your own piece.. ")
      ClosedGame ->{
          return println("Game not opened..")
      }
      Encounter -> println("Another piece is on the way..")
       else -> println("Something went wrong..")
    }
    draw(board = result.boardState.movesList.currentState)
}



/**
 * Display refresh messages
 */
fun refreshView(input:Any?) {
    val result  = input as toReturn
    when (result.result) {
        UpdatedGame -> println("The Game was updated..")
        InvalidMovement -> println("Wait for the other person to do her play.." )
        EndedGame -> println("You lost..")
        ClosedGame ->{
            return println("Game not opened..")
        }
          else -> println("Something went wrong..")
      }
    draw(board = result.boardState.movesList.currentState)
}
/**
 * Display the list of moves
 */
fun movesView(input: Any?) {
     val result = input as toReturn
     if (result.result == ClosedGame) return println("Game not opened..")
     if (result.result == MovesGame) {
     var idx = 0
     val list = result.boardState.movesList.content
      println("----------MOVES-----------")
     while (idx != list.size  && list.isNotEmpty()) {
         val play = list[idx]
         println("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
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

 fun draw(board: String) {
    println("    a b c d e f g h ")
    println("   -----------------")
    var idx = 0
    for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
        print("${i + 1} | ")
        for (k in Columns.CA.ordinal..Columns.CH.ordinal) {
            val piece = board[idx]
            if (piece != ' ') {
                print(piece)
                print(" ")
            } else print("  ")
            idx++
        }
        println("| ")
    }
    println("   -----------------\n")
}

