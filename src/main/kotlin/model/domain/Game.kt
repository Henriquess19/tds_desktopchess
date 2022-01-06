package model.domain

import model.storage.BoardDB
import org.litote.kmongo.MongoOperator

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
   fun open(repository: BoardDB, localTurn: Team, gameId: GameId):Game{
      val game = Open(BoardState(),repository).invoke(gameId.toString())
      return if(game is ValueResult<*>){
         val gameState = game.data as toReturn
         println(localTurn)
         GameStarted(repository,gameId,localTurn,Pair(gameState.board.first,gameState.board.second))
      }
      else GameStarted(repository, gameId, localTurn, Pair(BoardState(), MoveVerity()))
   }


   fun join(repository: BoardDB, localTurn: Team, gameId: GameId):Game{
      val game = Join(BoardState(),repository).invoke(gameId.toString())
      return if(game is ValueResult<*>){
         val gameState = game.data as toReturn
         println(localTurn)
         GameStarted(repository,gameId,localTurn,Pair(gameState.board.first,gameState.board.second))
      }
      else GameStarted(repository, gameId, localTurn, Pair(BoardState(), MoveVerity()))
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
   var board: Pair<BoardState,MoveVerity>
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
   fun makeMove(move: String) : GameStarted {
      val play = Play(board.first,repository).invoke(move)
      return if(play is ValueResult<*>){
         val gameState = play.data as toReturn
         GameStarted(repository,id,localTurn,Pair(gameState.board.first,gameState.board.second))
      }
      else this
   }

   /**
    * Creates a new instance from the data published to the repository
    */
   fun refresh(): GameStarted {
      val refresh = repository.getGame(id.toString())
      return if(refresh !=null){
         GameStarted(repository,id,localTurn,Pair(BoardState(movesList = refresh.first),MoveVerity(refresh.second,ValidMovement)))
      } else this
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


/**
 * Exception used to represent errors while trying to reach the shared game state
 *
 * @param cause the error's root cause
 */
class UnreachableSharedGameException(cause: Throwable) : Exception(cause)