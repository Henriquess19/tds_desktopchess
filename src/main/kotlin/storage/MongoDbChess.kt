package storage
import com.mongodb.MongoException
import storage.mongodb.*

import com.mongodb.client.MongoDatabase
import domain.MovesList

/**
 * Class that permit to work with the DB
 * @param MongoDatabase
 */
class MongoDbChess(private val db:MongoDatabase): BoardDB {
   /**
    * Gets the all the ids created
    * @return an iterable to the string that are the ids
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun gamesIDList(): Iterable<String> {
      try{
         return db.getRootCollectionsIds()
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Gets the all the ids created
    * @param moveslist the moveslist we want to find by id
    * @return [MovesList]the moves list present on that id
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun findgamebyId(moveslist: MovesList):MovesList {
      try {
         return db.getCollectionWithId<MovesList>(moveslist._id).getAll()
            .filter { it._id == moveslist._id }.first()
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Updates the game on DB
    * @param moveslist the list that we wanna put on DB
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun updateGame(moveslist: MovesList): Boolean {
      try {
         if (moveslist._id == "-1") throw Throwable("Something went bad...")
         else
            return db.getCollectionWithId<MovesList>(moveslist._id).updateDocument(moveslist)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Creates a new id on DB(creates a new collection)
    * @param id the new collection that is suppose to be created
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun createID(id: String){
      try {
         return db.createCollection(id)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
   /**
    * Creates a new document on id that is passed on moveslist
    * @param moveslist the new document that is suppose to be created
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   override fun createGame(moveslist: MovesList):Boolean{
      try {
         return db.getCollectionWithId<MovesList>(moveslist._id).createDocument(moveslist)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
}