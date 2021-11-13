package pieceTests

import Board
import draw
import kotlin.test.assertEquals
import kotlin.test.Test
class RookTests {
    @Test
    fun `successful rook upwards movement`() {
        val sut = Board()
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
        assertEquals(
            "rnbqkbnr"+
                    "pppppppp"+
                    "        "+
                    "        "+
                    "P       "+
                    "R       "+
                    " PPPPPPP"+
                    " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook downwards movement`() {
        val sut = Board()
            .makeMove("pa7a5")
            .makeMove("ra8a6")
        assertEquals(
            " nbqkbnr"+
              " ppppppp"+
              "r       "+
              "p       "+
              "        "+
              "        "+
              "PPPPPPPP"+
              "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook rightwards movement`() {
        val sut = Board()
            .makeMove("Pa2a4")
            .makeMove("Ra1a3")
            .makeMove("Ra3h3")
        assertEquals(
            "rnbqkbnr"+
              "pppppppp"+
              "        "+
              "        "+
              "P       "+
              "       R"+
              " PPPPPPP"+
              " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook leftwards movement`() {
        val sut = Board()
            .makeMove("Ph2h4")
            .makeMove("Rh1h3")
            .makeMove("Rh3a3")
        assertEquals(
            "rnbqkbnr"+
              "pppppppp"+
              "        "+
              "        "+
              "       P"+
              "R       "+
              "PPPPPPP "+
              "RNBQKBN ",sut.toString()
        )
    }

    @Test
    fun `successful rook upwards eating movement`() {
        val sut = Board()
            .makeMove("pb7b5")
            .makeMove("Pa2a4")
            .makeMove("pb5a4")
            .makeMove("Ra1a4")
        assertEquals(
            "rnbqkbnr"+
              "p pppppp"+
              "        "+
              "        "+
              "R       "+
              "        "+
              " PPPPPPP"+
              " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook downwards eating movement`() {
        val sut = Board()
            .makeMove("Pb2b4")
            .makeMove("pa7a5")
            .makeMove("Pb4a5")
            .makeMove("ra8a5")
        assertEquals(
            " nbqkbnr"+
              " ppppppp"+
              "        "+
              "r       "+
              "        "+
              "        "+
              "P PPPPPP"+
              "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook rightwards eating movement`() {
        val sut = Board()
            .makeMove("ph7h5")
            .makeMove("Pa2a4")
            .makeMove("ra1a3")
            .makeMove("ph5h4")
            .makeMove("ph4h3")
            .makeMove("Ra3h3")
        assertEquals(
            "rnbqkbnr"+
              "ppppppp "+
              "        "+
              "        "+
              "P       "+
              "       R"+
              " PPPPPPP"+
              " NBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful rook leftwards eating movement`() {
        val sut = Board()
            .makeMove("Ph2h4")
            .makeMove("pa7a5")
            .makeMove("Rh1h3")
            .makeMove("pa5a4")
            .makeMove("pa4a3")
            .makeMove("Rh3a3")
        assertEquals(
            "rnbqkbnr"+
              " ppppppp"+
              "        "+
              "        "+
              "       P"+
              "R       "+
              "PPPPPPP "+
              "RNBQKBN ",sut.toString()
        )
    }

    @Test
    fun `ilegal rook upwards movement`() {
        val sut = Board()
            .makeMove("Ra1a3")
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
    fun `ilegal rook downwards movement`() {
        val sut = Board()
            .makeMove("ra8a6")
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
    fun `ilegal rook rightwards movement`() {
        val sut = Board()
            .makeMove("Ra1a3")
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
    fun `ilegal rook leftwards movement`() {
        val sut = Board()
            .makeMove("Ra8a7")
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