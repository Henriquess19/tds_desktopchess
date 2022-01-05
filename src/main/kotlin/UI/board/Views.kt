package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.domain.*

/**
 * Based on the result of the UI.board, shows a message to the mistake made, and return the UI.board
 * @param board the UI.board that is currently being played
 * @param team making the play
 * @param movement the movement tried to be made
 * @return the new UI.board and the same result
 */
@Composable
fun composingResults(board: Pair<BoardState, MoveVerity>, team: Team, movement: Move): Pair<BoardState, MoveVerity> {
    val newMovement = remember { mutableStateOf(movement) }
    var message = ""
    when(board.second.result){
        NeedPromotion ->{
            Text(text = "Need Promotion ", fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, modifier= Modifier.background(color = Color.Gray) )
            promotionView(team = board.first.getTeam(), onTileSelected = {piece: Piece ->
                newMovement.value = Move(movement.move + "=${piece.representation.toTeamRepresentation(team = team)}")
            })
            return board.first.makeMove(move = newMovement.value , team = team)
        }
        InvalidMovement -> message = "Invalid Movement "

        DifferentTeamPiece -> message = "That´s not your piece "

        Encounter -> message = "Some piece is own the way "

        SameTeam -> message = "Are you really trying to eat your own piece? "
    }
    Text(text = message, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, modifier = Modifier.background(color = Color.Gray) )
    return board
}
@Composable
fun endGameView(team: Team,) {
    Text(text = "The game ended with the victory too player $team",
        fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold,
        modifier= Modifier
            .background(color = Color.Gray)
            .size(50.dp)
    )
}