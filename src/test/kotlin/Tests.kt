
import domain.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardStateTests {
    @Test
    fun `Initial position Board`() {
        val sut = BoardState(MovesList(null, mutableListOf()))
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
        val sut = BoardState(MovesList(null, mutableListOf()))
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

    //TODO -> TESTES DAS STRINGS DE MOVE NAO COMPLETAS

}
