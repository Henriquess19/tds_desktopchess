package pieceTests

import Board

import org.junit.Test
import kotlin.test.assertEquals

class KingTests {
   @Test
   fun `king movement upward`() {
      val sut = Board()
         .makeMove("Pe2e4", teamTurn(moves(board)))
         .makeMove("Ke1e2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbnr"+
                 "pppppppp"+
                 "        "+
                 "        "+
                 "    P   "+
                 "        "+
                 "PPPPKPPP"+
                 "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king movement downward`() {
      val sut = Board()
         .makeMove("pe7e5", teamTurn(moves(board)))
         .makeMove("ke8e7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "ppppkppp"+
           "        "+
           "    p   "+
           "        "+
           "        "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `king movement rightward`() {
      val sut = Board()
         .makeMove("pe7e5", teamTurn(moves(board)))
         .makeMove("bf8d6", teamTurn(moves(board)))
         .makeMove("ke8f8", teamTurn(moves(board)))

      assertEquals(
         "rnbq knr"+
           "pppp ppp"+
           "   b    "+
           "    p   "+
           "        "+
           "        "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `king movement leftward`() {
      val sut = Board()
         .makeMove("pd7d5", teamTurn(moves(board)))
         .makeMove("qd8d6", teamTurn(moves(board)))
         .makeMove("ke8d8", teamTurn(moves(board)))

      assertEquals(
         "rnbk bnr"+
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
   fun `king movement downdiagonal leftward`() {
      val sut = Board()
         .makeMove("pd7d5", teamTurn(moves(board)))
         .makeMove("ke8d7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "pppkpppp"+
           "        "+
           "   p    "+
           "        "+
           "        "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `king movement downdiagonal rightward`() {
      val sut = Board()
         .makeMove("pf7f5", teamTurn(moves(board)))
         .makeMove("ke8f7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "pppppkpp"+
           "        "+
           "     p  "+
           "        "+
           "        "+
           "PPPPPPPP"+
           "RNBQKBNR",sut.toString()
      )
   }

   @Test
   fun `king movement updiagonal leftward`() {
      val sut = Board()
         .makeMove("Pd2d3", teamTurn(moves(board)))
         .makeMove("Ke1d2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "        "+
           "        "+
           "        "+
           "   P    "+
           "PPPKPPPP"+
           "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king movement updiagonal rightward`() {
      val sut = Board()
         .makeMove("Pf2f3", teamTurn(moves(board)))
         .makeMove("Ke1f2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbnr"+
           "pppppppp"+
           "        "+
           "        "+
           "        "+
           "     P  "+
           "PPPPPKPP"+
           "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king ilegal movement upward`() {
      val sut = Board()
         .makeMove("Ke1e2", teamTurn(moves(board)))

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
   fun `king ilegal movement downward`() {
      val sut = Board()
         .makeMove("ke8e7", teamTurn(moves(board)))

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
   fun `king ilegal movement rightward`() {
      val sut = Board()
         .makeMove("Ke1f1", teamTurn(moves(board)))

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
   fun `king ilegal movement leftward`() {
      val sut = Board()
         .makeMove("ke8d8", teamTurn(moves(board)))

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
   fun `king ilegal movement downdiagonal leftward`() {
      val sut = Board()
         .makeMove("ke8d7", teamTurn(moves(board)))

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
   fun `king ilegal movement downdiagonal rightward`() {
      val sut = Board()
         .makeMove("ke8f7", teamTurn(moves(board)))

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
   fun `king ilegal movement updiagonal leftward`() {
      val sut = Board()
         .makeMove("Ke1d2", teamTurn(moves(board)))

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
   fun `king ilegal movement updiagonal rightward`() {
      val sut = Board()
         .makeMove("Ke1d2", teamTurn(moves(board)))

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
   fun `king eating movement updiagonal rightward`() {
      val sut = Board()
         .makeMove("ph7h5", teamTurn(moves(board)))
         .makeMove("rh8h6", teamTurn(moves(board)))
         .makeMove("rh6f6", teamTurn(moves(board)))
         .makeMove("rf6f2", teamTurn(moves(board)))
         .makeMove("Ke1f2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbn "+
           "ppppppp "+
           "        "+
           "       p"+
           "        "+
           "        "+
           "PPPPPKPP"+
           "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king eating movement updiagonal leftward`() {
      val sut = Board()
         .makeMove("ph7h5", teamTurn(moves(board)))
         .makeMove("rh8h6", teamTurn(moves(board)))
         .makeMove("rh6d6", teamTurn(moves(board)))
         .makeMove("rd6d2", teamTurn(moves(board)))
         .makeMove("Ke1d2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbn "+
           "ppppppp "+
           "        "+
           "       p"+
           "        "+
           "        "+
           "PPPKPPPP"+
           "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king eating movement upward`() {
      val sut = Board()
         .makeMove("ph7h5", teamTurn(moves(board)))
         .makeMove("rh8h6", teamTurn(moves(board)))
         .makeMove("rh6e6", teamTurn(moves(board)))
         .makeMove("re6e2", teamTurn(moves(board)))
         .makeMove("Ke1e2", teamTurn(moves(board)))

      assertEquals(
         "rnbqkbn "+
           "ppppppp "+
           "        "+
           "       p"+
           "        "+
           "        "+
           "PPPPKPPP"+
           "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king eating movement downward`() {
      val sut = Board()
         .makeMove("Ph2h4", teamTurn(moves(board)))
         .makeMove("Rh1h3", teamTurn(moves(board)))
         .makeMove("Rh3e3", teamTurn(moves(board)))
         .makeMove("Re3e7", teamTurn(moves(board)))
         .makeMove("ke8e7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "ppppkppp"+
           "        "+
           "        "+
           "       P"+
           "        "+
           "PPPPPPP "+
           "RNBQKBN ",sut.toString()
      )
   }

   @Test
   fun `king eating movement rightdiagonal downward`() {
      val sut = Board()
         .makeMove("Ph2h4", teamTurn(moves(board)))
         .makeMove("Rh1h3", teamTurn(moves(board)))
         .makeMove("Rh3f3", teamTurn(moves(board)))
         .makeMove("Rf3f7", teamTurn(moves(board)))
         .makeMove("ke8f7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "pppppkpp"+
           "        "+
           "        "+
           "       P"+
           "        "+
           "PPPPPPP "+
           "RNBQKBN ",sut.toString()
      )
   }

   @Test
   fun `king eating movement leftdiagonal downward`() {
      val sut = Board()
         .makeMove("Ph2h4", teamTurn(moves(board)))
         .makeMove("Rh1h3", teamTurn(moves(board)))
         .makeMove("Rh3d3", teamTurn(moves(board)))
         .makeMove("Rd3d7", teamTurn(moves(board)))
         .makeMove("ke8d7", teamTurn(moves(board)))

      assertEquals(
         "rnbq bnr"+
           "pppkpppp"+
           "        "+
           "        "+
           "       P"+
           "        "+
           "PPPPPPP "+
           "RNBQKBN ",sut.toString()
      )
   }
}