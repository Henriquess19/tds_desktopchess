package model.storage

import com.mongodb.MongoException
import com.mongodb.client.*
import isel.leic.tds.tictactoe.storage.mongodb.getCollectionWithId
import isel.leic.tds.tictactoe.storage.mongodb.getDocument
import isel.leic.tds.tictactoe.storage.mongodb.updateDocument
import model.domain.MoveVerity
import model.domain.MovesList
import model.domain.Positions
import org.litote.kmongo.MongoOperator

private const val ON_GOING_GAMES_ROOT = "ongoing"

/**
 * Class that permit to work with the DB
 * @param MongoDatabase
 */
class MongoDbChess(private val db: MongoDatabase): BoardDB {

   /**
    * Gets the all the ids created
    * @param moveslist the moveslist we want to find by id
    * @return [MovesList]the moves list present on that id
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun getGame(id:String): Pair<MovesList, MutableList<Positions>>? {
      try {
         val docMoves = db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.moves
         val docPositions = db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.positions
        return if (docMoves == null || docMoves.content.isEmpty() || docPositions == null) null
         else Pair(docMoves,docPositions)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Updates the game on DB
    * @param movesList the list that we wanna put on DB
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun updateGame(id:String, movesList: MovesList,positions: MutableList<Positions>): Boolean {
      try {
            return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).updateDocument(GameInfo(_id = id, moves = movesList, positions = positions))
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
}
/**
 * Defines the contents of documents bearing game state information
 */
private data class GameInfo(val _id: String, val moves: MovesList,val positions:MutableList<Positions>)