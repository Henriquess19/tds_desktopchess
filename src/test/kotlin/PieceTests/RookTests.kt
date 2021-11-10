package PieceTests

import Board
import kotlin.test.assertEquals
import kotlin.test.Test
class RookTests {
    @Test
    fun `two successful rook moves both sides`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("Ra8a6")
            .makeMove("Ra6h6")
            .makeMove("pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")

        assertEquals(
            " nbqkbnr"+
                    " ppppppp"+
                    "       r"+
                    "p       "+
                    "P       "+
                    "       R"+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }
    @Test
    fun `eating a piece with both sides`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("Ra8a6")
            .makeMove("Ra6h6")
            .makeMove("rh6h2")
            .makeMove("pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3g3")
            .makeMove("rg3g7")
        assertEquals(
            " nbqkbnr"+
                    " pppppRp"+
                    "        "+
                    "p       "+
                    "P       "+
                    "        "+
                    " PPPPPPr"+
                    " NBQKBNR",sut.toString()
        )
    }
    @Test
    fun `ilegal moves with boath rooks`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("ra8a4") //Ilegal movement
            .makeMove("ra8a6")
            .makeMove("ra6c4") //Ilegal movement
            .makeMove("pb7b6")
            .makeMove("ra6d6") //Ilegal movement
            .makeMove("Pa2a4")
            .makeMove("Ra1a5") //Ilegal movement
            .makeMove("Ra1a3")
            .makeMove("Pb2b3")
            .makeMove("Ra3d3") //Ilegal movement
        assertEquals(
            " nbqkbnr"+
                    "  pppppp"+
                    "rp      "+
                    "p       "+
                    "P       "+
                    "RP      "+
                    "  PPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }
}