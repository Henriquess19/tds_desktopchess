/**
 * Class the receives the [Board] and draw the information
 */
class BoardConsoleDraw(board: Board){
    private val boards= board.toString()

    /**
     * Catch the current state of the board, when called, and print it
     */

    fun draw() {
        println("    a b c d e f g h ")
        println("   -----------------")
        var idx = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            print("${i + 1} | ")
            for (k in Columns.CA.ordinal..Columns.CH.ordinal) {
                val piece = boards[idx]
                if(piece != ' ') {
                    print(piece)
                    print(" ")
                }
                else print("  ")
                idx++
            }
            println("| ")
        }
        println("   -----------------\n")

        println("$GAME_ID:${Colors.values()[0]}>\n ")
    }

    /**
     * Not working
     */
    fun moves(moveslist: MutableMap<Int, PlayMade>) {
        moveslist.forEach {
            println("Play number ${it.key}: ${it.value.team} -> ${it.value.play}")
        }
    }
}