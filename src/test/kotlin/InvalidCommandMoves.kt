import Board
import org.junit.Test
import teamTurn
import toMove
import kotlin.test.assertEquals

class InvalidCommandMoves {

    @Test(expected = IllegalArgumentException::class)
    fun `move empty`() {
        val sut = Board()
        sut
            .makeMove("".toMove(),  teamTurn(sut.getMoveList(),null))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `move with only one character`() {
        val sut = Board()
        sut
            .makeMove("p".toMove(),  teamTurn(sut.getMoveList(),null))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `move command to big`() {
        val sut = Board()
        sut
            .makeMove("pa8b47h".toMove(),  teamTurn(sut.getMoveList(),null))
    }
}