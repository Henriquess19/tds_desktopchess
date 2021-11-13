package pieceTests

import Board
import org.junit.Test
import kotlin.test.assertEquals

class QueenTests {
   @Test
   fun `legal queen downward move `() {
      val sut = Board()
         .makeMove("pd7d5")
         .makeMove("qd8d6")


      assertEquals(
         "rnb kbnr"+
                 "ppp pppp"+
                 "   q    "+
                 "   p    "+
                 "        "+
                 "        "+
                 "PPPPPPPP"+
                 "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen upward move `() {
      val sut = Board()
         .makeMove("Pd2d4")
         .makeMove("Qd1d3")


      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "        "+
           "        "+
           "   P    "+
           "   Q    "+
           "PPP PPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen rightward move `() {
      val sut = Board()
         .makeMove("Pd2d4")
         .makeMove("Qd1d3")
         .makeMove("Qd3h3")

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "        "+
           "        "+
           "   P    "+
           "       Q"+
           "PPP PPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen leftward move `() {
      val sut = Board()
         .makeMove("Pd2d4")
         .makeMove("Qd1d3")
         .makeMove("Qd3a3")

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "        "+
           "        "+
           "   P    "+
           "Q       "+
           "PPP PPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen upleftdiagonal move `() {
      val sut = Board()
         .makeMove("Pd2d4")
         .makeMove("Qd1d3")
         .makeMove("Qd3a6")

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "Q       "+
           "        "+
           "   P    "+
           "        "+
           "PPP PPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen uprightdiagonal move `() {
      val sut = Board()
         .makeMove("Pd2d4")
         .makeMove("Qd1d3")
         .makeMove("Qd3g6")

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "      Q "+
           "        "+
           "   P    "+
           "        "+
           "PPP PPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen downrightdiagonal move `() {
      val sut = Board()
         .makeMove("pd7d5")
         .makeMove("qd8d6")
         .makeMove("qd6g3")

      assertEquals(
         "rnb kbnr"+
           "ppp pppp"+
           "        "+
           "   p    "+
           "        "+
           "      q "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `legal queen downleftdiagonal move `() {
      val sut = Board()
         .makeMove("pd7d5")
         .makeMove("qd8d6")
         .makeMove("qd6a3")

      assertEquals(
         "rnb kbnr"+
           "ppp pppp"+
           "        "+
           "   p    "+
           "        "+
           "q       "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `ilegal queen downward move `() {
      val sut = Board()
         .makeMove("qd8d6")


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
   fun `ilegal queen upward move `() {
      val sut = Board()
         .makeMove("qd1d3")


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
   fun `ilegal queen rightward move `() {
      val sut = Board()
         .makeMove("qd8e8")

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
   fun `ilegal queen leftward move `() {
      val sut = Board()
         .makeMove("qd8e8")

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
   fun `ilegal queen upleftdiagonal move `() {
      val sut = Board()
         .makeMove("Qd1c2")

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
   fun `ilegal queen uprightdiagonal move `() {
      val sut = Board()
         .makeMove("Qd1e2")

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
   fun `ilegal queen downrightdiagonal move `() {
      val sut = Board()
         .makeMove("qd8e7")

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
   fun `ilegal queen downleftdiagonal move `() {
      val sut = Board()
         .makeMove("qd8c7")

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
   fun `queen downward eat move `() {
      val sut = Board()
         .makeMove("pc7c6")
         .makeMove("qd8a5")
         .makeMove("qa5a2")

      assertEquals(
         "rnb kbnr"+
           "pp ppppp"+
           "  p     "+
           "        "+
           "        "+
           "        "+
           "qPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `queen upward eat move `() {
      val sut = Board()
         .makeMove("pe2e3")
         .makeMove("Qd1h5")
         .makeMove("Qh5h7")


      assertEquals(
         "rnbqkbnr"+
           "pppppppQ"+
           "        "+
           "        "+
           "        "+
           "    P   "+
           "PPPP PPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `queen rightward eat move `() {
      val sut = Board()
         .makeMove("pc7c6")
         .makeMove("qd8a5")
         .makeMove("qa5a2")
         .makeMove("qa2b2")

      assertEquals(
         "rnb kbnr"+
           "pp ppppp"+
           "  p     "+
           "        "+
           "        "+
           "        "+
           " qPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `queen leftward eat move `() {
      val sut = Board()
         .makeMove("Pe2e3")
         .makeMove("Qd1h5")
         .makeMove("Qh5h7")
         .makeMove("Qh7g7")

      assertEquals(
         "rnbqkbnr"+
           "ppppppQ "+
           "        "+
           "        "+
           "        "+
           "    P   "+
           "PPPP PPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `queen upleftdiagonal eat move `() {
      val sut = Board()
         .makeMove("Pe2e3")
         .makeMove("Qd1h5")
         .makeMove("Qh5f7")

      assertEquals(
         "rnbqkbnr"+
           "pppppQpp"+
           "        "+
           "        "+
           "        "+
           "    P   "+
           "PPPP PPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `queen uprightdiagonal eat move `() {
      val sut = Board()
         .makeMove("Pc2c3")
         .makeMove("Qd1a4")
         .makeMove("Qa4d7")

      assertEquals(
         "rnbqkbnr"+
           "pppQpppp"+
           "        "+
           "        "+
           "        "+
           "  P     "+
           "PP PPPPP"+
           "RNB KBNR",sut.toString()
      )
   }

   @Test
   fun `queen downrightdiagonal eat move `() {
      val sut = Board()
         .makeMove("pc7c6")
         .makeMove("qd8a5")
         .makeMove("qa5d2")


      assertEquals(
         "rnb kbnr"+
           "pp ppppp"+
           "  p     "+
           "        "+
           "        "+
           "        "+
           "PPPqPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `queen downleftdiagonal eat move `() {
      val sut = Board()
         .makeMove("pe7e6")
         .makeMove("qd8h4")
         .makeMove("qh4f2")

      assertEquals(
         "rnb kbnr"+
           "pppp ppp"+
           "    p   "+
           "        "+
           "        "+
           "        "+
           "PPPPPqPP"+
           "RNBQKBNR",sut.toString()
      )
   }
}