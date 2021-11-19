package ui.console

import domain.Board
import domain.nextTeam

/**
 * Reads a line from the console and parses it to obtain the corresponding command.
 * @param  board the board that is being played
 * @return a pair bearing the command text and its parameter
 */
fun readChessCommand(board: Board):Pair<String,String?>{
   val game = board.gameId
   val teamTurn = nextTeam(board)
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}
/**
 * Reads the input from the console
 */
private fun readln() = readLine()!!