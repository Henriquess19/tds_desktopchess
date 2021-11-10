/**
 * Fun the receives the [Board] and draw the information
 */
fun boardConsoleDraw(board: Board){
    val boards= board.toString()
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
    }
}