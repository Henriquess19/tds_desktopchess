package model.domain
/*
import ui.console.*
import javax.swing.text.View


 * Point where the domain and ui are combine
 * @param action the domain part, the data itself
 * @param display the representation of that data for the user
 */
/*
data class CommandsHandler(
    val action: ChessCommands,
    val display: View
)

/**
 * Executes de the command the do draw of that
 * @param board where the data worked came from
 * @return Map that associates the strings to the command
 */

fun buildCommandsHandler(board: Board): Map<String, CommandsHandler>{
    return mapOf(
        "OPEN" to CommandsHandler(Open(board), ::gameView),
        "JOIN" to CommandsHandler(Join(board),::gameView),
        "PLAY" to CommandsHandler(Play(board),::playView),
        "REFRESH" to CommandsHandler(Refresh(board),::refreshView),
        "MOVES" to CommandsHandler(Moves(),::movesView),
        "EXIT" to CommandsHandler(Exit(),{ }),
    )
}

 */
