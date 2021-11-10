import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.getCollectionWithId
import isel.leic.tds.mongodb.getRootCollectionsIds

class MongoDbChess(private val db:MongoDatabase ): Board() {

   override fun open(gameId: String): =
      db.createCollection(gameId)
         //Criar e abrir
}