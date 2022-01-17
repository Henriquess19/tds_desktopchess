package ui.board
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.domain.*

/**
 * Shows the current state of the moves, updated when its made a move
 * @param board the current state of the local UI.board
 */
@Composable
fun movesView(board: BoardState) {
   var idx = 0
   val list = board.movesList.content
   Column(modifier = Modifier.verticalScroll(rememberScrollState())
      .background(Color.LightGray)
      .height(824.dp)
      .border(width = 3.dp,color= Color.Black, shape = RoundedCornerShape(5.dp))

      ){
         Text("MOVES", Modifier.padding(start= 50.dp,top= 3.dp, end = 50.dp, bottom = 3.dp))
         while (idx != list.size && list.isNotEmpty()) {
            Column {
               val play = list[idx]
               Text("${play.team} -> ${play.play.move}", modifier = Modifier.padding(start=20.dp, bottom = 3.dp) )
               idx++
            }
         }
   }
}
