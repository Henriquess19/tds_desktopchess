package storage

import domain.MovesList

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