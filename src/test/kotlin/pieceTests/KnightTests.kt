package pieceTests

import domain.*
import kotlin.test.Test
import kotlin.test.assertEquals

class KnightTests {
    @Test
    fun `legal knight bigup smallright move`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1f3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pd2d3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nb1d2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pd7d6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb8d7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pe2e3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng1e2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pe7e6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ng8e7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ph2h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pf2f3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng1f3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pa7a6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb8a6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pc7c6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb8c6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Nb1d2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8d7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1e2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ng8e7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nh3g5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng5h7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nh3g5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng5f7".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("na6b4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb4a2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8a6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("na6b4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb4c2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nh3g5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng5e6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ne6g7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nc6b4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb4d3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nd3f2".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ng1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nh3g5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ng5e6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ne6c7".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("nb8c6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nc6b4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nb4d3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("nd3b2".toMove(), teamTurn(sut.movesList,null))

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