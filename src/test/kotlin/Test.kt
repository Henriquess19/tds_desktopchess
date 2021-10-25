import org.junit.Test
import kotlin.test.assertEquals
class Test {
        @Test
        fun `Initial position Board`() {
            val sut = Board().board
            assertEquals(
                "rnbqkbnr" +
                        "pppppppp" +
                        " ".repeat(4) +
                        "PPPPPPPP" +
                        "RNBQKBNR", sut.toString()
            )
        }
    /*
    @Test
    fun `MakeMove in Board`() {
        val sut = Board().makeMove("Pe2e4").makeMove("Pe7e5").makeMove("Nb1c3")
        assertEquals(
            "rnbqkbnr"+
                    "pppp ppp"+
                    " "+
                    " p "+
                    " P "+
                    " N "+
                    "PPPP PPP"+
                    "R BQKBNR", sut.toString() )
    }

     */
}