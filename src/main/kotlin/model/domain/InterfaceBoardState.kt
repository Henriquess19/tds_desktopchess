
import model.domain.*
import kotlin.Result

/**
 *The UI.board contract
 */
interface BoardStateInterface {
   /**
    * Move a piece on the UI.board, if it is a valid movement
    * @param move the move that its suppose to be made
    * @return [BoardState] the UI.board change after the movement
    */
   fun makeMove(move: Move, team: model.domain.Team): Pair<BoardState, ValueResult<*>>
   /**
    * Verificate if the position contains a piece
    * @param position position where the piece should be
    * @return if contains return true else false
    */
   fun containsPiece(position: model.domain.Position):Boolean
   /**
    * Gets the piece specified
    * @param line line where the piece should be
    * @param position position where the piece should be
    * @return the piece itself
    */
   fun getPiece(position: model.domain.Position): Piece?

   /**
    * Based on the move made, and which team turn is, can see if the piece is their ones
    * @param move the move being made
    * @param teamTurn which team is making the move
    * @return [Result] if is a valid or a invalid movement
    */
   fun pieceTeamCheck(move: Move, teamTurn: model.domain.Team): ValueResult<*>

}
