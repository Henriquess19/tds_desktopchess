/*
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.getRootCollectionsIds
import org.bson.Document

class MongoDbChess(private val db:MongoDatabase): Board() {

   override fun open(gameId: String): MongoCollection<Document>? {
      if(gameId !in db.getRootCollectionsIds()){
         db.createCollection(gameId)
      }
       return db.getCollection(gameId)
   }
}
*/