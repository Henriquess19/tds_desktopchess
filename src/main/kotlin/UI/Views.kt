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

@Composable
fun composingResults(board: Pair<BoardState, model.domain.Result>, team: Team, movement: Move): Pair<BoardState, model.domain.Result> {
    val newMovement = remember { mutableStateOf(movement) }
    var message = ""
    when(board.second){
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