package pieceTests

import Board

import org.junit.Test
import kotlin.test.assertEquals

class KingTests {
   @Test
   fun `king movement upward`() {
      val sut = Board()
         .makeMove("Pe2e4")
         .makeMove("Ke1e2")

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
         .makeMove("pe7e5")
         .makeMove("ke8e7")

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
         .makeMove("pe7e5")
         .makeMove("bf8d6")
         .makeMove("ke8f8")

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
         .makeMove("pd7d5")
         .makeMove("qd8d6")
         .makeMove("ke8d8")

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
         .makeMove("pd7d5")
         .makeMove("ke8d7")

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
         .makeMove("pf7f5")
         .makeMove("ke8f7")

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
         .makeMove("Pd2d3")
         .makeMove("Ke1d2")

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
         .makeMove("Pf2f3")
         .makeMove("Ke1f2")

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
         .makeMove("Ke1e2")

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
         .makeMove("ke8e7")

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
         .makeMove("Ke1f1")

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
         .makeMove("ke8d8")

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
         .makeMove("ke8d7")

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
         .makeMove("ke8f7")

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
         .makeMove("Ke1d2")

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
         .makeMove("Ke1d2")

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
         .makeMove("ph7h5")
         .makeMove("rh8h6")
         .makeMove("rh6f6")
         .makeMove("rf6f2")
         .makeMove("Ke1f2")

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
         .makeMove("ph7h5")
         .makeMove("rh8h6")
         .makeMove("rh6d6")
         .makeMove("rd6d2")
         .makeMove("Ke1d2")

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
         .makeMove("ph7h5")
         .makeMove("rh8h6")
         .makeMove("rh6e6")
         .makeMove("re6e2")
         .makeMove("Ke1e2")

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
         .makeMove("Ph2h4")
         .makeMove("Rh1h3")
         .makeMove("Rh3e3")
         .makeMove("Re3e7")
         .makeMove("ke8e7")

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
         .makeMove("Ph2h4")
         .makeMove("Rh1h3")
         .makeMove("Rh3f3")
         .makeMove("Rf3f7")
         .makeMove("ke8f7")

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
         .makeMove("Ph2h4")
         .makeMove("Rh1h3")
         .makeMove("Rh3d3")
         .makeMove("Rd3d7")
         .makeMove("ke8d7")

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