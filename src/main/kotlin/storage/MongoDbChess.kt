package storage
import storage.mongodb.*
import domain.PlayMade

import com.mongodb.client.MongoDatabase
import domain.MovesList


class MongoDbChess(private val db:MongoDatabase): BoardDB {

   override fun gamesIDList(): Iterable<String> {
      return db.getRootCollectionsIds()
   }

   override fun findgamebyId(moveslist: MovesList):MovesList {
     return db.getCollectionWithId<MovesList>(moveslist._id!!.toString()).getAll().filter { it._id== moveslist._id }.first()
   }

   override fun updateGame(moveslist: MovesList): Boolean {
      if (moveslist._id==null) throw Throwable("Something went bad...")
      return db.getCollectionWithId<MovesList>(moveslist._id!!).updateDocument(moveslist)
   }



   override fun createID(id: String){
      return db.createCollection(id)
   }

   override fun createGame(moveslist: MovesList):Boolean{
      return db.getCollectionWithId<MovesList>(moveslist._id!!).createDocument(moveslist)
   }

   fun listToDB(moveslist: MutableList<PlayMade>){
      val li :MutableList<PlayMade> = mutableListOf()
      moveslist.forEach {
         li += it
      }
   }
}