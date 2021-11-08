
class BoardConsoleDraw(private val board: MutableMap<Positions, Pieces> = mutableMapOf()){
    fun draw() {

        println("    a b c d e f g h ")
        println("   -----------------")
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            print("${i + 1} | ")
            for (k in Columns.CA.ordinal..Columns.CH.ordinal) {
                val piece = board[Positions(Lines.values()[i], Columns.values()[k])]
                if(piece != null) {
                    print(board[Positions(Lines.values()[i], Columns.values()[k])])
                    print(" ")
                }
                else print("  ")
            }
            println("| ")
        }
        println("   -----------------\n")

        println("$GAME_ID:${Colors.values()[0]}>\n ")
    }
}