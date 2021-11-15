
interface ChessCommands{

   fun open(board: Board,id:String):Iterable<String>

   fun join(board: Board,id:String)

   fun play(board: Board,move:String?)

   fun refresh(board: Board)

   fun moves(board: Board)

   fun exit()
}