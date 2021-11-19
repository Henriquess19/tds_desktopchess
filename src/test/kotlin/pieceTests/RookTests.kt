package pieceTests

import domain.*
import kotlin.test.assertEquals
import kotlin.test.Test

class RookTests {
    @Test
    fun `successful rook upwards movement`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pa7a5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra8a6".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ph2h4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh3a3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("pb7b5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pb5a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra1a4".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pb2b4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pa7a5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pb4a5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra8a5".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ph7h5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pa2a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ra1a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ph5h4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("ph4h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Ra3h3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ph2h4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pa7a5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh1h3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pa5a4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("pa4a3".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Rh3a3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("ra8a6".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ra1a3".toMove(), teamTurn(sut.movesList,null))
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
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Ra8a7".toMove(), teamTurn(sut.movesList,null))
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