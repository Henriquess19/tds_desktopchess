package pieceTests

import Board

import org.junit.Test
import teamTurn
import toMove
import kotlin.test.assertEquals

class KingTests {
   @Test
   fun `king movement upward`() {
      val sut = Board()
      sut
         .makeMove("Pe2e4".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1e2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("pe7e5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8e7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("pe7e5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("bf8d6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8f8".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("pd7d5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("qd8d6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8d8".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("pd7d5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8d7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("pf7f5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8f7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Pd2d3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Pf2f3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1f2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ke1e2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ke8e7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ke1f1".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ke8d8".toMove(), teamTurn(sut.getMoveList(),null))
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
      sut
         .makeMove("ke8d7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ke8f7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ke1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ke1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh6f6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rf6f2".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1f2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh6d6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rd6d2".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1d2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("ph7h5".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh8h6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("rh6e6".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("re6e2".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Ke1e2".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ph2h4".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh1h3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh3e3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Re3e7".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8e7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ph2h4".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh1h3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh3f3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rf3f7".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8f7".toMove(), teamTurn(sut.getMoveList(),null))

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
      sut
         .makeMove("Ph2h4".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh1h3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rh3d3".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("Rd3d7".toMove(), teamTurn(sut.getMoveList(),null))
         .makeMove("ke8d7".toMove(), teamTurn(sut.getMoveList(),null))

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