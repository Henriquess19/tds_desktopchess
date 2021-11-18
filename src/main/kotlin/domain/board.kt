package domain

import BoardStateInterface
import movePieceVerity
import storage.MongoDbChess
import ui.console.draw
import ui.console.endGame

/**
 * Representation of the columns in a board of chess
 */
enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}
/**
 * Representation of the line in a board of chess
 */
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}

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

data class MovesList(var _id:String?, val content:MutableList<PlayMade>)


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

data class BoardState(val moves: MovesList) : BoardStateInterface {
   private val board = mutableMapOf<Positions, Piece>()

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
               board[Positions(Lines.values()[i], Columns.values()[j])] = Piece(whichTeam, TypeOfPieces.valueOf(key.uppercase()), key)
            }
         }
      }
   }
   /**
    * Make the piece move, if its valid
    * @param move the move to be made
    * @return [BoardState] the game updated with the move
    */

   override fun makeMove(move: Move, team: Team): Pair<domain.BoardState,ValueResult<*>> {
      val oldLine = move.move[2].toString().toInt() - 1
      val newline = move.move[4].toString().toInt() - 1
      val oldColumn = "C" + move.move[1].uppercaseChar()
      val newColumn = "C" + move.move[3].uppercaseChar()

      val oldPosition = Positions(Lines.values()[oldLine], Columns.valueOf(oldColumn))
      val newPosition = Positions(Lines.values()[newline], Columns.valueOf(newColumn))

      val piece = board[oldPosition] ?: return Pair(this,ValueResult(InvalidMovement))

      val verification = movePieceVerity(piece, oldPosition, newPosition,this)
      if (verification.data == ValidMovement) {

         if (board[newPosition]?.typeOfPiece == TypeOfPieces.K )  endGame(getPiece(oldPosition)?.team) /** TODO(Wrong placement for function calling) **/

         if (piece.typeOfPiece == TypeOfPieces.P
               && verifypromocion(newPosition,piece.team).data == ValidMovement){
            if(move.length() > 5 && move.move[5]== '='){
               if (move.move[6].uppercaseChar() != 'K'){
                  piece.toPromotion(move.move[6])
               }else{
                  return Pair(this,ValueResult(NeedPromotion))
               }
            }
         }

         board[newPosition] = piece
         board.remove(oldPosition)
         movesList.content.add(PlayMade(piece.team, move))
      }else return Pair(this,ValueResult(verification))

      return Pair(this,ValueResult(ValidMovement))
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
   override fun getPiece(positions: Positions): Piece? {
      return board[positions]
   }
   override fun refreshmovesList(moves: MovesList): MovesList {
      return moves
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

   /**
    * Based on the move made, and which team turn is, can see if the piece is their ones
    * @param move the move being made
    * @param teamTurn which team is making the move
    * @return [Result] if is a valid or a invalid movement
    */
   override fun pieceTeamCheck(move: Move, teamTurn: Team): ValueResult<*>{
      val line = (move.move[2].toInt() - '0'.code) - 1
      val column = "C" + move.move[1].uppercaseChar()
      val position = Positions(Lines.values()[line], Columns.valueOf(column))
      val piece = board[position] ?: return ValueResult(InvalidMovement)
      return if(teamTurn == piece.team) ValueResult(ValidMovement)
      else ValueResult(InvalidMovement)
   }


   fun verifypromocion(location: Positions,team: Team):ValueResult<*>{
      return if (team==Team.WHITE && location.line==Lines.L8
         || team==Team.BLACK && location.line==Lines.L1){
         ValueResult(ValidMovement)
      } else ValueResult(InvalidMovement)
   }
}

data class Board(var localboard:BoardState, val dbboard:MongoDbChess){

   var GAMEID: String? = null;

   fun playMove(move: Move, team: Team):ValueResult<*>{
      val play = localboard.makeMove(move, team)
      if(play.second.data==ValidMovement) {
         updateMoves()
         return ValueResult(ValidMovement)
      }else{
         return ValueResult(InvalidCommand)
      }
   }

   fun moveList(): MovesList{
      return localboard.movesList
   }

   private fun dbmoveList(moves: MovesList): MovesList {
      val list = mutableListOf<PlayMade>()
      dbboard.findgamebyId(moves).content.forEach {list += it}
      return MovesList(moves._id,list)
   }

   private fun updatedMovesList(moves: MovesList){
      localboard.movesList = dbmoveList(moves)
   }

   fun pieceTeam(move: Move, teamTurn: Team):ValueResult<*>{
      return localboard.pieceTeamCheck(move, teamTurn)
   }
   private fun createMoves(){
      dbboard.createGame(localboard.movesList)
   }

   fun updateMoves(){
      dbboard.updateGame(localboard.movesList)
   }

   fun openGame(id:String):Boolean{
      GAMEID = id
      updatedMovesList(MovesList(id, mutableListOf()))
      return GAMEID in dbboard.gamesIDList()
   }

   fun createList(id:String){
      localboard.movesList = MovesList(id, mutableListOf())
   }

   fun createGame(id: String){
      GAMEID = id
      dbboard.createID(GAMEID!!)
      localboard
      localboard.moves._id =GAMEID!!
      createMoves()
   }

}
