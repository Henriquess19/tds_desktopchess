package ui.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.rememberDialogState
import model.domain.Team

@OptIn(ExperimentalComposeUiApi::class)
@Composable
/**
* Composable to show the window where the user is going to choose the gameId he wants to play
*  @param team the team who won
 *  @param onReview the function called when review is wanted
*  @param onClose the function when closing the window
*/
fun endGameDialog(team: Team, onReview:()-> Unit,onClose:() -> Unit) = Dialog(
   title = "Game Ended",
   state = rememberDialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){

   Column(modifier = Modifier.padding(16.dp)) {
      Row{Text(text = "$team won,congrats!!", modifier = Modifier.padding(8.dp))}
      Row(modifier = Modifier.padding(16.dp)){
         Button(onClick = {onReview()}){ Text("Review") }
         Button(onClick = {onClose()}){ Text("Close") }
      }
   }
}