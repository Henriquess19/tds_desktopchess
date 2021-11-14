package pieceTests

import Board
import draw
import teamTurn
import toMove
import kotlin.test.Test
import kotlin.test.assertEquals

class PawnTests {
    @Test
    fun `legal pawn 1 block upward move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a3".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "        "+
                    "P       "+
                    " PPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `legal pawn 2 block upward move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "P       "+
                    "        "+
                    " PPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `legal pawn 1 block downward move`() {
        val sut = Board()
        sut
            .makeMove("pa7a6".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbnr"+
                    " ppppppp"+
                    "p       "+
                    "        "+
                    "        "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `legal pawn 2 block downward move`() {
        val sut = Board()
        sut
            .makeMove("pa7a5".toMove(), teamTurn(sut.getMoveList(),null))

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
    fun `Pawn upleftdiagonal eat move`() {
        val sut = Board()
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("rh6a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ra6a3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Pb2a3".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbn "+
                    "ppppppp "+
                    "        "+
                    "       p"+
                    "        "+
                    "P       "+
                    "P PPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Pawn uprightdiagonal eat move`() {
        val sut = Board()
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("rh6a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ra6a3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ra3b3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Pa2b3".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbn "+
                    "ppppppp "+
                    "        "+
                    "       p"+
                    "        "+
                    " P      "+
                    " PPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Pawn downrightdiagonal eat move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Rh3h6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("pg7h6".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbnr"+
                    "pppppp p"+
                    "       p"+
                    "        "+
                    "P       "+
                    "        "+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Pawn downleftdiagonal eat move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Rh3h6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Rh6g6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ph7g6".toMove(), teamTurn(sut.getMoveList(),null))

        assertEquals(
            "rnbqkbnr"+
                    "ppppppp "+
                    "      p "+
                    "        "+
                    "P       "+
                    "        "+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Ilegal pawn 1 block upward move`() {
        val sut = Board()
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("rh6a6".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("ra6a3".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("Pa2a3".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))

        assertEquals(
            "rnbqkbn "+
                    "ppppppp "+
                    "        "+
                    "       p"+
                    "        "+
                    "r       "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Ilegal pawn 2 block upward move`() {
        val sut = Board()
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("rh6a6".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("ra6a4".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
        draw(sut)
        assertEquals(
            "rnbqkbn "+
                    "ppppppp "+
                    "        "+
                    "       p"+
                    "r       "+
                    "        "+
                    "PPPPPPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Ilegal pawn 1 block downward move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Rh3h6".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("ph7h6".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))

        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "       R"+
                    "        "+
                    "P       "+
                    "        "+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Ilegal pawn 2 block downward move`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("Rh3h5".toMove(), teamTurn(sut.getMoveList(),Team.WHITE))
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),Team.BLACK))

        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "       R"+
                    "P       "+
                    "        "+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `Ilegal pawn uprightdiagonal move`() {
        val sut = Board()
        sut
            .makeMove("Pa2b3".toMove(), teamTurn(sut.getMoveList(),null))

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
    fun `Ilegal pawn upleftdiagonal move`() {
        val sut = Board()
        sut
            .makeMove("Pb2a3".toMove(), teamTurn(sut.getMoveList(),null))

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
    fun `Ilegal pawn downleftdiagonal move`() {
        val sut = Board()
        sut
            .makeMove("pb7a6".toMove(), teamTurn(sut.getMoveList(),null))

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
    fun `Ilegal pawn downrightdiagonal move`() {
        val sut = Board()
        sut
            .makeMove("pa7b6".toMove(), teamTurn(sut.getMoveList(),null))

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
}