package pieceTests

import Board
import teamTurn
import toMove

import kotlin.test.assertEquals
import kotlin.test.Test

class BishopTests {
    @Test
    fun `successful whitebishop right diagonal move`() {
        val sut = Board()
        sut
            .makeMove("Pd2d3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Bc1h6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Pe2e3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Bf1a6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Bc1a6".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("Pe2e3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Bf1a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Ba6b7".toMove(), teamTurn(sut.getMoveList(),null))
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
        sut
            .makeMove("Pd2d4".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Pe2e3".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Bf1a5".toMove(), teamTurn(sut.getMoveList(),null))  //Ilegal move


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
        sut
            .makeMove("pd7d6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("bc8h3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pe7e6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("bf8a3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("bc8a3".toMove(), teamTurn(sut.getMoveList(),null))

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
        sut
            .makeMove("pb7b6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("bc8a6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("ba6e2".toMove(), teamTurn(sut.getMoveList(),null))
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
        sut
            .makeMove("pd7d5".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("pe7e6".toMove(), teamTurn(sut.getMoveList(),null))
            .makeMove("Bc8h5".toMove(), teamTurn(sut.getMoveList(),null))


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