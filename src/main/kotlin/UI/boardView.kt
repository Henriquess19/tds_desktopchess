package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.domain.*

@Composable

fun BoardView(board: BoardState, onTileSelected: (Piece?, coordinate: Positions) -> Unit ){
    val boards= board.toString()
    val lineThickness = 8.dp
    var idx = 0
    Column(modifier = Modifier.background(Color.Black)) {
        repeat(BOARD_SIDE){ lineIndex->
            Row{
                repeat(BOARD_SIDE){columnIndex->
                    val charOfThePiece = boards[idx]
                    val team = charOfThePiece.teamCheck()
                    val tile = charOfThePiece.toPiece(charOfThePiece.teamCheck())
                    Tile(team, piece = tile ,onSelected = {
                        onTileSelected(tile, Positions(lineIndex.toLine(), columnIndex.toColumn()))
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
