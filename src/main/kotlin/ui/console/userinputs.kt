package ui.console

import domain.Board
import domain.nextTeam

fun readChessCommand(board: Board):Pair<String,String?>{
   val game = board.GAMEID
   val teamTurn = nextTeam(board)
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}

private fun readln() = readLine()!!