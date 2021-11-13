/**
 *Verify if the position where we wanna go have a team mate there, if not call another functions to see if the move is valid
 * @param   piece the piece to verify the move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @param   board current state of the board
 * @return  if its a valid movement returns true else returns false
 */
fun movePieceVerity(piece: Pieces, initialPosition: Positions, wantedPosition: Positions, board: Board): Boolean {
    val ocupied = board.containsPiece(wantedPosition)

    if (ocupied) {
        val ocupiedcolor = board.getPiece(wantedPosition)?.team
        if (ocupiedcolor == piece.team) {
            return false //Same team
        }
    }
    return when (piece) {
        //Rooks
        Pieces.r,Pieces.R -> moveVerityRook(initialPosition, wantedPosition,board)
        //Pawns
        Pieces.p, Pieces.P -> moveVerityPawn(piece.team, initialPosition, wantedPosition, ocupied,board)
        //Bishops
        Pieces.b, Pieces.B -> moveVerityBishop(initialPosition, wantedPosition,board)
        //Kings
        Pieces.k, Pieces.K -> moveVerityKing(initialPosition, wantedPosition)
        //Knights
        Pieces.n, Pieces.N  -> moveVerityKnight(initialPosition, wantedPosition)
        //Queens (they have the movement from a knight plus bishop, so if one of the is true, she can move)
        else -> moveVerityRook(initialPosition, wantedPosition,board)
                || moveVerityBishop(initialPosition, wantedPosition, board)
    }
}

/**
 * Verify if the movement of the rook its possible
 * @param   initialPosition original position of the rook
 * @param   wantedPosition where the rook is to be moved
 * @param   board current state of the board
 * @return  if its a valid movement returns true else returns false
 */
private fun moveVerityRook(
    initialposition: Positions,
    wantedposition: Positions,
    board: Board
    ): Boolean {
    //walk on the collumn
    if (initialposition.line == wantedposition.line) {
        val highercolumn = Math.max(initialposition.column.ordinal, wantedposition.column.ordinal)
        val lowercolumn = Math.min(initialposition.column.ordinal, wantedposition.column.ordinal)

        for (i in highercolumn - 1 downTo lowercolumn + 1) {
            if (board.containsPiece(Positions(wantedposition.line, Columns.values()[i])))
                return false //Encounter
        }
    }//walk on the line
    else if (initialposition.column == wantedposition.column) {
        val higherline = Math.max(initialposition.line.ordinal, wantedposition.line.ordinal)
        val lowerline = Math.min(initialposition.line.ordinal, wantedposition.line.ordinal)

        for (i in higherline - 1 downTo lowerline + 1) {
            if (board.containsPiece(Positions( Lines.values()[i], wantedposition.column)))
                return false //Encounter
        }
    }
    else return false //invalid movement

    return true //Valid piece movement
}
/**
 * Verify if the movement of the pawn its possible
 * @param   initialPosition original position of the pawn
 * @param   wantedPosition where the pawn is to be moved
 * @param   board current state of the board
 * @return  if its a valid movement returns true else returns false
 */
private fun moveVerityPawn(
    pieceteam: Team,
    initialposition: Positions,
    wantedposition: Positions,
    ocupied: Boolean,
    board: Board
): Boolean {

    //Initial pawn position
    if (pieceteam == Team.WHITE &&
        initialposition.line == Lines.L2 &&
        (wantedposition.line == Lines.L3 ||
                wantedposition.line == Lines.L4) &&
        initialposition.column == wantedposition.column && !ocupied) {
        return !(board.containsPiece(Positions(Lines.L3, initialposition.column)))
                || board.containsPiece(Positions(Lines.L4, initialposition.column))
    }

    if (pieceteam == Team.BLACK &&
        initialposition.line == Lines.L7 &&
        (wantedposition.line == Lines.L6 ||
                wantedposition.line == Lines.L5) &&
        initialposition.column == wantedposition.column) {
        return !(board.containsPiece(Positions(Lines.L6, initialposition.column))
                || board.containsPiece(Positions( Lines.L5, initialposition.column)))
    }

    //Move infront
        if (initialposition.column == wantedposition.column) {
            val blackOrWhite= if(pieceteam==Team.WHITE) - 1 else 1
            return !(initialposition.line.ordinal != wantedposition.line.ordinal + blackOrWhite || ocupied)
        }
    //Move diagonal
    if (initialposition.column.ordinal == wantedposition.column.ordinal - 1
        || initialposition.column.ordinal == wantedposition.column.ordinal + 1){
        val blackOrWhite= if(pieceteam == Team.WHITE) 1 else -1
        if (initialposition.line.ordinal != wantedposition.line.ordinal + blackOrWhite
            && !ocupied) return false //Not Encounter
    }
    return true //Valid piece movement
}
/**
 * Verify if the movement of the bishop its possible
 * @param   initialPosition original position of the bishop
 * @param   wantedPosition where the bishop is to be moved
 * @param   board current state of the board
 * @return  if its a valid movement returns true else returns false
 */

private fun moveVerityBishop(
    initialposition: Positions,
    wantedposition: Positions,
    board: Board
): Boolean {

    if (initialposition.column.ordinal == wantedposition.column.ordinal
        || initialposition.line.ordinal == wantedposition.line.ordinal
    ) return false // Invalid movement

    var line = Lines.values()[initialposition.line.ordinal]
    var column = Columns.values()[initialposition.column.ordinal]

    if (initialposition.column.ordinal > wantedposition.column.ordinal) {

        // Diagonal left_x
        column = Columns.values()[initialposition.column.ordinal-1]

        if (initialposition.line.ordinal > wantedposition.line.ordinal) {

            // Diagonal left_down
            if(!diagonalrighttoleftverity(initialposition,wantedposition)) return false //Diferent diagonal
            line = Lines.values()[initialposition.line.ordinal -1]

            while (line.ordinal >= wantedposition.line.ordinal + 1
                && column.ordinal >= wantedposition.column.ordinal +1
            ) {

                if (board.containsPiece(Positions( line, column))) return false // Encounter

                line = Lines.values()[line.ordinal- 1]
                column = Columns.values()[column.ordinal - 1]
            }

        } else {

            // Diagonal left_up
            if (!diagonallefttorightverity(initialposition,wantedposition)) return false //Diferent diagonal
            line = Lines.values()[initialposition.line.ordinal + 1]

            while (line.ordinal <= wantedposition.line.ordinal - 1
                && column.ordinal >= wantedposition.column.ordinal + 1
            ) {

                if (board.containsPiece(Positions( line, column))) return false // Encounter

                line = Lines.values()[line.ordinal + 1]
                column = Columns.values()[column.ordinal - 1]
            }

        }

    } else {

        // Diagonal right_x
        column = Columns.values()[initialposition.column.ordinal + 1]

        if (initialposition.line.ordinal > wantedposition.line.ordinal) {

            // Diagonal right_down
            if (!diagonallefttorightverity(initialposition,wantedposition)) return false //Diferent diagonal
            line = Lines.values()[initialposition.line.ordinal - 1]

            while (line.ordinal >= wantedposition.line.ordinal + 1
                && column.ordinal <= wantedposition.column.ordinal - 1
            ) {

                if (board.containsPiece(Positions( line, column))) return false // Encounter

                line = Lines.values()[line.ordinal - 1]
                column = Columns.values()[column.ordinal + 1]
            }


        } else {
            // Diagonal right_up
            if(!diagonalrighttoleftverity(initialposition,wantedposition)) return false //Diferent diagonal
            line = Lines.values()[initialposition.line.ordinal + 1]

            while (line.ordinal <= wantedposition.line.ordinal - 1
                && column.ordinal <= wantedposition.column.ordinal - 1
            ) {

                if (board.containsPiece(Positions( line, column))) return false // Encounter

                line = Lines.values()[line.ordinal + 1]
                column = Columns.values()[column.ordinal + 1]
            }
        }
    }
    return true // Valid movement
    }
/**
 * Verify if the movement of the king its possible
 * @param   initialPosition original position of the king
 * @param   wantedPosition where the king is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun moveVerityKing(
    initialposition: Positions,
    wantedposition: Positions,
): Boolean {

    if (wantedposition.column.ordinal != initialposition.column.ordinal
        && wantedposition.column.ordinal != initialposition.column.ordinal - 1
        && wantedposition.column.ordinal != initialposition.column.ordinal + 1
    ) return false // Wrong column

    if (wantedposition.line.ordinal != initialposition.line.ordinal
        && wantedposition.line.ordinal != initialposition.line.ordinal - 1
        && wantedposition.line.ordinal != initialposition.line.ordinal + 1
    ) return false // Wrong line

    return true // Valid movement
    }
/**
 * Verify if the movement of the knight its possible
 * @param   initialPosition original position of the knight
 * @param   wantedPosition where the knight is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun moveVerityKnight(
initialposition: Positions,
wantedposition: Positions,
): Boolean {
    //Up movement
    if (initialposition.line.ordinal == wantedposition.line.ordinal - 2) {
        if (initialposition.column.ordinal == wantedposition.column.ordinal + 1) return true // Valid movement; left
        if (initialposition.column.ordinal == wantedposition.column.ordinal - 1) return true // Valid movement; right
    }

    //Down movement
    if (initialposition.line.ordinal == wantedposition.line.ordinal + 2) {
        if (initialposition.column.ordinal == wantedposition.column.ordinal + 1) return true // Valid movement; left
        if (initialposition.column.ordinal == wantedposition.column.ordinal - 1) return true // Valid movement; right
    }

    //Left movement
    if (initialposition.column.ordinal == wantedposition.column.ordinal + 2) {
        if (initialposition.line.ordinal == wantedposition.line.ordinal - 1) return true // Valid movement; up
        if (initialposition.line.ordinal == wantedposition.line.ordinal + 1) return true // Valid movement; down
    }

    //Right movement
    if (initialposition.column.ordinal == wantedposition.column.ordinal - 2) {
        if (initialposition.line.ordinal == wantedposition.line.ordinal - 1) return true // Valid movement; up
        if (initialposition.line.ordinal == wantedposition.line.ordinal + 1) return true // Valid movement; down
    }
    return false // Invalid movement
}
/**
 * A nice formula to calculate the distance to the diagonal right move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun diagonallefttorightverity (initialposition: Positions,wantedposition: Positions): Boolean {
    val initialdif = initialposition.line.ordinal - initialposition.column.ordinal
    val finaldif = wantedposition.line.ordinal - wantedposition.column.ordinal
    return initialdif == finaldif + 2 * (wantedposition.column.ordinal - initialposition.column.ordinal)
}
/**
 * A nice formula to calculate the distance to the diagonal left move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun diagonalrighttoleftverity (initialposition: Positions,wantedposition: Positions): Boolean {
    val initialdif = initialposition.line.ordinal - initialposition.column.ordinal
    val finaldif = wantedposition.line.ordinal - wantedposition.column.ordinal
    return initialdif == finaldif
}



