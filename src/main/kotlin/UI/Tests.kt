package UI
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.domain.*

private var moveListToDraw = mutableListOf<PlayMade>(PlayMade(team= Team.WHITE,play= Move("sjkdf")),PlayMade(team= Team.BLACK,play= Move("sjkdf")))
/*
@Composable
@Preview
fun movesView(board: Board) {
   var idx = 0
   val list = board.moveList()
   Column {
      Text("----------MOVES-----------")
      while (idx != list.content.size && list.content.isNotEmpty()) {
         Column {
            val play = list.content[idx]
            Text("\tNº${idx + 1}: ${play.team} -> ${play.play.move}")
            idx++
         }
      }
   }
}

fun positionTostring(positions: Positions):String{
   val line = positions.line.ordinal+1
   val columm = positions.column.toString()[1]
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
   movement += positionTostring(positions)
   if (movement.length == 11) {
      return movement
   }
   return null
}

 */
