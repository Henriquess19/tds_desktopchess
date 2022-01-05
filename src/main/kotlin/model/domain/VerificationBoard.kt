package model.domain

data class MoveVerity (val tiles: MutableList<Positions>,val result: Result)

/**
 *Verify if the position where we wanna go have a team mate there, if not call another functions to see if the move is valid
 * @param   piece the piece to verify the move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @param   boardState current state of the UI.board
 * @return  if its a valid movement returns true else returns false
 */
fun movePieceVerity(piece: Piece, initialPosition: Positions, wantedPosition: Positions, boardState: BoardState): MoveVerity {
    val ocupied = boardState.containsPiece(wantedPosition)

    if (ocupied) {
        val ocupiedColor = boardState.getPiece(wantedPosition)?.team
        if (ocupiedColor == piece.team) {
            return MoveVerity(mutableListOf(),SameTeam)
        }
    }
    return when (piece.typeOfPiece) {
        //Rooks
        TypeOfPieces.R -> moveVerityRook(initialPosition, wantedPosition,boardState)
        //Pawns
        TypeOfPieces.P -> moveVerityPawn(piece.team, initialPosition, wantedPosition, ocupied,boardState)
        //Bishops
        TypeOfPieces.B -> moveVerityBishop(initialPosition, wantedPosition,boardState)
        //Kings
        TypeOfPieces.K -> moveVerityKing(initialPosition, wantedPosition)
        //Knights
        TypeOfPieces.N  -> moveVerityKnight(initialPosition, wantedPosition)
        //Queens (they have the movement from a knight plus bishop, so if one of the is true, she can move)
        else -> moveVerityQueen(initialPosition, wantedPosition,boardState)
    }
}

/**
 * Verify if the movement of the rook its possible
 * @param   initialPosition original position of the rook
 * @param   wantedPosition where the rook is to be moved
 * @param   boardState current state of the UI.board
 * @return  returns according to the result interface
 */
private fun moveVerityRook(
    initialPosition: Positions,
    wantedPosition: Positions,
    boardState: BoardState
): MoveVerity {
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))
    //walk on the collumn
    if (initialPosition.line == wantedPosition.line) {
        val higherColumn = Math.max(initialPosition.column.ordinal, wantedPosition.column.ordinal)
        val lowerColumn = Math.min(initialPosition.column.ordinal, wantedPosition.column.ordinal)
        for (i in higherColumn - 1 downTo lowerColumn + 1) {
            tiles.add(Positions(line= initialPosition.line,column= i.toColumn()))
            if (boardState.containsPiece(Positions(wantedPosition.line, i.toColumn())))
                return MoveVerity(mutableListOf(),Encounter)
        }
    }

    //walk on the line
    else if (initialPosition.column == wantedPosition.column) {
        val higherLine = Math.max(initialPosition.line.ordinal, wantedPosition.line.ordinal)
        val lowerLine = Math.min(initialPosition.line.ordinal, wantedPosition.line.ordinal)
        for (i in higherLine - 1 downTo lowerLine + 1) {
            tiles.add(Positions(line= i.toLine(),column= initialPosition.column))
            if (boardState.containsPiece(Positions( i.toLine(), wantedPosition.column)))
                return MoveVerity(mutableListOf(),Encounter)
        }
    }
    else return MoveVerity(mutableListOf(),InvalidMovement)

    return MoveVerity(tiles,ValidMovement)
}
/**
 * Verify if the movement of the pawn its possible
 * @param   initialPosition original position of the pawn
 * @param   wantedPosition where the pawn is to be moved
 * @param   boardState current state of the UI.board
 * @return  returns according to the result interface
 */
private fun moveVerityPawn(
    pieceTeam: Team,
    initialPosition: Positions,
    wantedPosition: Positions,
    ocupied: Boolean,
    boardState: BoardState
): MoveVerity{
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))

    //Initial pawn position
    if (pieceTeam == Team.WHITE &&
        initialPosition.line == Lines.L2 &&
        (wantedPosition.line == Lines.L3 ||
                wantedPosition.line == Lines.L4) &&
        initialPosition.column == wantedPosition.column) {

        return if (!ocupied && !(boardState.containsPiece(Positions(Lines.L3, initialPosition.column)))) {
            if(wantedPosition.line == Lines.L4) tiles.add(Positions(Lines.L3, initialPosition.column))
            MoveVerity(tiles,ValidMovement)
        }
        else MoveVerity(mutableListOf(),InvalidMovement)
    }

    if (pieceTeam == Team.BLACK &&
        initialPosition.line == Lines.L7 &&
        (wantedPosition.line == Lines.L6 ||
                wantedPosition.line == Lines.L5) &&
        initialPosition.column == wantedPosition.column) {

        return if(!ocupied && !(boardState.containsPiece(Positions(Lines.L6, initialPosition.column)))){
            if(wantedPosition.line == Lines.L5)tiles.add(Positions(Lines.L6, initialPosition.column))
            MoveVerity(tiles,ValidMovement)
        }
        else MoveVerity(mutableListOf(),InvalidMovement)
    }

    //Move infront
    if (initialPosition.column == wantedPosition.column) {
        val blackOrWhite= if(pieceTeam== Team.WHITE) - 1 else 1
        if(initialPosition.line.ordinal == wantedPosition.line.ordinal + blackOrWhite && !ocupied)
            return MoveVerity(tiles,ValidMovement)
    }
    //Move diagonal
    if (initialPosition.column.ordinal == wantedPosition.column.ordinal - 1
        || initialPosition.column.ordinal == wantedPosition.column.ordinal + 1){
        val blackOrWhite= if(pieceTeam == Team.WHITE) -1 else 1
        if (ocupied &&(initialPosition.line.ordinal == wantedPosition.line.ordinal + blackOrWhite ))
            return MoveVerity(tiles,ValidMovement)
    }
    return MoveVerity(mutableListOf(),InvalidMovement)
}
/**
 * Verify if the movement of the bishop its possible
 * @param   initialPosition original position of the bishop
 * @param   wantedPosition where the bishop is to be moved
 * @param   boardState current state of the UI.board
 * @return  returns according to the result interface
 */

private fun moveVerityBishop(
    initialPosition: Positions,
    wantedPosition: Positions,
    boardState: BoardState
): MoveVerity {
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))

    if (initialPosition.column.ordinal == wantedPosition.column.ordinal
        || initialPosition.line.ordinal == wantedPosition.line.ordinal
    ) return MoveVerity(mutableListOf(),InvalidMovement)

    var line = Lines.values()[initialPosition.line.ordinal]
    var column = Columns.values()[initialPosition.column.ordinal]

    if (initialPosition.column.ordinal > wantedPosition.column.ordinal) {

        // Diagonal left_x
        column = Columns.values()[initialPosition.column.ordinal-1]

        if (initialPosition.line.ordinal > wantedPosition.line.ordinal) {

            // Diagonal left_down
            if(!diagonalRightToLeftVerity(initialPosition,wantedPosition)) return MoveVerity(mutableListOf(),InvalidMovement)
            line = Lines.values()[initialPosition.line.ordinal -1]

            while (line.ordinal >= wantedPosition.line.ordinal + 1
                && column.ordinal >= wantedPosition.column.ordinal +1
            ) {

                if (boardState.containsPiece(Positions( line, column))) return MoveVerity(mutableListOf(),Encounter)

                tiles.add(Positions(line,column))
                line = Lines.values()[line.ordinal- 1]
                column = Columns.values()[column.ordinal - 1]
            }

        } else {

            // Diagonal left_up
            if (!diagonalLeftToRightVerity(initialPosition,wantedPosition)) return MoveVerity(mutableListOf(),InvalidMovement)
            line = Lines.values()[initialPosition.line.ordinal + 1]

            while (line.ordinal <= wantedPosition.line.ordinal - 1
                && column.ordinal >= wantedPosition.column.ordinal + 1
            ) {

                if (boardState.containsPiece(Positions( line, column))) return MoveVerity(mutableListOf(),Encounter)

                tiles.add(Positions(line,column))
                line = Lines.values()[line.ordinal + 1]
                column = Columns.values()[column.ordinal - 1]
            }

        }

    } else {

        // Diagonal right_x
        column = Columns.values()[initialPosition.column.ordinal + 1]

        if (initialPosition.line.ordinal > wantedPosition.line.ordinal) {

            // Diagonal right_down
            if (!diagonalLeftToRightVerity(initialPosition,wantedPosition)) return MoveVerity(mutableListOf(),InvalidMovement)
            line = Lines.values()[initialPosition.line.ordinal - 1]

            while (line.ordinal >= wantedPosition.line.ordinal + 1
                && column.ordinal <= wantedPosition.column.ordinal - 1
            ) {

                if (boardState.containsPiece(Positions( line, column))) return MoveVerity(mutableListOf(),Encounter)

                tiles.add(Positions(line,column))
                line = Lines.values()[line.ordinal - 1]
                column = Columns.values()[column.ordinal + 1]
            }


        } else {
            // Diagonal right_up
            if(!diagonalRightToLeftVerity(initialPosition,wantedPosition)) return MoveVerity(mutableListOf(),InvalidMovement)
            line = Lines.values()[initialPosition.line.ordinal + 1]

            while (line.ordinal <= wantedPosition.line.ordinal - 1
                && column.ordinal <= wantedPosition.column.ordinal - 1
            ) {

                if (boardState.containsPiece(Positions( line, column))) return MoveVerity(mutableListOf(),Encounter)

                tiles.add(Positions(line,column))
                line = Lines.values()[line.ordinal + 1]
                column = Columns.values()[column.ordinal + 1]
            }
        }
    }
    return MoveVerity(tiles,ValidMovement)
}
/**
 * Verify if the movement of the king its possible
 * @param   initialPosition original position of the king
 * @param   wantedPosition where the king is to be moved
 * @return  returns according to the result interface
 */
private fun moveVerityKing(
    initialPosition: Positions,
    wantedPosition: Positions,
): MoveVerity {
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))

    if (wantedPosition.column.ordinal != initialPosition.column.ordinal
        && wantedPosition.column.ordinal != initialPosition.column.ordinal - 1
        && wantedPosition.column.ordinal != initialPosition.column.ordinal + 1
    ) return MoveVerity(mutableListOf(),InvalidMovement)

    if (wantedPosition.line.ordinal != initialPosition.line.ordinal
        && wantedPosition.line.ordinal != initialPosition.line.ordinal - 1
        && wantedPosition.line.ordinal != initialPosition.line.ordinal + 1
    ) return MoveVerity(mutableListOf(),InvalidMovement)

    return MoveVerity(tiles,ValidMovement)
}
/**
 * Verify if the movement of the knight its possible
 * @param   initialPosition original position of the knight
 * @param   wantedPosition where the knight is to be moved
 * @return  returns according to the result interface
 */
private fun moveVerityKnight(
    initialPosition: Positions,
    wantedPosition: Positions,
): MoveVerity {
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))

    //Up movement
    if (initialPosition.line.ordinal == wantedPosition.line.ordinal - 2) {
        if (initialPosition.column.ordinal == wantedPosition.column.ordinal + 1) return MoveVerity(tiles,ValidMovement) // Valid movement; left
        if (initialPosition.column.ordinal == wantedPosition.column.ordinal - 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; right
    }

    //Down movement
    if (initialPosition.line.ordinal == wantedPosition.line.ordinal + 2) {
        if (initialPosition.column.ordinal == wantedPosition.column.ordinal + 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; left
        if (initialPosition.column.ordinal == wantedPosition.column.ordinal - 1) return MoveVerity(tiles,ValidMovement) // Valid movement; right
    }

    //Left movement
    if (initialPosition.column.ordinal == wantedPosition.column.ordinal + 2) {
        if (initialPosition.line.ordinal == wantedPosition.line.ordinal - 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; up
        if (initialPosition.line.ordinal == wantedPosition.line.ordinal + 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; down
    }

    //Right movement
    if (initialPosition.column.ordinal == wantedPosition.column.ordinal - 2) {
        if (initialPosition.line.ordinal == wantedPosition.line.ordinal - 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; up
        if (initialPosition.line.ordinal == wantedPosition.line.ordinal + 1) return MoveVerity(tiles,ValidMovement)  // Valid movement; down
    }
    return MoveVerity(mutableListOf(),InvalidMovement)
}
/**
 * Verify if the movement of the queen its possible
 * @param   initialPosition original position of the queen
 * @param   wantedPosition where the queen is to be moved
 * @return  returns according to the result interface
 */
private fun moveVerityQueen(
    initialPosition: Positions,
    wantedPosition: Positions,
    boardState: BoardState
):MoveVerity {
    val tiles = mutableListOf<Positions>()
    tiles.add(Positions(line= initialPosition.line,column= initialPosition.column))

    /* Check type rook movement */
    val rooktype = moveVerityRook(initialPosition, wantedPosition,boardState)
    if (rooktype.result == ValidMovement) tiles += rooktype.tiles

    /* Check type bishop movement */
    val bishoptype = moveVerityBishop(initialPosition, wantedPosition, boardState)
    if (bishoptype.result == ValidMovement) tiles += bishoptype.tiles

    return if (rooktype.result == ValidMovement || bishoptype.result == ValidMovement) MoveVerity(tiles.distinct().toMutableList(),ValidMovement)
    else MoveVerity(mutableListOf(),InvalidMovement)
}
/**
 * A nice formula to calculate the distance to the diagonal right move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun diagonalLeftToRightVerity (initialPosition: Positions, wantedPosition: Positions): Boolean {
    val initialDif = initialPosition.line.ordinal - initialPosition.column.ordinal
    val finalDif = wantedPosition.line.ordinal - wantedPosition.column.ordinal
    return initialDif == finalDif + 2 * (wantedPosition.column.ordinal - initialPosition.column.ordinal)
}
/**
 * A nice formula to calculate the distance to the diagonal left move
 * @param   initialPosition original position of the piece
 * @param   wantedPosition where the piece is to be moved
 * @return  if its a valid movement returns true else returns false
 */
private fun diagonalRightToLeftVerity (initialPosition: Positions, wantedPosition: Positions): Boolean {
    val initialDif = initialPosition.line.ordinal - initialPosition.column.ordinal
    val finalDif = wantedPosition.line.ordinal - wantedPosition.column.ordinal
    return initialDif == finalDif
}



