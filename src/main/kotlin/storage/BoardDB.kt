package storage

import domain.PlayMade

/**
 *  The boardDB contract
 */

interface BoardDB {
   fun gamesIDList(): Iterable<String>

   fun findgamebyId(id: String): List<PlayMade>

   fun updateGame(id: String, moveslist: MutableList<PlayMade>):Boolean
}