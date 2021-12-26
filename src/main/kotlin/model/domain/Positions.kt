package model.domain

enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}

const val BOARD_SIDE = 8

val columns = arrayOf('A','B','C','D','E','F','G','H')
fun isValid(value: Int) = value in 0 until BOARD_SIDE
fun isValid(char:Char) = columns.contains(char)
fun Int.toColumn():Columns {
    require(isValid(this))
    return Columns.values()[this]
}
fun Char.toColumn():Columns{
    val char = this.uppercaseChar()
    require(isValid(char = char))
    val col = "C$char"
    return Columns.valueOf(col)
}


/**
 * Representation of the line in a board of chess
 */
enum class Lines{L1,L2,L3,L4,L5,L6,L7,L8}

fun Int.toLine(): Lines {
    require(isValid(this))
    return Lines.values()[this]
}
fun Char.toLine():Lines{
    val number = this.toString().toInt() - 1
    require(isValid(value = number))
    return Lines.values()[number]
}
/**
 * Represents the position of the piece
 * @property lines  The line where the piece is
 * @property column  The column where the piece is
 */
data class Positions(val line: Lines, val column: Columns)