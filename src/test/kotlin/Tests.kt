import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTests {
    @Test
    fun `Initial position Board`() {
        val sut = Board()
        assertEquals(
            "rnbqkbnr" +
                    "pppppppp" +
                    "        ".repeat(4) +
                    "PPPPPPPP" +
                    "RNBQKBNR", sut.toString()
        )
        draw(sut)
    }

    @Test
    fun `MakeMove in Board`() {
        val sut = Board()
            .makeMove("Pe2e4", teamTurn(moves(board)))
            .makeMove("Pe7e5", teamTurn(moves(board)))
            .makeMove("Nb1c3", teamTurn(moves(board)))

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
        draw(sut)
    }

    //TODO -> TESTES DAS STRINGS DE MOVE NAO COMPLETAS

}
