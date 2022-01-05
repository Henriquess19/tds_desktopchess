package UI
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import model.domain.*

/**
 * Shows the current state of the moves, updated when its made a move
 * @param board the current state of the local UI.board
 */
@Composable
fun movesView(board: BoardState) {
   var idx = 0
   val list = board.movesList.content
   Column(modifier = Modifier.verticalScroll(rememberScrollState())){
      Text("----------MOVES-----------")
      while (idx != list.size && list.isNotEmpty()) {
         Column {
            val play = list[idx]
            Text("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
            idx++
         }
      }
   }
}
