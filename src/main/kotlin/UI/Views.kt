package UI

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import model.domain.*

/**
 * Based on the result of the board, shows a message to the mistake made, and return the board
 * @param board the board that is currently being played
 * @param team making the play
 * @param movement the movement tried to be made
 * @return the new board and the same result
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
                println(newMovement)
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