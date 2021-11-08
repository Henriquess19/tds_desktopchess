import Board
import kotlin.test.assertEquals
import kotlin.test.Test

class BishopTests {
    @Test
    fun `successful bishop moves both sides`() {
        val sut = Board()
            .makeMove("pb7b6")
            .makeMove("bc8a5")

            .makeMove("Pd2d3")
            .makeMove("Bc3h6")


        assertEquals(
            "rn qkbnr"+
                    "  pppppp"+
                    "bp      "+
                    "       B"+
                    "        "+
                    "   P    "+
                    "PPP PPPP"+
                    "RNBQKBNR",sut.toString()
        )
    }
}