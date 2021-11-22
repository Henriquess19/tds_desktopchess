
import domain.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardStateTests {
    @Test
    fun `Initial position Board`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))
        assertEquals(
            "rnbqkbnr" +
                    "pppppppp" +
                    "        ".repeat(4) +
                    "PPPPPPPP" +
                    "RNBQKBNR", sut.toString()
        )
    }


    @Test
    fun `MakeMove in Board`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))
        sut
            .makeMove("Pe2e4".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Pe7e5".toMove(), teamTurn(sut.movesList,null)).first
            .makeMove("Nb1c3".toMove(), teamTurn(sut.movesList,null))

        assertEquals(
            "rnbqkbnr"+
                    "pppp ppp"+
                    "        "+
                    "    p   "+
                    "    P   "+
                    "  N     "+
                    "PPPP PPP"+
                    "R BQKBNR",sut.toString()
        )
    }
    @Test
    fun `Position contains the piece`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))

        assertEquals(sut.containsPiece(Positions(Lines.L1, Columns.CA)), true)
        assertEquals(sut.containsPiece(Positions(Lines.L8, Columns.CA)), true)
        assertEquals(sut.containsPiece(Positions(Lines.L7, Columns.CB)), true)
    }
    @Test
    fun `Position not contains the piece`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))

        assertEquals(sut.containsPiece(Positions(Lines.L4, Columns.CA)), false)
        assertEquals(sut.containsPiece(Positions(Lines.L5, Columns.CC)), false)
        assertEquals(sut.containsPiece(Positions(Lines.L3, Columns.CD)), false)
    }
    @Test
    fun `get the piece contained in that position`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))

        assertEquals(sut.getPiece(Positions(Lines.L1, Columns.CA)), Piece(Team.WHITE,TypeOfPieces.R,'R') )
        assertEquals(sut.getPiece(Positions(Lines.L2, Columns.CA)), Piece(Team.WHITE,TypeOfPieces.P,'P') )
        assertEquals(sut.getPiece(Positions(Lines.L3, Columns.CA)), null)
        assertEquals(sut.getPiece(Positions(Lines.L5, Columns.CA)), null)
    }
    @Test
    fun `check if the piece is from the team who is playing`() {
        val sut = BoardState(MovesList("-1", mutableListOf()))

        assertEquals(sut.pieceTeamCheck(Move("Pa8a7"), Team.BLACK).data,ValidMovement)
    }

}