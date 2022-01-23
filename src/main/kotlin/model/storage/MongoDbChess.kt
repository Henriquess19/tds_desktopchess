package model.storage

import com.mongodb.MongoException
import com.mongodb.client.*
import isel.leic.tds.tictactoe.storage.mongodb.getCollectionWithId
import isel.leic.tds.tictactoe.storage.mongodb.getDocument
import isel.leic.tds.tictactoe.storage.mongodb.updateDocument
import model.domain.MovesList
import model.domain.Position

private const val ON_GOING_GAMES_ROOT = "ongoing"

/**
 * Class that permit to work with the DB
 * @param MongoDatabase
 */
class MongoDbChess(private val db: MongoDatabase): BoardDB {

   /**
    * Gets the id content
    * @param moveslist the moveslist we want to find by id
    * @return [MovesList]the moves list present on that id
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override suspend fun getGame(id:String): Triple<MovesList, MutableList<Position>,Boolean>? {
      try {
         val docMoves = db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.moves
         val docPositions = db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.positions
         val docEnded = db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).getDocument(id)?.endedGame
        return if (docMoves == null || docMoves.content.isEmpty() || docPositions == null || docEnded == null) null
         else Triple(docMoves,docPositions,docEnded)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Updates the game on DB
    * @param movesList the list that we wanna put on DB
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override suspend fun updateGame(game:GameInfo): Boolean {
      try {
            return db.getCollectionWithId<GameInfo>(ON_GOING_GAMES_ROOT).updateDocument(game)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
}
/**
 * Defines the contents of documents bearing game state information
 */
data class GameInfo(val _id: String, val moves: MovesList,val positions:MutableList<Position>,val endedGame: Boolean)