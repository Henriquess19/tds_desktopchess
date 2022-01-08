@file:JvmName("PromotionDialogKt")

package UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.rememberDialogState
import model.domain.Piece
import model.domain.Team
import model.domain.TypeOfPieces
import model.domain.teamCheck

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun promotionDialog(movement:String,onPieceGiven:(String) ->Unit,onClose:() -> Unit) = Dialog(
   title = "Promotion Needed",
   state = rememberDialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){

   val team = movement[0].teamCheck()!!

   Column{
      Text("Choose te piece to promote to:")
      Row{
         Box(Modifier.clickable(true) { onPieceGiven("$movement=R")})
          {
            val image = painterResource(chooseImage(team = team, piece = Piece(team = team, typeOfPiece = TypeOfPieces.R, representation = 'R')))
            Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp))
         }

         Box(Modifier.clickable(true) { onPieceGiven("$movement=B")})
         {
            val image = painterResource(chooseImage(team = team, piece = Piece(team = team, typeOfPiece = TypeOfPieces.B, representation = 'B')))
            Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp)) }

         Box (Modifier.clickable(true) { onPieceGiven("$movement=N")})
         {
            val image = painterResource(chooseImage(team = team, piece = Piece(team = team, typeOfPiece = TypeOfPieces.N, representation = 'N')))
            Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp))
         }

         Box (Modifier.clickable(true) { onPieceGiven("$movement=Q")})
         {
            val image = painterResource(chooseImage(team = team, piece = Piece(team = team, typeOfPiece = TypeOfPieces.Q, representation = 'Q')))
            Image(painter = image, contentDescription = "pieceImage", modifier = Modifier.padding(start = 8.dp))
         }
      }
   }


}