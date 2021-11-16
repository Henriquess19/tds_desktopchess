package storage

import domain.PlayMade

import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.createDocument
import isel.leic.tds.mongodb.getCollectionWithId
import isel.leic.tds.mongodb.getRootCollectionsIds

class MongoDbChess(private val db:MongoDatabase): BoardDB {

   override fun gamesIDList(): Iterable<String> {
      return db.getRootCollectionsIds()
   }

   override fun findgamebyId(id: String):List<PlayMade> {
     return db.getCollectionWithId<List<PlayMade>>(id)
   }


   override fun updateGame(id: String, moveslist: MutableList<PlayMade>): Boolean {
      return db.createDocument(id, moveslist)
   }
}