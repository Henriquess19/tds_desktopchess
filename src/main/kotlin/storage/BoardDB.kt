package storage

import domain.MovesList

typealias Exception = java.lang.Exception

class ChessDBAccessException(failure:Exception): Exception(failure)

/**
 *  The boardDB contract
 */
interface BoardDB {
   fun gamesIDList(): Iterable<String>

   fun findgamebyId(moveslist: MovesList):MovesList

   fun updateGame(moveslist: MovesList):Boolean

   fun createID(id: String)

   fun createGame(moveslist: MovesList):Boolean
}