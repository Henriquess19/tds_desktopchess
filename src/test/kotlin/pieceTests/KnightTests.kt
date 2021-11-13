package pieceTests

import Board
import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTests {
    @Test
    fun `legal knight bigup smallright move`() {
        val sut = Board()
            .makeMove("Ng1h3", teamTurn(moves(board)))

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
            .makeMove("Ng1f3", teamTurn(moves(board)))

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
            .makeMove("nb8a6", teamTurn(moves(board)))

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
            .makeMove("nb8c6", teamTurn(moves(board)))

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
            .makeMove("Pd2d3", teamTurn(moves(board)))
            .makeMove("Nb1d2", teamTurn(moves(board)))

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
            .makeMove("pd7d6", teamTurn(moves(board)))
            .makeMove("nb8d7", teamTurn(moves(board)))

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
            .makeMove("Pe2e3", teamTurn(moves(board)))
            .makeMove("Ng1e2", teamTurn(moves(board)))

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
            .makeMove("pe7e6", teamTurn(moves(board)))
            .makeMove("ng8e7", teamTurn(moves(board)))

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
            .makeMove("Ph2h3", teamTurn(moves(board)))
            .makeMove("Ng1h3", teamTurn(moves(board)))

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
            .makeMove("Pf2f3", teamTurn(moves(board)))
            .makeMove("Ng1f3", teamTurn(moves(board)))

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
            .makeMove("pa7a6", teamTurn(moves(board)))
            .makeMove("nb8a6", teamTurn(moves(board)))

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
            .makeMove("pc7c6", teamTurn(moves(board)))
            .makeMove("nb8c6", teamTurn(moves(board)))

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
            .makeMove("Nb1d2", teamTurn(moves(board)))

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
            .makeMove("nb8d7", teamTurn(moves(board)))

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
            .makeMove("Ng1e2", teamTurn(moves(board)))

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
            .makeMove("ng8e7", teamTurn(moves(board)))

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
            .makeMove("Ng1h3", teamTurn(moves(board)))
            .makeMove("Nh3g5", teamTurn(moves(board)))
            .makeMove("Ng5h7", teamTurn(moves(board)))

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
            .makeMove("Ng1h3", teamTurn(moves(board)))
            .makeMove("Nh3g5", teamTurn(moves(board)))
            .makeMove("Ng5f7", teamTurn(moves(board)))

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
            .makeMove("nb8a6", teamTurn(moves(board)))
            .makeMove("na6b4", teamTurn(moves(board)))
            .makeMove("nb4a2", teamTurn(moves(board)))

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
            .makeMove("nb8a6", teamTurn(moves(board)))
            .makeMove("na6b4", teamTurn(moves(board)))
            .makeMove("nb4c2", teamTurn(moves(board)))

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
            .makeMove("Ng1h3", teamTurn(moves(board)))
            .makeMove("Nh3g5", teamTurn(moves(board)))
            .makeMove("Ng5e6", teamTurn(moves(board)))
            .makeMove("Ne6g7", teamTurn(moves(board)))

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
            .makeMove("nb8c6", teamTurn(moves(board)))
            .makeMove("nc6b4", teamTurn(moves(board)))
            .makeMove("nb4d3", teamTurn(moves(board)))
            .makeMove("nd3f2", teamTurn(moves(board)))

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
            .makeMove("Ng1h3", teamTurn(moves(board)))
            .makeMove("Nh3g5", teamTurn(moves(board)))
            .makeMove("Ng5e6", teamTurn(moves(board)))
            .makeMove("Ne6c7", teamTurn(moves(board)))

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
            .makeMove("nb8c6", teamTurn(moves(board)))
            .makeMove("nc6b4", teamTurn(moves(board)))
            .makeMove("nb4d3", teamTurn(moves(board)))
            .makeMove("nd3b2", teamTurn(moves(board)))

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