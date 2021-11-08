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
    fun `ilegal move`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("Ra8a6")
            .makeMove("Ra6e6")
            .makeMove("Re6f5")
            .makeMove("Re6d5")
        assertEquals(
            " nbqkbnr"+
                    " ppppppp"+
                    "    r   "+
                    "p       "+
                    "        "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
}