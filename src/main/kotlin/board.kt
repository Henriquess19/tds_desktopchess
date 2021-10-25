class Board:BoardInterface{
    private var board = arrayOf<String>("rnbqkbnr",
            "pppppppp",
            "        ",
            "        ",
            "        ",
            "        ",
            "PPPPPPPP",
            "RNBQKBNR")
    override fun print(): List<Unit> {
        return board.map{ println(it)}
    }

    override fun makeMove(move: String) {
        TODO("Not yet implemented")
    }
}
