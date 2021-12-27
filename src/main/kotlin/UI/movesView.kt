package UI
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import model.domain.*

/**
 * Shows the current state of the moves, updated when its made a move
 * @param board the current state of the local board
 */
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

private var movement = ""

/**
 * Based on clicks made on canvas, prepare a move if its valid
 * @param piece tried to be moved, if the position have the piece
 * @param positions the current position of the click made
 * @return A string if the conjecture of the teo clicks its a possible move
 */
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
   movement += positions.toStr()
   if (movement.length == 11) {
      return movement
   }
   return null
}

