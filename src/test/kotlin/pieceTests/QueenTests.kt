package pieceTests

import domain.*
import org.junit.Test
import kotlin.test.assertEquals

class QueenTests {
   @Test
   fun `legal queen downward move `() {
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pd7d5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8d6".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pd2d4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1d3".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pd2d4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1d3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd3h3".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pd2d4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1d3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd3a3".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pd2d4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1d3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd3a6".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pd2d4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1d3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd3g6".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
      sut
         .makeMove("pd7d5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8d6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd6g3".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pd7d5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8d6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd6a3".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd8d6".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd1d3".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd8e8".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd8e8".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Qd1c2".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Qd1e2".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd8e7".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("qd8c7".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))

      sut
         .makeMove("pc7c6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8a5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qa5a2".toMove(), teamTurn(sut.movesList,null))
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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pe2e3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1h5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qh5h7".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pc7c6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8a5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qa5a2".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qa2b2".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pe2e3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1h5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qh5h7".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qh7g7".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pe2e3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1h5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qh5f7".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("Pc2c3".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qd1a4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("Qa4d7".toMove(), teamTurn(sut.movesList,null))

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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pc7c6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8a5".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qa5d2".toMove(), teamTurn(sut.movesList,null))


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
      val sut = BoardState(MovesList("-1", mutableListOf()))
      sut
         .makeMove("pe7e6".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qd8h4".toMove(), teamTurn(sut.movesList,null)).first
         .makeMove("qh4f2".toMove(), teamTurn(sut.movesList,null))

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