package pieceTests

import Board

import kotlin.test.assertEquals
import kotlin.test.Test

class BishopTests {
    @Test
    fun `successful whitebishop right diagonal move`() {
        val sut = Board()
            .makeMove("Pd2d3")
            .makeMove("Bc1h6")

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
            .makeMove("Pe2e3")
            .makeMove("Bf1a6")

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
            .makeMove("Bc1a6")

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
            .makeMove("Pe2e3")
            .makeMove("Bf1a6")
            .makeMove("Ba6b7")
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
            .makeMove("Pd2d4")
            .makeMove("Pe2e3")
            .makeMove("Bf1a5")  //Ilegal move


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
            .makeMove("pd7d6")
            .makeMove("bc8h3")

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
            .makeMove("pe7e6")
            .makeMove("bf8a3")

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
            .makeMove("bc8a3")

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
            .makeMove("pb7b6")
            .makeMove("bc8a6")
            .makeMove("ba6e2")
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
            .makeMove("pd7d5")
            .makeMove("pe7e6")
            .makeMove("Bc8h5")


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