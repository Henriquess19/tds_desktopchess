

fun main(){
    val sut = Board()
        .makeMove("ph7h5")
        .makeMove("ph5h4")//Ilegal move
        /*
        .makeMove("pb7b6")
        .makeMove("bc8a6")
        .makeMove("ba6b3")  //Ilegal move
        .makeMove("Bc1c3")  //Ilegal move
        .makeMove("Pd2d3")
        .makeMove("Bc1h6")
        .makeMove("Bh6e4")  //Ilegal move
         */
        BoardConsoleDraw(sut).draw()

}
