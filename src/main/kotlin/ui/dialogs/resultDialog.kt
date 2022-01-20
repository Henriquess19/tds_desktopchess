package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.rememberDialogState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
/**
*  Composable when found a error with the play made, shows a message error
*  @param onClose function when the game is closing
*/
fun resultDialog(onClose:() -> Unit) = Dialog(
   title = "Error Founded",
   state = rememberDialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){

   Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "Invalid movement, try again!", modifier = Modifier.padding(8.dp))
   }
}