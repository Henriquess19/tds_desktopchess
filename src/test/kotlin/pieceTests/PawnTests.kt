package pieceTests

import Board
import draw
import kotlin.test.Test
import kotlin.test.assertEquals

class PawnTests {
    @Test
    fun `legal pawn 1 block upward move`() {
        val sut = Board()
            .makeMove("Pa2a3", teamTurn(moves(board)))

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
            .makeMove("Pa2a4", teamTurn(moves(board)))

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
            .makeMove("pa7a6", teamTurn(moves(board)))

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
            .makeMove("pa7a5", teamTurn(moves(board)))

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
            .makeMove("ph7h5", teamTurn(moves(board)))
            .makeMove("rh8h6", teamTurn(moves(board)))
            .makeMove("rh6a6", teamTurn(moves(board)))
            .makeMove("ra6a3", teamTurn(moves(board)))
            .makeMove("Pb2a3", teamTurn(moves(board)))

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
            .makeMove("ph7h5", teamTurn(moves(board)))
            .makeMove("rh8h6", teamTurn(moves(board)))
            .makeMove("rh6a6", teamTurn(moves(board)))
            .makeMove("ra6a3", teamTurn(moves(board)))
            .makeMove("ra3b3", teamTurn(moves(board)))
            .makeMove("Pa2b3", teamTurn(moves(board)))

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
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
            .makeMove("Rh3h6", teamTurn(moves(board)))
            .makeMove("pg7h6", teamTurn(moves(board)))

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
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
            .makeMove("Rh3h6", teamTurn(moves(board)))
            .makeMove("Rh6g6", teamTurn(moves(board)))
            .makeMove("ph7g6", teamTurn(moves(board)))

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
            .makeMove("ph7h5", teamTurn(moves(board)))
            .makeMove("rh8h6", teamTurn(moves(board)))
            .makeMove("rh6a6", teamTurn(moves(board)))
            .makeMove("ra6a3", teamTurn(moves(board)))
            .makeMove("Pa2a3", teamTurn(moves(board)))

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
            .makeMove("ph7h5", teamTurn(moves(board)))
            .makeMove("rh8h6", teamTurn(moves(board)))
            .makeMove("rh6a6", teamTurn(moves(board)))
            .makeMove("ra6a4", teamTurn(moves(board)))
            .makeMove("Pa2a4", teamTurn(moves(board)))
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
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
            .makeMove("Rh3h6", teamTurn(moves(board)))
            .makeMove("ph7h6", teamTurn(moves(board)))

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
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
            .makeMove("Rh3h5", teamTurn(moves(board)))
            .makeMove("ph7h5", teamTurn(moves(board)))

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
            .makeMove("Pa2b3", teamTurn(moves(board)))

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
            .makeMove("Pb2a3", teamTurn(moves(board)))

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
            .makeMove("pb7a6", teamTurn(moves(board)))

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
            .makeMove("pa7b6", teamTurn(moves(board)))

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