
enum class Collum
    (val collum:Int){ a(1), b(2), c(3), d(4), e(5), f(6), g(7), h(8) }
data class Positions(val line: Int,val collum:Collum)

class Board(): BoardInterface {
    private var board = arrayListOf<String>(
        "rnbqkbnr"+
        "pppppppp"+
        " "+
        " "+
        " "+
        " "+
        "PPPPPPPP"+
        "RNBQKBNR"
    )

    override fun makeMove(move: String): Board {
        val movecommands = move.toCharArray()
        val piece = movecommands[0]

    }

    override fun toString(): String {
        var res = ""
        this.board.forEach { res += it.toString() }
        return res
    }
}
