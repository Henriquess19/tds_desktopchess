package model.domain

import model.storage.MongoDbChess
import model.ui.console.*

/**
 * Point where the domain and ui are combine
 * @param action the domain part, the data itself
 * @param display the representation of that data for the user
 */

data class CommandsHandler(
    val action: ChessCommands,
    val display:View
)

/**
 * Executes de the command the do draw of that
 * @param board where the data worked came from
 * @return Map that associates the strings to the command
 */

fun buildCommandsHandler(localBoard: BoardState,dbBoard: MongoDbChess): Map<String, CommandsHandler>{
    return mapOf(
        "OPEN" to CommandsHandler(action = Open(localBoard,dbBoard), display =  ::gameView),
        "JOIN" to CommandsHandler(action = Join(localBoard, dbBoard),display = ::gameView),
        "PLAY" to CommandsHandler(action = Play(localBoard,dbBoard),display = ::playView),
        "REFRESH" to CommandsHandler(action = Refresh(localBoard,dbBoard),display = ::refreshView),
        "MOVES" to CommandsHandler(action = Moves(localBoard),display = ::movesView),
        "EXIT" to CommandsHandler(action = Exit(), display = { })
    )
}

