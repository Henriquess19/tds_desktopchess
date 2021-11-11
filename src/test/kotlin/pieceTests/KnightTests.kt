package PieceTests

import Board
import draw
import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTests {
    @Test
    fun `legal horse move return knight moved`() {
        val sut = Board()
            .makeMove("nb8c6")
            .makeMove("ng8f6")
            .makeMove("Nb1c3")
            .makeMove("Ng1f3")
            .makeMove("nc6a5")
            .makeMove("nf6h5")
            .makeMove("Nc3a4")
            .makeMove("Nf3h4")
        draw(sut)

        assertEquals(
            "r bqkb r"+
                    "pppppppp"+
                    "        "+
                    "n      n"+
                    "N      N"+
                    "        "+
                    "PPPPPPPP"+
                    "R BQKB R",sut.toString()
        )
    }
    @Test
    fun `ilegal moves with the knight on both sides`() {
        val sut = Board()
            .makeMove("nb8c6")
            .makeMove("nc6c5")//ilegal move
            .makeMove("nc6b6")//ilegal move
            .makeMove("nc6d6")//ilegal move
            .makeMove("Nb1c3")
            .makeMove("Nc3c4")//ilegal move
            .makeMove("Nc3b3")//ilegal move
            .makeMove("Nc3d3")//ilegal move
        draw(sut)
        assertEquals(
            "r bqkbnr"+
                    "pppppppp"+
                    "  n     "+
                    "        "+
                    "        "+
                    "  N     "+
                    "PPPPPPPP"+
                    "R BQKBNR",sut.toString()
        )
    }
    @Test
    fun `eating a piece with knight on both sides`() {
        val sut = Board()
            .makeMove("nb8c6")
            .makeMove("Pd2d4")
            .makeMove("nc6d4")
            .makeMove("Nb1c3")
            .makeMove("pd7d5")
            .makeMove("Nc3d5")
        draw(sut)
        assertEquals(
            "r bqkbnr"+
                    "ppp pppp"+
                    "        "+
                    "   N    "+
                    "   n    "+
                    "        "+
                    "PPP PPPP"+
                    "R BQKBNR",sut.toString()
        )
    }
}