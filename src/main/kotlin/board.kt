enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH }
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}
enum class Pieces(val char: Char){r('r'),n('n'),b('b'),q('q'),k('k'),p('p'),R('R'),N('N'),B('B'),Q('Q'),K('K'),P('P')}
data class Positions(val line:Lines,val columns:Columns)

const val INITAL_BOARD = "rnbqkbnr" +
    "pppppppp" +
    "        " +
    "        " +
    "        " +
    "        " +
    "PPPPPPPP" +
    "RNBQKBNR"

class Board(): BoardInterface {
    private val board= mutableMapOf<Positions,Pieces>()
    init {
        var k = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal){
            for (j in Columns.CA.ordinal..Columns.CH.ordinal){
                val key = INITAL_BOARD[k++]
                if ( key != ' '){
                    board.put(Positions(Lines.values()[i], Columns.values()[j]), Pieces.valueOf(key.toString()))
                }
            }
        }
    }


    override fun makeMove(move: String){
        /*val piece= move[0]
        val start:Positions = Positions(move[2].toInt(),move[1])
        val end:Positions = Positions(move[4].toInt(),move[5])

        // board.put(start,TODO())
        //board.put(end,piece)
        draw()*/
    }

    override fun toString(): String {
        var strboard = ""
        for(i in Lines.L8.ordinal downTo Lines.L1.ordinal){
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val boardpiece = board[Positions(Lines.values()[i], Columns.values()[j])]
                if (boardpiece != null){
                    strboard += boardpiece
                }else{
                    strboard+=" "
                }
            }
        }
        return strboard
    }

    override fun draw(strboard:String){
        println("    a b c d e f g h ")
        println("   -----------------")
        print("8 | ")
            for (i in 0 .. 7){
                print(strboard[i] + " ")
            }
        println("| ")
    }
}

