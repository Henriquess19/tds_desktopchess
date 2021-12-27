package model.domain

/**
 * Representation of the columns in a board of chess
 */
enum class Columns{CA,CB,CC,CD,CE,CF,CG,CH}

/**
 * The size of the side of the board
 */
const val BOARD_SIDE = 8

/**
 * Array of the possible letters to the columns
 */
val columns = arrayOf('A','B','C','D','E','F','G','H')

/**
 * Verify if the int is valid
 * @param value to be verified
 * @return if its true is valid and false if it´s not
 */
fun isValid(value: Int) = value in 0 until BOARD_SIDE

/**
 * Verify if the char is valid
 * @param char to be verified
 * @return if its true is valid and false if it´s not
 */
fun isValid(char:Char) = columns.contains(char)
/**
 * Takes the int and transform it to a valid column
 * @return The column corresponding
 */
fun Int.toColumn():Columns {
    require(isValid(this))
    return Columns.values()[this]
}
/**
 * Takes the char and transform it to a valid column
 * @return The column corresponding
 */
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

/**
 * Takes the int and transform it to a valid line
 * @return The line corresponding
 */
fun Int.toLine(): Lines {
    require(isValid(this))
    return Lines.values()[this]
}
/**
 * Takes the char and transform it to a valid line
 * @return The line corresponding
 */
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


/**
 * Turns a position to a string
 * @return the string itself
 */
fun Positions.toStr():String{
    val line = this.line.ordinal+1
    val columm = this.column.toString()[1]
    return "$columm$line"
}
