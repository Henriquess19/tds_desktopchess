import com.mongodb.client.MongoCollection
import domain.*
import domain.BoardState
import kotlin.Result

/**
 *The board contract
 */
interface BoardState {
   /**
    * Move a piece on the board, if it is a valid movement
    * @param move the move that its suppose to be made
    * @return [BoardState] the board change after the movement
    */
   fun makeMove(move: Move, team: Team): Pair<BoardState,ValueResult<*>>
   /**
    * Verificate if the position contains a piece
    * @param positions position where the piece should be
    * @return if contains return true else false
    */
   fun containsPiece(positions: Positions):Boolean
   /**
    * Gets the piece specified
    * @param line line where the piece should be
    * @param positions position where the piece should be
    * @return the piece itself
    */
   fun getPiece(positions: Positions): Piece?

   /**
    * Based on the move made, and which team turn is, can see if the piece is their ones
    * @param move the move being made
    * @param teamTurn which team is making the move
    * @return [Result] if is a valid or a invalid movement
    */
   fun pieceTeamCheck(move: Move, teamTurn: Team): ValueResult<*>
}
