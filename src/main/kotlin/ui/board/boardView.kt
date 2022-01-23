package ui
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.domain.*

@Composable
@Preview
/**
 * Composable used to display a chess board
 * @param board the board
 * @param theme the theme that the person wants to use
 * @param onTileSelected The function selected when the tile is selected
 */
fun BoardView(board:BoardState, theme: Theme, onTileSelected: (Piece?, coordinate: Position) -> Unit ){
    val boards= board.movesList.currentState
    val lineThickness = 8.dp
    var idx = 0
    Column(modifier = Modifier.background(Color.Black)) {
        repeat(board.side){ lineIndex->
            Row{
                repeat(board.side){columnIndex->
                    val charOfThePiece = boards[idx++]
                    val team = charOfThePiece.teamCheck()
                    val piece = charOfThePiece.toPiece(charOfThePiece.teamCheck())
                    Tile(theme= theme,team = team, piece = piece, i = lineIndex + columnIndex ,onSelected = {
                        onTileSelected(piece, Position((BOARD_SIDE-1-lineIndex).toLine(), columnIndex.toColumn()))
                    } )
                    if (columnIndex < BOARD_SIDE - 1)
                        Spacer(modifier = Modifier.width(lineThickness))
                }
            }
            if (lineIndex < BOARD_SIDE - 1)
                Spacer(modifier = Modifier.height(lineThickness))
        }
    }
}

