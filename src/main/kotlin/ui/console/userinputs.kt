package ui.console

import domain.BoardState
import getGameId
import teamTurn


fun readChessCommand(boardState:BoardState):Pair<String,String?>{
   val game = getGameId(boardState)
   val teamTurn = teamTurn(boardState.getMoveList(),null)
   print("$game:$teamTurn> ")
   val input = readln()
   val command = input.substringBefore(delimiter = ' ')
   val argument = input.substringAfter(delimiter = ' ', missingDelimiterValue = "").trim()
   return Pair(command.trim(), argument.ifBlank { null })
}

private fun readln() = readLine()!!