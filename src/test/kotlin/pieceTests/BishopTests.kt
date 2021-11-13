package pieceTests

import Board

import kotlin.test.assertEquals
import kotlin.test.Test

class BishopTests {
    @Test
    fun `successful whitebishop right diagonal move`() {
        val sut = Board()
            .makeMove("Pd2d3", teamTurn(moves(board)))
            .makeMove("Bc1h6", teamTurn(moves(board)))

        assertEquals(
            "rnbqkbnr"+
              "pppppppp"+
              "       B"+
              "        "+
              "        "+
              "   P    "+
              "PPP PPPP"+
              "RN QKBNR",sut.toString()
        )
    }

    @Test
    fun `successful whitebishop left diagonal move`() {
        val sut = Board()
            .makeMove("Pe2e3", teamTurn(moves(board)))
            .makeMove("Bf1a6", teamTurn(moves(board)))

        assertEquals(
            "rnbqkbnr"+
              "pppppppp"+
              "B       "+
              "        "+
              "        "+
              "    P   "+
              "PPPP PPP"+
              "RNBQK NR",sut.toString()
        )
    }

    @Test
    fun `ilegal whitebishop move`() {
        val sut = Board()
            .makeMove("Bc1a6", teamTurn(moves(board)))

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
    fun `eating a piece with whitebishop`() {
        val sut = Board()
            .makeMove("Pe2e3", teamTurn(moves(board)))
            .makeMove("Bf1a6", teamTurn(moves(board)))
            .makeMove("Ba6b7", teamTurn(moves(board)))
        assertEquals(
            "rnbqkbnr"+
              "pBpppppp"+
              "        "+
              "        "+
              "        "+
              "    P   "+
              "PPPP PPP"+
              "RNBQK NR",sut.toString()
        )
    }

    @Test
    fun `whitebishop move encounter`() {
        val sut = Board()
            .makeMove("Pd2d4", teamTurn(moves(board)))
            .makeMove("Pe2e3", teamTurn(moves(board)))
            .makeMove("Bf1a5", teamTurn(moves(board)))  //Ilegal move


        assertEquals(
            "rnbqkbnr"+
              "pppppppp"+
              "        "+
              "        "+
              "   P    "+
              "    P   "+
              "PPP  PPP"+
              "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful blackbishop right diagonal move`() {
        val sut = Board()
            .makeMove("pd7d6", teamTurn(moves(board)))
            .makeMove("bc8h3", teamTurn(moves(board)))

        assertEquals(
            "rn qkbnr"+
              "ppp pppp"+
              "   p    "+
              "        "+
              "        "+
              "       b"+
              "PPPPPPPP"+
              "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `successful blackbishop left diagonal move`() {
        val sut = Board()
            .makeMove("pe7e6", teamTurn(moves(board)))
            .makeMove("bf8a3", teamTurn(moves(board)))

        assertEquals(
            "rnbqk nr"+
                    "pppp ppp"+
                    "    p   "+
                  "        "+
                  "        "+
                  "b       "+
                  "PPPPPPPP"+
                  "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `ilegal blackbishop move`() {
        val sut = Board()
            .makeMove("bc8a3", teamTurn(moves(board)))

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
    fun `eating a piece with blackbishop`() {
        val sut = Board()
            .makeMove("pb7b6", teamTurn(moves(board)))
            .makeMove("bc8a6", teamTurn(moves(board)))
            .makeMove("ba6e2", teamTurn(moves(board)))
        assertEquals(
            "rn qkbnr"+
              "p pppppp"+
              " p      "+
              "        "+
              "        "+
              "        "+
              "PPPPbPPP"+
              "RNBQKBNR",sut.toString()
        )
    }

    @Test
    fun `blackbishop move encounter`() {
        val sut = Board()
            .makeMove("pd7d5", teamTurn(moves(board)))
            .makeMove("pe7e6", teamTurn(moves(board)))
            .makeMove("Bc8h5", teamTurn(moves(board)))


        assertEquals(
            "rnbqkbnr"+
              "ppp  ppp"+
              "    p   "+
              "   p    "+
              "        "+
              "        "+
              "PPPPPPPP"+
              "RNBQKBNR",sut.toString()
        )
    }
}