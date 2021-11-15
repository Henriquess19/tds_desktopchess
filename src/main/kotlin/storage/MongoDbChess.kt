package storage
import Board
import ChessCommands
import com.mongodb.client.MongoDatabase
import isel.leic.tds.mongodb.getRootCollectionsIds

class MongoDbChess(private val db:MongoDatabase): ChessCommands {

   override fun open(board: Board, id:String): Iterable<String> {
      return db.getRootCollectionsIds()
   }

   override fun join(board: Board, id: String) {
      TODO("Not yet implemented")
   }

   override fun play(board: Board, move: String?) {
      TODO("Not yet implemented")
   }

   override fun refresh(board: Board) {
      TODO("Not yet implemented")
   }

   override fun moves(board: Board) {
      TODO("Not yet implemented")
   }

   override fun exit() {
      TODO("Not yet implemented")
   }
}