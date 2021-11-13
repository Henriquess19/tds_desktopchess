package pieceTests

import Board
import kotlin.test.assertEquals
import kotlin.test.Test
class RookTests {
    @Test
    fun `successful rook upwards movement`() {
        val sut = Board()
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
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
            .makeMove("pa7a5", teamTurn(moves(board)))
            .makeMove("ra8a6", teamTurn(moves(board)))
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
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("Ra1a3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
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
            .makeMove("Ph2h4", teamTurn(moves(board)))
            .makeMove("Rh1h3", teamTurn(moves(board)))
            .makeMove("Rh3a3", teamTurn(moves(board)))
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
            .makeMove("pb7b5", teamTurn(moves(board)))
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("pb5a4", teamTurn(moves(board)))
            .makeMove("Ra1a4", teamTurn(moves(board)))
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
            .makeMove("Pb2b4", teamTurn(moves(board)))
            .makeMove("pa7a5", teamTurn(moves(board)))
            .makeMove("Pb4a5", teamTurn(moves(board)))
            .makeMove("ra8a5", teamTurn(moves(board)))
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
            .makeMove("ph7h5", teamTurn(moves(board)))
            .makeMove("Pa2a4", teamTurn(moves(board)))
            .makeMove("ra1a3", teamTurn(moves(board)))
            .makeMove("ph5h4", teamTurn(moves(board)))
            .makeMove("ph4h3", teamTurn(moves(board)))
            .makeMove("Ra3h3", teamTurn(moves(board)))
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
            .makeMove("Ph2h4", teamTurn(moves(board)))
            .makeMove("pa7a5", teamTurn(moves(board)))
            .makeMove("Rh1h3", teamTurn(moves(board)))
            .makeMove("pa5a4", teamTurn(moves(board)))
            .makeMove("pa4a3", teamTurn(moves(board)))
            .makeMove("Rh3a3", teamTurn(moves(board)))
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
            .makeMove("Ra1a3", teamTurn(moves(board)))
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
            .makeMove("ra8a6", teamTurn(moves(board)))
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
            .makeMove("Ra1a3", teamTurn(moves(board)))
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
            .makeMove("Ra8a7", teamTurn(moves(board)))
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