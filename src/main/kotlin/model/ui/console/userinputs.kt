package model.ui.console

import model.domain.BoardState

/**
 * Reads a line from the console and parses it to obtain the corresponding command.
 * @param  localBoardState the UI.board that is being played
 * @return a pair bearing the command text and its parameter
 */
fun readChessCommand(localBoardState: BoardState):Pair<String,String?>{
   val game = localBoardState.id
   val teamTurn = localBoardState.turn
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
