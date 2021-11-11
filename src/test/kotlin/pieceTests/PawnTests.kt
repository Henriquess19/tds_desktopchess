package PieceTests

import Board
import draw
import kotlin.test.Test
import kotlin.test.assertEquals

class PawnTests {
    @Test
    fun `illegal pawn move return the board not change`() {
        val sut = Board()
            .makeMove("Pa2b3") //Ilegal movement
            .makeMove("Pg7h6") //Ilegal movement
            .makeMove("Pe7e4")
            .makeMove("Pe2e5")
            .makeMove("Pe2f5")
            .makeMove("Pe7f5")
        draw(sut)

        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
    @Test
    fun `the two possible first moves are ok on both sides`() {
        val sut = Board()
            .makeMove("Ph7h6")
            .makeMove("Pf7f5")
            .makeMove("Pc2c4")
            .makeMove("Pa2a3")
        draw(sut)

        assertEquals(
            "rnbqkbnr"+
                    "ppppp p "+
                    "       p"+
                    "     p  "+
                    "  P     "+
                    "P       "+
                    " P PPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `wrong pawn second move return false`() {
        val sut = Board()
            .makeMove("Pa7a5")
            .makeMove("Pa5a3")
        draw(sut)

        assertEquals(
            "rnbqkbnr"+
                    " ppppppp"+
                    "        "+
                    "p       "+
                    "        "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
    @Test
    fun `two successful pawn moves`() {
        val sut = Board()
            .makeMove("Pa7a5")
            .makeMove("Pa5a4")
        draw(sut)

        assertEquals(
            "rnbqkbnr"+
                    " ppppppp"+
                    "        "+
                    "        "+
                    "p       "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
    //não dá para comer peças
    @Test
    fun `move diagonaly after eating a piece`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("Pb2b4")
            .makeMove("pa5b4")  //Eating movement
            .makeMove("Pc2c3")
            .makeMove("Pc3b4")  //Eating movement
        draw(sut)


        assertEquals(
            "rnbqkbnr"+
                    " ppppppp"+
                    "        "+
                    "        "+
                    " P      "+
                    "        "+
                    "P  PPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
}