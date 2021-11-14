package pieceTests

import Board
import teamTurn
import toMove
import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTests {
    @Test
    fun `legal knight bigup smallright move`() {
        val sut = Board()
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1f3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Pd2d3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Nb1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pd7d6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb8d7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Pe2e3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng1e2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pe7e6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ng8e7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ph2h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Pf2f3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng1f3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pa7a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb8a6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pc7c6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb8c6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Nb1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8d7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1e2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("ng8e7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Nh3g5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng5h7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Nh3g5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng5f7".toMove(), teamTurn(sut.getMoveList(),null))
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
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("na6b4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb4a2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("na6b4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb4c2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Nh3g5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng5e6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ne6g7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nc6b4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb4d3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nd3f2".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Nh3g5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ng5e6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ne6c7".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nc6b4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nb4d3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("nd3b2".toMove(), teamTurn(sut.getMoveList(),null))

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