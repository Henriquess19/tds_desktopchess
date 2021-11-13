package pieceTests

import Board
import draw
import kotlin.test.Test
import kotlin.test.assertEquals

class PawnTests {
    @Test
    fun `legal pawn 1 block upward move`() {
        val sut = Board()
            .makeMove("Pa2a3")

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
            .makeMove("Pa2a4")

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
            .makeMove("pa7a6")

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
            .makeMove("pa7a5")

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
            .makeMove("ph7h5")
            .makeMove("rh8h6")
            .makeMove("rh6a6")
            .makeMove("ra6a3")
            .makeMove("Pb2a3")

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
            .makeMove("ph7h5")
            .makeMove("rh8h6")
            .makeMove("rh6a6")
            .makeMove("ra6a3")
            .makeMove("ra3b3")
            .makeMove("Pa2b3")

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
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")
            .makeMove("Rh3h6")
            .makeMove("pg7h6")

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
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")
            .makeMove("Rh3h6")
            .makeMove("Rh6g6")
            .makeMove("ph7g6")

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
            .makeMove("ph7h5")
            .makeMove("rh8h6")
            .makeMove("rh6a6")
            .makeMove("ra6a3")
            .makeMove("Pa2a3")

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
            .makeMove("ph7h5")
            .makeMove("rh8h6")
            .makeMove("rh6a6")
            .makeMove("ra6a4")
            .makeMove("Pa2a4")
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
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")
            .makeMove("Rh3h6")
            .makeMove("ph7h6")

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
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")
            .makeMove("Rh3h5")
            .makeMove("ph7h5")

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
            .makeMove("Pa2b3")

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
            .makeMove("Pb2a3")

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
            .makeMove("pb7a6")

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
            .makeMove("pa7b6")

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