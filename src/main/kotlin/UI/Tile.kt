package UI

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.domain.Piece
import model.domain.Team
import model.domain.TypeOfPieces

//alterei a piece para ter o nome do tipo da peça para ser mais facil fazer esta macacada
fun chooseImage(team:Team, piece:Piece?):String{
    if(piece == null)return  " "
    return if(team == Team.WHITE) "w_${piece.typeOfPiece.type}.png"
    else "b_${piece.typeOfPiece.type}.png"

}

@Composable
fun Tile(team: Team?, piece:Piece?,onSelected: (Team?)-> Unit = { } ){
    Box(modifier = Modifier
        .size(80.dp)
        .background(MaterialTheme.colors.background)
        .clickable(true) {
            onSelected(team)
        }
        .padding(32.dp)
    ){
        team?.let {
            //val image = painterResource(chooseImage(team = it, piece = piece))
            //Image(painter = image, contentDescription = "Move")
        }
    }
}
@Composable
@Preview
fun move(){
    Tile(Team.WHITE, piece = Piece(team = Team.WHITE, typeOfPiece = TypeOfPieces.B, representation = 'b'))
}