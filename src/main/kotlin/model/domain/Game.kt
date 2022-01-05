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
   fun start(repository: BoardDB, localTurn: Team, gameId: GameId) =
      GameStarted(repository, gameId, localTurn, Pair(BoardState(), MoveVerity()))
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
   val board: Pair<BoardState,MoveVerity>
) : Game() {

   /**
    * Checks whether it's the local player turn to play
    */
   fun isLocalPlayerTurn() = localTurn== board.first.turn

   /**
    * Makes a move, if it's the local player turn.
    * @param at    the coordinates of the play to be made
    * @return the new [GameStarted] instance
    * @throws IllegalStateException if it's not the local player turn to play
    */
   fun makeMove(move: Move,team:Team) : GameStarted {
      val newState = copy(board = board.first.makeMove(move,team))
      repository.updateGame(board.first.movesList)
      return newState
   }

   /**
    * Creates a new instance from the data published to the repository
    */
   /*fun refresh(): GameStarted {
      val gameMoves = repository.findgamebyId(id.toString())
      return if (gameMoves != null) {
         copy(board = gameMoves.currentState.to)
      } else throw UnreachableSharedGameException(NullPointerException())
   }*/
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


/**
 * Exception used to represent errors while trying to reach the shared game state
 *
 * @param cause the error's root cause
 */
class UnreachableSharedGameException(cause: Throwable) : Exception(cause)