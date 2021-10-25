interface BoardInterface {
    fun open(gameId:Int)
    fun join(gameId:Int)
    fun play(move:String):Unit
    fun refresh()
    fun moves()
    fun exit()
}