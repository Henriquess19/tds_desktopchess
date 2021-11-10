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
   fun makeMove(move:String):Board



}