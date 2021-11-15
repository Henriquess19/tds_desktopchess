package ui.console

import domain.Board
import getGameId
import teamTurn


fun readChessCommand(board:Board):Pair<String,String?>{
   val game = getGameId(board)
   val teamTurn = teamTurn(board.getMoveList(),null)
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}

private fun readln() = readLine()!!