package ui

import androidx.compose.foundation.layout.*
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
fun endGameDialog(team: Team, onClose:() -> Unit) = Dialog(
   title = "Game Ended",
   state = rememberDialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){

   Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "$team won,congrats!!", modifier = Modifier.padding(8.dp))
   }
}