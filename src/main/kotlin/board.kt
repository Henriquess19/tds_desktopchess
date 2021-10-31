enum class Columns(val char: Char){CA('a'),CB('b'),CC('c'),CD('d'),CE('e'),CF('f'),CG('g'),CH('h') }
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}
enum class Pieces(val char: Char){r('r'),n('n'),b('b'),q('q'),k('k'),p('p'),R('R'),N('N'),B('B'),Q('Q'),K('K'),P('P')}
data class Positions(val line:Lines,val columns:Columns)

const val INITAL_BOARD =
    "rnbqkbnr" +
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


    override fun makeMove(move: String): Board {
        val piece = Pieces.valueOf(move[0].toString())
        val oldline = move[2].toString().toInt()-1
        val newline = move[4].toString().toInt()-1
        val oldcolumn = "C" + move[1].toUpperCase()
        val newcolumn = "C" + move[3].toUpperCase()

        val oldposition = Positions(Lines.values()[oldline], Columns.valueOf(oldcolumn))
        val newposition = Positions(Lines.values()[newline], Columns.valueOf(newcolumn))

        board.remove(oldposition)
        board[newposition] = piece
        draw()

        return
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

    override fun draw(){
        val strboard = toString()
        var itrstrboard = 0

        println("    a b c d e f g h ")
        println("   -----------------")
        for(i in Lines.L8.ordinal downTo Lines.L1.ordinal){
             print("${i+1} | ")
                for (k in 0 .. 7){
                    print(strboard[itrstrboard++] + " ")
                }
            println("| ")
        }
        println("   -----------------")
    }
}

