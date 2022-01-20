package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.domain.Piece
import model.domain.Team
/**
 * The various themes created, that can be choose by the user
 */
enum class Theme(val color1:Color,val color2:Color){
    CLASSIC(Color.Black,Color.White),
    INVERSECLASSIC(Color.White,Color.Black),
    GRAY(Color.DarkGray,Color.Gray),
    PSYCHO(Color.Blue,Color.Magenta),
    MONO(Color.White,Color.White),
    ICE(Color.Blue, Color.White),
    FIRE(Color.Red,Color.Yellow),
    HARDCORE(Color.Transparent,Color.Transparent)
    }

/**
 * Based on the team, and the piece representation, calls the corresponding image
 * @param team the team who making the play
 * @param piece the piece iself
 * @return a string with the corresponding name, of the image
 */
fun chooseImage(team:Team, piece:Piece):String{
    val pieceRepresentation  = piece.representation.lowercaseChar()
    return if(team == Team.WHITE) "w_${pieceRepresentation}.png"
    else "b_${pieceRepresentation}.png"

}
/**
 * The Tile of one position in the UI.board
 * @param team the team of the corresponding position of the UI.board or null if its empty
 * @param piece the piece that´s it's represented in that position or null if its none
 * @param onSelected The team of the corresponding position or null if its empty
 */
@Composable
fun Tile(theme:Theme,team: Team?, piece:Piece?, i:Int,onSelected: (Team?)-> Unit = { } ){
    Box(modifier = Modifier
        .size(96.dp)
        .background(color = if(i %2 ==0) theme.color1 else theme.color2)
        .clickable(true) {
            onSelected(team)
        }
        .padding(8.dp)
    ){
        if(team != null && piece != null) {
            val image = painterResource(chooseImage(team = team, piece = piece))
            Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
