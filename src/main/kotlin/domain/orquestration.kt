package domain

import ui.console.*

data class CommandsHandler(
    val action: ChessCommands,
    val display: View
)


fun buildCommandsHandler(board: Board, ): Map<String, CommandsHandler>{
    return mapOf(
        "OPEN" to CommandsHandler(Open(board), ::gameView),
        "JOIN" to CommandsHandler(Join(board),::gameView),
        "PLAY" to CommandsHandler(Play(board),::playView),
        "REFRESH" to CommandsHandler(Refresh(board),::refreshView),
        "MOVES" to CommandsHandler(Moves(),::movesView),
        "EXIT" to CommandsHandler(Exit(),{ }),
    )
}