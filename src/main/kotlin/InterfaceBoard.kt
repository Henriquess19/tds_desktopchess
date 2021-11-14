import com.mongodb.client.MongoCollection
import org.bson.Document

/**
 *The board contract
 */
interface BoardInterface {
   /**
    * Move a piece on the board, if it is a valid movement
    * @param move the move that its suppose to be made
    * @return [Board] the board change after the movement
    */
   fun makeMove(move: Move, team: Team):Board
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
   fun getPiece(positions: Positions):Piece?
   /**
    * Return all the play made
    * @return list of all plays made
    */
   fun getMoveList():MutableList<PlayMade>

   /**
    * Based on the move made, and which team turn is, can see if the piece is their ones
    * @param move the move being made
    * @param teamTurn which team is making the move
    * @return [Result] if is a valid or a invalid movement
    */
   fun pieceTeamCheck(move: Move, teamTurn: Team): Result
}