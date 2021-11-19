package pieceTests

import domain.*
import kotlin.test.Test
import kotlin.test.assertEquals

class PawnTests {
    @Test
    fun `legal pawn 1 block upward move`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pa7a6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pa7a5".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("rh8h6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("rh6a6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra6a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pb2a3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("rh8h6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("rh6a6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra6a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra3b3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pa2b3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh3h6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pg7h6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh3h6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh6g6".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ph7g6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("rh8h6".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("rh6a6".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("ra6a3".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("Pa2a3".toMove(), teamTurn(sut.movesList,Team.WHITE))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("rh8h6".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("rh6a6".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("ra6a4".toMove(), teamTurn(sut.movesList,Team.BLACK)).first
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,Team.WHITE))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Rh3h6".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("ph7h6".toMove(), teamTurn(sut.movesList,Team.BLACK))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("Rh3h5".toMove(), teamTurn(sut.movesList,Team.WHITE)).first
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,Team.BLACK))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2b3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pb2a3".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pb7a6".toMove(), teamTurn(sut.movesList,null))

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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pa7b6".toMove(), teamTurn(sut.movesList,null))

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