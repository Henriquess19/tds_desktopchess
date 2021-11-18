
import domain.*
import org.junit.Test


class InvalidCommandMoves {

    @Test(expected = IllegalArgumentException::class)
    fun `move empty`() {
        val sut = BoardState(MovesList(null, mutableListOf()))
        sut
            .makeMove("".toMove(),  teamTurn(sut.movesList,null))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `move with only one character`() {
        val sut = BoardState(MovesList(null, mutableListOf()))
        sut
            .makeMove("p".toMove(),  teamTurn(sut.movesList,null))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `move command to big`() {
        val sut = BoardState(MovesList(null, mutableListOf()))
        sut
            .makeMove("pa8b47h".toMove(),  teamTurn(sut.movesList,null))
    }
}