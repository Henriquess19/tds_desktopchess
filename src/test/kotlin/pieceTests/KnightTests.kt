package pieceTests

import Board
import draw
import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTests {
    @Test
    fun `legal knight bigup smallright move`() {
        val sut = Board()
            .makeMove("Ng1h3")

        assertEquals(
            "rnbqkbnr" +
                      "pppppppp" +
                      "        " +
                      "        " +
                      "        " +
                      "       N" +
                      "PPPPPPPP" +
                      "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `legal knight bigup smallleft move`() {
        val sut = Board()
            .makeMove("Ng1f3")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "     N  " +
              "PPPPPPPP" +
              "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `legal knight bigdown smallleft move`() {
        val sut = Board()
            .makeMove("nb8a6")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "n       " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }
    @Test
    fun `legal knight bigdown smallright move`() {
        val sut = Board()
            .makeMove("nb8c6")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "  n     " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `legal knight bigright smallup move`() {
        val sut = Board()
            .makeMove("Pd2d3")
            .makeMove("Nb1d2")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "   P    " +
              "PPPNPPPP" +
              "R BQKBNR", sut.toString()
        )
    }

    @Test
    fun `legal knight bigright smalldown move`() {
        val sut = Board()
            .makeMove("pd7d6")
            .makeMove("nb8d7")

        assertEquals(
            "r bqkbnr" +
              "pppnpppp" +
              "   p    " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `legal knight bigleft smallup move`() {
        val sut = Board()
            .makeMove("Pe2e3")
            .makeMove("Ng1e2")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "    P   " +
              "PPPPNPPP" +
              "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `legal knight bigleft smalldown move`() {
        val sut = Board()
            .makeMove("pe7e6")
            .makeMove("ng8e7")

        assertEquals(
            "rnbqkb r" +
              "ppppnppp" +
              "    p   " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigup smallright move`() {
        val sut = Board()
            .makeMove("Ph2h3")
            .makeMove("Ng1h3")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "       P" +
              "PPPPPPP " +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigup smallleft move`() {
        val sut = Board()
            .makeMove("Pf2f3")
            .makeMove("Ng1f3")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "     P  " +
              "PPPPP PP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigdown smallleft move`() {
        val sut = Board()
            .makeMove("pa7a6")
            .makeMove("nb8a6")

        assertEquals(
            "rnbqkbnr" +
              " ppppppp" +
              "p       " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }
    @Test
    fun `ilegal knight bigdown smallright move`() {
        val sut = Board()
            .makeMove("pc7c6")
            .makeMove("nb8c6")

        assertEquals(
            "rnbqkbnr" +
              "pp ppppp" +
              "  p     " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigright smallup move`() {
        val sut = Board()
            .makeMove("Nb1d2")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigright smalldown move`() {
        val sut = Board()
            .makeMove("nb8d7")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigleft smallup move`() {
        val sut = Board()
            .makeMove("Ng1e2")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `ilegal knight bigleft smalldown move`() {
        val sut = Board()
            .makeMove("ng8e7")

        assertEquals(
            "rnbqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `knight bigup smallright eat move`() {
        val sut = Board()
            .makeMove("Ng1h3")
            .makeMove("Nh3g5")
            .makeMove("Ng5h7")

        assertEquals(
              "rnbqkbnr" +
                      "pppppppN" +
                      "        " +
                      "        " +
                      "        " +
                      "        " +
                      "PPPPPPPP" +
                      "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `knight bigup smallleft eat move`() {
        val sut = Board()
            .makeMove("Ng1h3")
            .makeMove("Nh3g5")
            .makeMove("Ng5f7")

        assertEquals(
            "rnbqkbnr" +
              "pppppNpp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `knight bigdown smallleft eat move`() {
        val sut = Board()
            .makeMove("nb8a6")
            .makeMove("na6b4")
            .makeMove("nb4a2")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "nPPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }
    @Test
    fun `knight bigdown smallright eat move`() {
        val sut = Board()
            .makeMove("nb8a6")
            .makeMove("na6b4")
            .makeMove("nb4c2")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPnPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `knight bigright smallup eat move`() {
        val sut = Board()
            .makeMove("Ng1h3")
            .makeMove("Nh3g5")
            .makeMove("Ng5e6")
            .makeMove("Ne6g7")

        assertEquals(
            "rnbqkbnr" +
              "ppppppNp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `knight bigright smalldown eat move`() {
        val sut = Board()
            .makeMove("nb8c6")
            .makeMove("nc6b4")
            .makeMove("nb4d3")
            .makeMove("nd3f2")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPnPP" +
              "RNBQKBNR", sut.toString()
        )
    }

    @Test
    fun `knight bigleft smallup eat move`() {
        val sut = Board()
            .makeMove("Ng1h3")
            .makeMove("Nh3g5")
            .makeMove("Ng5e6")
            .makeMove("Ne6c7")

        assertEquals(
            "rnbqkbnr" +
              "ppNppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PPPPPPPP" +
              "RNBQKB R", sut.toString()
        )
    }

    @Test
    fun `knight bigleft smalldown eat move`() {
        val sut = Board()
            .makeMove("nb8c6")
            .makeMove("nc6b4")
            .makeMove("nb4d3")
            .makeMove("nd3b2")

        assertEquals(
            "r bqkbnr" +
              "pppppppp" +
              "        " +
              "        " +
              "        " +
              "        " +
              "PnPPPPPP" +
              "RNBQKBNR", sut.toString()
        )
    }
}