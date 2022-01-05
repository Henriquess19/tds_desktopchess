package UI

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogState
import androidx.compose.ui.window.WindowSize



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun getGameID(onGameIdGiven:(/*GameId*/) -> Unit, onClose:() -> Unit) = Dialog(
   title = "Game ID",
   state = DialogState(size = WindowSize(Dp.Unspecified, Dp.Unspecified)),
   resizable = false,
   onCloseRequest = onClose
){
   val userInput = remember {mutableStateOf("")}
   val currentInput = userInput.value

   val gameIdGiven = {
      val gameId = currentInput
      if(gameId == null){userInput.value = ""}
      else {onGameIdGiven(/*gameId*/)}
   }

   Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "Please enter the game id:", modifier = Modifier.padding(8.dp))
      Row {
         TextField(
            modifier = Modifier
               .onKeyEvent { event ->
                  if (event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                     gameIdGiven()
                  }
                  true
               },
            value = currentInput,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFEFEFEF)),
            onValueChange = { userInput.value = it }
         )
         Spacer(modifier = Modifier.width(16.dp))
         Button(onClick = gameIdGiven) {
            Text(text = "OK")
         }
      }
   }
}