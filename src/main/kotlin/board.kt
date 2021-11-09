enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}
enum class Pieces(val char: Char,val team:Colors){r('r',Colors.BLACK),n('n',Colors.BLACK),b('b',Colors.BLACK),q('q',Colors.BLACK),k('k',Colors.BLACK),p('p',Colors.BLACK),R('R',Colors.WHITE),N('N',Colors.WHITE),B('B',Colors.WHITE),Q('Q',Colors.WHITE),K('K',Colors.WHITE),P('P',Colors.WHITE)}
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

const val GAME_ID = "g1"
enum class Colors{BLACK,WHITE}

class Board(): BoardInterface {
    private val board = mutableMapOf<Positions, Pieces>()
    private val moveslist = emptyArray<String>()
    init {
        var k = 0
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val key = INITAL_BOARD[k++]
                if (key != ' ') {
                    board[Positions(Lines.values()[i], Columns.values()[j])] = Pieces.valueOf(key.toString())
                }
            }
        }
        println("$GAME_ID:${Colors.values()[0]}>\n ")

    }


    override fun makeMove(move: String): Board {
        val oldline = move[2].toString().toInt() - 1
        val newline = move[4].toString().toInt() - 1
        val oldcolumn = "C" + move[1].uppercaseChar()
        val newcolumn = "C" + move[3].uppercaseChar()

        val oldposition = Positions(Lines.values()[oldline], Columns.valueOf(oldcolumn))
        val newposition = Positions(Lines.values()[newline], Columns.valueOf(newcolumn))
        println(board)

        val piece = board[oldposition] ?: throw Throwable("Piece not founded in the initialposition.")

        if(VerificationBoard(board).moveVerity(piece, oldposition, newposition)) {
            board[newposition] = piece
            board.remove(oldposition)
        }
        //moveslist[moveslist.size] = move
        BoardConsoleDraw(board).draw()
        return this
    }

    override fun toString(): String {
        var strboard = ""
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            for (j in Columns.CA.ordinal..Columns.CH.ordinal) {
                val boardpiece = board[Positions(Lines.values()[i], Columns.values()[j])]
                if (boardpiece != null) {
                    strboard += boardpiece
                } else {
                    strboard += " "
                }
            }
        }
        return strboard
    }

    override fun moves() {
        var teamselector = 0
        var team:String
        moveslist.forEach {i->
            if (teamselector%2 != 0){
                team="BLACK"
            }else{
                team="White"
            }
            teamselector++
            println("Play number ${teamselector+1}: ${team} -> ${i}")
        }
    }
}


