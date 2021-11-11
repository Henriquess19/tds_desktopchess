
/**
 * Fun the receives the [Board] and draw the information
 */
fun draw(board: Board) {
    val boards= board.toString()
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
