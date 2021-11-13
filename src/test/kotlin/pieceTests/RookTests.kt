package pieceTests

import Board
import teamTurn
import toMove
import kotlin.test.assertEquals
import kotlin.test.Test
class RookTests {
    @Test
    fun `successful rook upwards movement`() {
        val sut = Board()
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("pa7a5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("ra8a6".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Ph2h4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Rh1h3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Rh3a3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("pb7b5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("pb5a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Ra1a4".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Pb2b4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("pa7a5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Pb4a5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("ra8a5".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Pa2a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("ra1a3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("ph5h4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("ph4h3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Ra3h3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Ph2h4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("pa7a5".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Rh1h3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("pa5a4".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("pa4a3".toMove(), teamTurn(sut.getMoveList()))
            .makeMove("Rh3a3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("ra8a6".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Ra1a3".toMove(), teamTurn(sut.getMoveList()))
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
        sut
            .makeMove("Ra8a7".toMove(), teamTurn(sut.getMoveList()))
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