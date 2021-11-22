package domain

import storage.MongoDbChess

/**
 * The point of connection between the MongoDb and the local board
 * @param localBoard the local board
 * @param dbBoard the way to manipulate the DB
 */
data class Board(var localBoard: BoardState, val dbBoard: MongoDbChess){

   var gameId: String = "-1"//doesn't exist

   /**
    * Make the play, if is valid, the user want to do in the local board and update the mongo one
    * @param move the move being made
    * @param team the team who is making the play
    * @return [ValueResult] if the move was valid or not
    */

   fun playMove(move: Move, team: Team):ValueResult<*>{
      val play = localBoard.makeMove(move, team)
      return when (play.second.data){
          ValidMovement -> {
             updateMoves()
             ValueResult(ValidMovement)
          }
         EndedGame -> {
            updateMoves()
            ValueResult(EndedGame)
         }
         NeedPromotion ->{
            ValueResult(NeedPromotion)
         }
         InvalidMovement->{
            ValueResult(InvalidMovement)
         }
         SameTeam->{
            ValueResult(SameTeam)
         }
         Encounter->{
            ValueResult(Encounter)
         }
         else -> ValueResult(InvalidCommand)

      }
   }
   /**
    * Catch the local move list
    * @return [MovesList] the list of moves made
    */
   fun moveList(): MovesList{
      return localBoard.movesList
   }
   /**
    * Catch the DB move list
    * @param moves move list
    * @return [MovesList] the list of moves made on stored on DB
    */
   private fun dbMoveList(moves: MovesList): MovesList {
      val list = mutableListOf<PlayMade>()
      dbBoard.findgamebyId(moves).content.forEach {list += it}
      return MovesList(moves._id,list)
   }
   /**
    * Update the local move list
    * @param moves what local moveList will be transform to
    * @return [MovesList] the new local list of moves
    */
   private fun updatedMovesList(moves: MovesList){
      localBoard.movesList = dbMoveList(moves)
   }
   /**
    * If the piece being moved is really from the team who is trying to move her
    * @param move the move being made
    * @param teamTurn who is the team playing
    * @return [ValueResult] if is valid movement or not
    */
   fun correctPieceTeam(move: Move, teamTurn: Team):ValueResult<*>{
      return localBoard.pieceTeamCheck(move, teamTurn)
   }

   /**
    * Create a list of moves on DB
    */
   private fun createMoves(){
      dbBoard.createGame(localBoard.movesList)
   }

   fun updateList(){
      localBoard.movesList = dbBoard.findgamebyId(localBoard.movesList)
   }

   /**
    * It will update the DB with the local moves list
    */
   fun updateMoves(){
      dbBoard.updateGame(localBoard.movesList)
   }
   /**
    * Open a game in the specified id
    * @param id the id we want to open
    */
   fun openGame(id:String){
      gameId = id
      updatedMovesList(MovesList(id, mutableListOf()))
   }
   /**
    * Create a new game in the specified id
    * @param id the id we want to create
    */
   fun createGame(id: String){
      gameId = id
      dbBoard.createID(gameId)
      localBoard
      localBoard.moves._id =gameId
      createMoves()
   }
}
