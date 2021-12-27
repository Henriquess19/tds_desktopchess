package UI
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.domain.*

@Composable
@Preview
fun movesView(board: BoardState) {
   var idx = 0
   val list = board.movesList.content
   Column {
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

fun Positions.toStrings():String{
   val line = this.line.ordinal+1
   val columm = this.column.toString()[1]
   return "$columm$line"
}

private var movement = ""
fun getmovement(piece: Piece?,positions: Positions):String?{
   if (movement.length == 11) {
      movement = ""
   }
   if(movement.isEmpty()){
      if (piece == null) return null
      movement += piece.team
      movement += ","
      movement += piece.representation
   }
   movement += positions.toStrings()
   if (movement.length == 11) {
      return movement
   }
   return null
}

