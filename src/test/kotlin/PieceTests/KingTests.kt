package PieceTests

import Board
import org.junit.Test
import kotlin.test.assertEquals

class KingTests {
   @Test
   fun `both kings move return king moved`() {
      val sut = Board()
         .makeMove("pe7e5")
         .makeMove("Pe2e4")
         .makeMove("ke8e7")
         .makeMove("Ke1e2")
      assertEquals(
         "rnbq bnr"+
                 "ppppkppp"+
                 "        "+
                 "    p   "+
                 "    P   "+
                 "        "+
                 "PPPPKPPP"+
                 "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `king move to eat diagonal piece`() {
      val sut = Board()
         .makeMove("pe7e5")
         .makeMove("Pe2e4")
         .makeMove("pd7d5")
         .makeMove("Ke1e2")
         .makeMove("pd5d4")
         .makeMove("Ke2e3")
         .makeMove("Ke3d4") //Eating Movement

      assertEquals(
         "rnbqkbnr"+
                 "ppp  ppp"+
                 "        "+
                 "    p   "+
                 "   KP   "+
                 "        "+
                 "PPPP PPP"+
                 "RNBQ BNR",sut.toString()
      )
   }

   @Test
   fun `wrong king moves`() {
      val sut = Board()
         .makeMove("ke8e7") //Ilegal Movement
         .makeMove("pe7e5")
         .makeMove("ke8e6") //Ilegal Movement
         .makeMove("ke8f8") //Ilegal Movement
         .makeMove("ke8d7") //Ilegal Movement
         .makeMove("ke8d8") //Ilegal Movement

      assertEquals(
         "rnbqkbnr"+
                 "pppp ppp"+
                 "        "+
                 "    p   "+
                 "        "+
                 "        "+
                 "PPPPPPPP"+
                 "RNBQKBNR",sut.toString()
      )
   }
}