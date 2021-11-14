/**
 * Representation of the columns in a board of chess
 */
enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}
/**
 * Representation of the line in a board of chess
 */
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}

/**
 * All the the seven different pieces, for each team, that game of chess have
 */
enum class TypeOfPieces{ R,N,B,Q,K,P }

/**
 * Represent the piece itself
 * @property team which team the piece is
 * @property typeOfPiece the type of the piece
 * @property representation
 */
data class Pieces(val team: Team, val typeOfPiece: TypeOfPieces, val representation:Char)

/**
 * The two teams that are playable
 */
enum class Team{WHITE,BLACK}

/**
 * Represents the position of the piece
 * @property lines  The line where the piece is
 * @property column  The column where the piece is
 */
data class Positions(val line:Lines,val column:Columns)
/**
 * Represents the play that was made
 * @property team  The team where the piece was
 * @property play  The play itself
 */
data class PlayMade(val team:Team, val play:Move)


/**
 * Initial game board format
 */
const val INITIAL_BOARD =
      "rnbqkbnr" +
      "pppppppp" +
      "        " +
      "        " +
      "        " +
      "        " +
      "PPPPPPPP" +
      "RNBQKBNR"

const val GAME_ID = "g1"


class Board: BoardInterface {
   private val board = mutableMapOf<Positions, Pieces>()
   private val movesList = mutableMapOf<Int, PlayMade>()
   val gameId = GAME_ID //TODO -> ARRANJAR DEPOIS MANEIRA MELHOR PARA A DB
   private var numberOfPlays = 0

   /**
    * Init the board putting the pieces on corresponding initial positions
    */
   init {
      var k = 0
      for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
         for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
            val key = INITIAL_BOARD[k++]
            val whichTeam = if(key.isLowerCase()) Team.BLACK else Team.WHITE
            if (key != ' ') {
               board[Positions(Lines.values()[i], Columns.values()[j])] = Pieces(whichTeam, TypeOfPieces.valueOf(key.uppercase()), key)
            }
         }
      }
   }
   /**
    * Make the piece move, if its valid
    * @param move the move to be made
    * @return [Board] the game updated with the move
    */

   override fun makeMove(move: Move, team: Team): Board {
      val oldLine = (move.move[2].toInt() - '0'.code) - 1
      val newline = move.move[4].toString().toInt() - 1
      val oldColumn = "C" + move.move[1].uppercaseChar()
      val newColumn = "C" + move.move[3].uppercaseChar()

      val oldPosition = Positions(Lines.values()[oldLine], Columns.valueOf(oldColumn))
      val newPosition = Positions(Lines.values()[newline], Columns.valueOf(newColumn))

      val piece = board[oldPosition] ?: throw Throwable("Piece not founded in the initialposition.")

      val verification = movePieceVerity(piece, oldPosition, newPosition,this)
      if (verification == ValidMovement) {
         if (board[newPosition]?.typeOfPiece == TypeOfPieces.K )  endGame(getPiece(oldPosition)?.team)

         board[newPosition] = piece
         board.remove(oldPosition)
         movesList[numberOfPlays++] = PlayMade(piece.team, move)
      }else println(handleResult(verification))
      draw(this)
      return this
   }
   /**
    * Verify if the position contains a piece
    * @param positions position where the piece should be
    * @return if contains return true else false
    */
   override fun containsPiece(positions: Positions): Boolean {
      return board.containsKey(positions)
   }
   /**
    * Gets the piece specified
    * @param line line where the piece should be
    * @param positions position where the piece should be
    * @return the piece itself
    */
   override fun getPiece(positions: Positions): Pieces? {
      return board[positions]
   }
   /**
    * Return all the play made
    * @return list of all plays made
    */
   override fun getMoveList() : MutableList<PlayMade> {
      val list = mutableListOf<PlayMade>()
      movesList.forEach {
         list+= it.value
      }
      return list
   }


   /**
    * Overwrites the function string to transform the board in something readble
    * @return the board in form of a string
    */
   override fun toString(): String {
      var strboard = ""
      for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
         for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
            val boardPiece = board[Positions(Lines.values()[i], Columns.values()[j])]?.representation
            if (boardPiece != null) {
               strboard += boardPiece
            } else {
               strboard += " "
            }
         }
      }
      return strboard
   }

   override fun turnToPlay(move: Move, teamTurn: Team): Result {
      val oldLine = (move.move[2].toInt() - '0'.code) - 1
      val oldColumn = "C" + move.move[1].uppercaseChar()
      val oldPosition = Positions(Lines.values()[oldLine], Columns.valueOf(oldColumn))
      val piece = board[oldPosition] ?: return InvalidCommand
      return if(teamTurn == piece.team) ValidCommand
         else InvalidCommand
   }
   private fun endGame(team: Team?){
      TODO()
   }
}