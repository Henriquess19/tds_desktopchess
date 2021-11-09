import Board
import kotlin.test.assertEquals
import kotlin.test.Test

class BishopTests {
    @Test
    fun `successful bishop moves both sides`() {
        val sut = Board()
            .makeMove("pb7b6")
            .makeMove("bc8a6")
            .makeMove("Pd2d3")
            .makeMove("Bc1h6")


        assertEquals(
            "rn qkbnr"+
                    "p pppppp"+
                    "bp     B"+
                    "        "+
                    "        "+
                    "   P    "+
                    "PPP PPPP"+
                    "RN QKBNR",sut.toString()
        )
    }

    @Test
    fun `ilegal bishop moves both sides`() {
        val sut = Board()
            .makeMove("bc8c6")  //Ilegal move
            .makeMove("pb7b6")
            .makeMove("bc8a6")
            .makeMove("ba6b3")  //Ilegal move
            .makeMove("Bc1c3")  //Ilegal move
            .makeMove("Pd2d3")
            .makeMove("Bc1h6")
            .makeMove("Bh6e4")  //Ilegal move


        assertEquals(
              "rn qkbnr"+
                      "p pppppp"+
                      "bp     B"+
                      "        "+
                      "        "+
                      "   P    "+
                      "PPP PPPP"+
                      "RN QKBNR",sut.toString()
        )
    }

    @Test
    fun `eating a piece with bishop on both sides`() {
        val sut = Board()
            .makeMove("pb7b6")
            .makeMove("bc8a6")
            .makeMove("ba6e2")  //Eating movement
            .makeMove("Bf1e2")  //Eating movement
            .makeMove("Be2h5")
            .makeMove("Bh5f7")  //Eating movement
        assertEquals(
              "rn qkbnr"+
                      "p pppBpp"+
                      " p      "+
                      "        "+
                      "        "+
                      "        "+
                      "PPPP PPP"+
                      "RNBQK NR",sut.toString()
        )
    }
}