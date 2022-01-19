package model.domain

import model.storage.BoardDB

/**
 * Sum type used to define the game's state.
 * @see [GameNotStarted]
 * @see [GameStarted]
 */
sealed class Game

/**
 * Singleton that represents games that have not yet been started.
 */
object GameNotStarted : Game() {

   /**
    * Starts a game. The game's plays are published to the given repository.
    *
    * @param repository    the repository to where the game's plays are to be published
    * @param localPlayer   the local player
    * @param gameId        the game identifier
    */
   suspend fun open(repository: BoardDB, localTurn: Team, gameId: GameId):Game{
      val game = Open(BoardState(),repository).invoke(gameId.toString())
      return if(game is ValueResult<*>){
         val gameState = game.data as toReturn
         GameStarted(repository,gameId,localTurn,Triple(gameState.board.first,gameState.board.second,gameState.endedGame))
      }
      else GameStarted(repository, gameId, localTurn, Triple(BoardState(id=gameId.toString()), MoveVerity(),false))
   }

   suspend fun join(repository: BoardDB, localTurn: Team, gameId: GameId):Game{
      val game = Join(BoardState(),repository).invoke(gameId.toString())
      return if(game is ValueResult<*>){
         val gameState = game.data as toReturn
         GameStarted(repository,gameId,localTurn,Triple(gameState.board.first,gameState.board.second,gameState.endedGame))
      }
      else GameStarted(repository, gameId, localTurn, Triple(BoardState(id=gameId.toString()), MoveVerity(),false))
   }
}

/**
 * Represents started games, whose state is published to the given repository.
 *
 * @param repository    the repository to where the game's state is published (and fetched from)
 * @param localPlayer   the player playing locally
 * @param board         the game board
 */

data class GameStarted(
   private val repository: BoardDB,
   private val id: GameId,
   val localTurn: Team,
   var board: Triple<BoardState, MoveVerity, Boolean>
) : Game() {

   /**
    * Checks whether it's the local player turn to play
    */
   fun isLocalPlayerTurn() =
      localTurn ==  if (board.first.movesList.content.isEmpty()) {
         Team.WHITE
      } else {
         board.first.movesList.content.last().team.other
      }


   /**
    * Makes a move, if it's the local player turn.
    * @param at the coordinates of the play to be made
    * @return the new [GameStarted] instance
    * @throws IllegalStateException if it's not the local player turn to play
    */
   suspend fun makeMove(move: String) : GameStarted {
      val play = Play(board.first,repository).invoke(move)
      return if(play is ValueResult<*>){
         val gameState = play.data as toReturn
         GameStarted(repository,id,localTurn,Triple(gameState.board.first,MoveVerity(gameState.board.second.tiles,gameState.result), gameState.endedGame))
      }
      else this
   }

   /**
    * Creates a new instance from the data published to the repository
    */
   suspend fun refresh(): GameStarted {
      val refresh = Refresh(board.first,repository).invoke()
      return if(refresh is ValueResult<*>){
         val gameState = refresh.data as toReturn
         GameStarted(repository, id, localTurn, Triple(gameState.board.first,MoveVerity(gameState.board.second.tiles,gameState.result), gameState.endedGame))
      } else this
   }

   fun updateVerity():GameStarted{
      return  GameStarted(repository,id,localTurn,Triple(board.first,MoveVerity(tiles= board.second.tiles,result= ValidMovement), board.third))
   }

   suspend fun promotionMove(move:String):GameStarted{
      return makeMove(move)
   }
}

object GameEnded :Game() {

   fun getBoardPlay(idx: Int): BoardState {
      val oi = storageOfBoards
      return oi[idx]
   }

}


/**
 * Represents game identifiers.
 * @property value the game identifier (cannot be a string comprised of only whitespace characters).
 */
data class GameId(val value: String) {
   init {
      require(isValidGameIdentifier(value))
   }

   override fun toString() = value
}

/**
 * Checks whether the given string is a valid game identifier.
 * @param   id  the string to be checked
 * @return  true if [id] can be used as game identifier, false otherwise
 */
private fun isValidGameIdentifier(id: String) = id.isNotBlank()

/**
 * Extension function that converts this string to a [GameId] instance.
 * @return  the [GameId] instance or null if this string is not a valid game identifier.
 */
fun String.toGameIdOrNull() = if (isValidGameIdentifier(this)) GameId(this) else null

