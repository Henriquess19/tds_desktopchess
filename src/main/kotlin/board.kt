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
    private val board = mutableMapOf<Positions, Pieces?>()

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

        val piece = board[oldposition] ?: throw Throwable("Piece not founded in the initialposition.")

        board[newposition] = Pieces.values()[piece.ordinal]
        board.remove(oldposition)
        draw()

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

    override fun draw() {
        val strboard = toString()
        var itrstrboard = 0

        println("    a b c d e f g h ")
        println("   -----------------")
        for (i in Lines.L8.ordinal downTo Lines.L1.ordinal) {
            print("${i + 1} | ")
            for (k in 0..7) {
                print(strboard[itrstrboard++] + " ")
            }
            println("| ")
        }
        println("   -----------------\n")

        println("$GAME_ID:${Colors.values()[0]}>\n ")
    }

    fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean {
        val ocupied = board.containsKey(wantedposition)

        if (ocupied) {
            val ocupiedcolor = board[wantedposition]?.team
            if (ocupiedcolor == piece.team) {
                return false //Same team
            }
        }

        /**
         * Rock Verity
         */
        if (piece == Pieces.R || piece == Pieces.r) {
            if (initialposition.line == wantedposition.line) {
                val highercolumn = Math.max(initialposition.columns.ordinal, wantedposition.columns.ordinal)
                val lowercolumn = Math.min(initialposition.columns.ordinal, wantedposition.columns.ordinal)

                for (i in highercolumn - 1 until lowercolumn + 1) {
                    if (board.containsKey(Positions(Lines.values()[i],wantedposition.columns))) return false //Encounter
                }
            }

            else if (initialposition.columns == wantedposition.columns) {
                val higherline = Math.max(initialposition.line.ordinal, wantedposition.line.ordinal)
                val lowerline = Math.min(initialposition.line.ordinal, wantedposition.line.ordinal)

                for (i in higherline - 1 until lowerline + 1) {
                    if (board.containsKey(Positions(Lines.values()[i],wantedposition.columns))) return false //Encounter
                }
            }

            return true //Valid piece movement
        }

        /**
         * Pawn Verity
         */
        if (piece == Pieces.P || piece == Pieces.p) {
            //Initial pawn position
            if (piece == Pieces.P && initialposition.line == Lines.L2 && initialposition.columns == wantedposition.columns) {
                if (board.containsKey(Positions(Lines.L3, initialposition.columns))
                    || board.containsKey(Positions(Lines.L4, initialposition.columns))
                ) return false //Encounter
            }

            //Move infront
            if (piece == Pieces.P) {
                if (initialposition.columns == wantedposition.columns) {
                    if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 || ocupied) return false //Encounter

                }

                if (piece == Pieces.p) {
                    if (initialposition.columns == wantedposition.columns) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || ocupied) return false //Encounter
                    }
                }

                //Move diagonal
                if (initialposition.columns.ordinal != wantedposition.columns.ordinal - 1
                    || initialposition.columns.ordinal != wantedposition.columns.ordinal + 1
                ) return false //Invalid Movement

                else {
                    if (piece == Pieces.P) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || !ocupied) return false //Not Encounter
                    }

                    if (piece == Pieces.p) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 && !ocupied) return false //Not Encounter
                    }
                }
                return true //Valid piece movement
            }
        }

        return true
    }
}


