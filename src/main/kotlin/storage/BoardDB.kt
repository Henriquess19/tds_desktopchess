package storage

import domain.MovesList

typealias Exception = java.lang.Exception

class ChessDBAccessException(failure:Exception): Exception(failure)

/**
 *  The boardDB contract
 */
interface BoardDB {
   /**
    * Gets the all the ids created
    * @return an iterable to the string that are the ids
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun gamesIDList(): Iterable<String>
   /**
    * Gets the all the ids created
    * @param moveslist the moveslist we want to find by id
    * @return [MovesList]the moves list present on that id
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun findgamebyId(moveslist: MovesList):MovesList
   /**
    * Updates the game on DB
    * @param moveslist the list that we wanna put on DB
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun updateGame(moveslist: MovesList): Boolean
   /**
    * Creates a new id on DB(creates a new collection)
    * @param id the new collection that is suppose to be created
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun createID(id: String)
   /**
    * Creates a new document on id that is passed on moveslist
    * @param moveslist the new document that is suppose to be created
    * @throws ChessDBAccessException if something goes wrong with the DB
    */
   fun createGame(moveslist: MovesList):Boolean
}