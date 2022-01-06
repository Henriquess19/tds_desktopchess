package model.storage

import model.domain.MovesList
import model.domain.Position

typealias Exception = java.lang.Exception

class ChessDBAccessException(failure: Exception): Exception(failure)

/**
 *  The boardDB contract
 */
interface BoardDB {
   /**
    * Gets the all the ids created
    * @param moveslist the moveslist we want to find by id
    * @return [MovesList]the moves list present on that id
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun getGame(id: String): Pair<MovesList, MutableList<Position>>?
   /**
    * Updates the game on DB
    * @param movesList the list that we wanna put on DB
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun updateGame(id:String, movesList: MovesList,positions: MutableList<Position>): Boolean

}