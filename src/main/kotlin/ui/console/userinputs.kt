package ui.console

import domain.Board

import domain.teamTurn



fun readChessCommand(board: Board):Pair<String,String?>{
   val game = board.GAMEID
   val teamTurn = teamTurn(board.moveList(),null)
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}

private fun readln() = readLine()!!