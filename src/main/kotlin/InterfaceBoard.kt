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
   fun makeMove(move: String, teamTurn: Team):Board
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
   fun getPiece(positions: Positions):Pieces?
   /**
    * Return all the play made
    * @return list of all plays made
    */
   fun getMoveList():MutableList<PlayMade>

}