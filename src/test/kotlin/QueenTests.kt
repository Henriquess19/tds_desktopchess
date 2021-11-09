import org.junit.Test
import kotlin.test.assertEquals

class QueenTests {
   @Test
   fun `legal queen move `() {
      val sut = Board()
         .makeMove("pd7d5")
         .makeMove("qd8d6")
         .makeMove("Pc2c3")
         .makeMove("Qd1a4")

      assertEquals(
         "rnb kbnr"+
                 "ppp pppp"+
                 "   q    "+
                 "   p    "+
                 "Q       "+
                 "  P     "+
                 "PP PPPPP"+
                 "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `ilegal queen move `() {
      val sut = Board()
         .makeMove("qd8d6") //Ilegal movement
         .makeMove("pd7d5")
         .makeMove("qd8d6")
         .makeMove("qd6a7") //Ilegal movement
         .makeMove("Pe2e3")
         .makeMove("Pg2g4")
         .makeMove("Qd1h4") //Ilegal movement

      assertEquals(
         "rnb kbnr"+
           "ppp pppp"+
           "   q    "+
           "   p    "+
           "      P "+
           "    P   "+
           "PPPP P P"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `queen eating piece `() {
      val sut = Board()
         .makeMove("Pe2e3")
         .makeMove("pf7f6")
         .makeMove("Qd1h5")
         .makeMove("Qh5e8") //Eating movement

      assertEquals(
         "rnbqQbnr"+
           "ppppp pp"+
           "     p  "+
           "        "+
           "        "+
           "    P   "+
           "PPPP PPP"+
           "RNB KBNR",sut.toString()
      )
   }
}