package UI
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.domain.*

private var moveListToDraw = mutableListOf<PlayMade>(PlayMade(team= Team.WHITE,play= Move("sjkdf")),PlayMade(team= Team.BLACK,play= Move("sjkdf")))

@Composable
@Preview
fun movesView() {
   /*var idx = 0
   val list = moveListToDraw
   Column {
      Text("----------MOVES-----------")
      while (idx != list.size && list.isNotEmpty()) {
         Column {
            val play = list[idx]
            Text("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
            idx++
         }
      }
   }*/
}