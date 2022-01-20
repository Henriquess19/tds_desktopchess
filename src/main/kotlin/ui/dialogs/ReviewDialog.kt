package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.rememberDialogState
import model.domain.Team
import model.domain.storageOfBoards

@OptIn(ExperimentalComposeUiApi::class)
@Composable
/**
* Composable when the review game window is chosen
* @param onPlayIndexGiven the function when clicked to see the next or the previous play
* @param onClose the function when the window is closed
*/
fun reviewDialog(onPlayIndexGiven:(Int) -> Unit, onClose:() -> Unit) = Dialog(
   title = "Game Review",
   state = rememberDialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){
   val idx = remember { mutableStateOf(0) }

   println(storageOfBoards.size)
   Column() {
      Row(){
         Button(onClick = { if(idx.value < storageOfBoards.size-1) idx.value++ ; onPlayIndexGiven(idx.value) }){ Text("Next") }
         Button(onClick = { if (idx.value > 0) idx.value-- ; onPlayIndexGiven(idx.value)}){ Text("Previous") }
      }
   }
}