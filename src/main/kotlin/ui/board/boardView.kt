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

/**
 * Type of pieces thats its possible pawn be promoted to
 */
val piecesThatCanBePromotedTo = arrayOf(TypeOfPieces.B,TypeOfPieces.R,TypeOfPieces.N, TypeOfPieces.Q)

/**
 * If its need a promotion, displays the various pieces that can be promoted to, in a column
 * @param team the team who is needing the promotion
 * @param onTileSelected when selected a tile, gets the piece
 */
@Composable
fun promotionView(team: Team, onTileSelected: (Piece) -> Unit) {
    val lineThickness = 8.dp
    Column(modifier = Modifier.background(Color.Black)) {
        repeat(piecesThatCanBePromotedTo.size ) { piecesIndex ->
            val pieceOnWorking = piecesThatCanBePromotedTo[piecesIndex]
            val pieceRepresentation = piecesThatCanBePromotedTo[piecesIndex].char
            val teamPiece = pieceRepresentation.toTeamRepresentation(team = team)
            val piece = Piece(team = team, typeOfPiece = pieceOnWorking, representation = teamPiece)
            PieceThatPromotesTo(team = team, piece = piece,
                onSelected = {
                    onTileSelected(piece)
                })
            Spacer(modifier = Modifier.height(lineThickness))
        }
    }
}
/**
 * Display in a box the piece that was called
 * @param team the team who is making the play
 * @param piece that´s is need to be shown
 * @param onSelected The team who made it
 */
@Composable
fun PieceThatPromotesTo(team: Team, piece: Piece,onSelected: (Team?)-> Unit = { } ){
    Box(modifier = Modifier
        .size(96.dp)
        .background(color = Color.Red)
        .clickable(true) {
            onSelected(team)
        }
        .padding(8.dp)
    ){
        val image = painterResource(chooseImage(team = team, piece = piece))
        Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp))
    }
}

