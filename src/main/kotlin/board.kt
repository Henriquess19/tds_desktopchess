enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH }
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}
enum class Pieces{r,n,b,q,k,p,R,N,B,Q,K, empty}
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
        for (i in Lines.L1.ordinal..Lines.L8.ordinal){
            for (j in Columns.CA.ordinal..Columns.CH.ordinal){
                val key = INITAL_BOARD[k++]
                if ( key != ' '){
                    board.put(Positions(Lines.values()[i], Columns.values()[j]), key)
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
        var res = ""
        this.board.forEach { res += it.toString() }
        return res
    }

    override fun draw(){
        TODO()
    }
}

