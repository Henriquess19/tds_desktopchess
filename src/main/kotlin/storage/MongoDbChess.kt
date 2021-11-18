package storage
import com.mongodb.MongoException
import storage.mongodb.*
import domain.PlayMade

import com.mongodb.client.MongoDatabase
import domain.MovesList


class MongoDbChess(private val db:MongoDatabase): BoardDB {

   override fun gamesIDList(): Iterable<String> {
      try{
         return db.getRootCollectionsIds()
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }

   override fun findgamebyId(moveslist: MovesList):MovesList {
      try {
         return db.getCollectionWithId<MovesList>(moveslist._id!!.toString()).getAll()
            .filter { it._id == moveslist._id }.first()
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }

   override fun updateGame(moveslist: MovesList): Boolean {
      try {
         if (moveslist._id == null) throw Throwable("Something went bad...")
         return db.getCollectionWithId<MovesList>(moveslist._id!!).updateDocument(moveslist)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }

   override fun createID(id: String){
      try {
         return db.createCollection(id)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }

   override fun createGame(moveslist: MovesList):Boolean{
      try {
         return db.getCollectionWithId<MovesList>(moveslist._id!!).createDocument(moveslist)
      }catch (failure:MongoException){
         throw ChessDBAccessException(failure)
      }
   }
}