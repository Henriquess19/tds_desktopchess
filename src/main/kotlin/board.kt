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
enum class Pieces(val team:Colors){r(Colors.BLACK),n(Colors.BLACK),b(Colors.BLACK),q(Colors.BLACK),k(Colors.BLACK),p(Colors.BLACK),R(Colors.WHITE),N(Colors.WHITE),B(Colors.WHITE),Q(Colors.WHITE),K(Colors.WHITE),P(Colors.WHITE)}
/**
 * The two teams that are playable
 */
enum class Colors{WHITE,BLACK}

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
data class PlayMade(val team:Colors, val play:String)


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
   val movesList = mutableMapOf<Int, PlayMade>()
   val gameId = GAME_ID //TODO -> ARRANJAR DEPOIS MANEIRA MELHOR
   private var numberOfPlay = 0

   /**
    * Init the board putting the pieces on corresponding initial positions
    */
   init {
      var k = 0
      for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
         for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
            val key = INITIAL_BOARD[k++]
            if (key != ' ') {
               board[Positions(Lines.values()[i], Columns.values()[j])] = Pieces.valueOf(key.toString())
            }
         }
      }
   }
   /**
    * Make the piece move, if its valid
    * @param move the move to be made
    * @return [Board] the game updated with the move
    */

   override fun makeMove(move: String): Board {
      val oldLine = move[2].toString().toInt() - 1
      val newline = move[4].toString().toInt() - 1
      val oldColumn = "C" + move[1].uppercaseChar()
      val newColumn = "C" + move[3].uppercaseChar()

      val oldPosition = Positions(Lines.values()[oldLine], Columns.valueOf(oldColumn))
      val newPosition = Positions(Lines.values()[newline], Columns.valueOf(newColumn))

      val piece = board[oldPosition] ?: throw Throwable("Piece not founded in the initialposition.")

      if (moveVerity(piece, oldPosition, newPosition,board)) {
         board[newPosition] = piece
         board.remove(oldPosition)
         //TODO -> If kings dies, the other team wins, maybe add to moveverity and add result END
      }
      movesList[numberOfPlay++] = PlayMade(piece.team, move)
      return this
   }

   /**
    * Overwrites the function string to a use more adequate to the project
    */
   override fun toString(): String {
      var strboard = ""
      for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
         for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
            val boardPiece = board[Positions(Lines.values()[i], Columns.values()[j])]
            if (boardPiece != null) {
               strboard += boardPiece
            } else {
               strboard += " "
            }
         }
      }
      return strboard
   }
}