class VerificationBoard():InterfaceVerificationBoard {
    private val board = mutableMapOf<Positions, Pieces?>()
    override fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean {
        val ocupied = board.containsKey(wantedposition)

        if (ocupied) {
            val ocupiedcolor = board[wantedposition]?.team
            if (ocupiedcolor == piece.team) {
                return false //Same team
            }
        }
        return when (piece) {
            //Rooks
            Pieces.r -> moveVerityRook(initialposition, wantedposition)
            Pieces.R -> moveVerityRook(initialposition, wantedposition)
            //Pawns
            Pieces.p -> moveVerityPawn(Colors.BLACK,initialposition, wantedposition,ocupied)
            Pieces.P -> moveVerityPawn(Colors.WHITE,initialposition, wantedposition,ocupied)
            //Bishops
            Pieces.b -> moveVerityBishop(initialposition, wantedposition)
            Pieces.B -> moveVerityBishop(initialposition, wantedposition)
            //Kings
            Pieces.k -> moveVerityKing(initialposition, wantedposition)
            Pieces.K -> moveVerityKing(initialposition, wantedposition)
            //Knights
            Pieces.n -> moveVerityKnight(initialposition, wantedposition)
            Pieces.N -> moveVerityKnight(initialposition, wantedposition)
            //Queens (they have the movement from a knight plus bishop, so if one of the is true, she can move)
            else -> moveVerityKnight(initialposition, wantedposition)||moveVerityBishop(initialposition, wantedposition)
        }
    }

    override fun moveVerityRook(
        initialposition: Positions,
        wantedposition: Positions
    ): Boolean {

        if (initialposition.line == wantedposition.line) {
            val highercolumn = Math.max(initialposition.columns.ordinal, wantedposition.columns.ordinal)
            val lowercolumn = Math.min(initialposition.columns.ordinal, wantedposition.columns.ordinal)

            for (i in highercolumn - 1 until lowercolumn + 1) {
                if (board.containsKey(
                        Positions(
                            Lines.values()[i],
                            wantedposition.columns
                        )
                    )
                ) return false //Encounter
            }
        } else if (initialposition.columns == wantedposition.columns) {
            val higherline = Math.max(initialposition.line.ordinal, wantedposition.line.ordinal)
            val lowerline = Math.min(initialposition.line.ordinal, wantedposition.line.ordinal)

            for (i in higherline - 1 until lowerline + 1) {
                if (board.containsKey(
                        Positions(
                            Lines.values()[i],
                            wantedposition.columns
                        )
                    )
                ) return false //Encounter
            }
        }
        return true //Valid piece movement
    }

    override fun moveVerityPawn(
        pieceteam: Colors,
        initialposition: Positions,
        wantedposition: Positions,
        ocupied: Boolean
    ): Boolean {

        //Initial pawn position
        if (pieceteam == Colors.WHITE && initialposition.line == Lines.L2 && initialposition.columns == wantedposition.columns) {
            if (board.containsKey(Positions(Lines.L3, initialposition.columns))
                || board.containsKey(Positions(Lines.L4, initialposition.columns))
            ) return false //Encounter
        }

        //Move infront
        if (pieceteam == Colors.WHITE) {
            if (initialposition.columns == wantedposition.columns) {
                if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 || ocupied) return false //Encounter

            }

            if (pieceteam == Colors.BLACK) {
                if (initialposition.columns == wantedposition.columns) {
                    if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || ocupied) return false //Encounter
                }
            }

            //Move diagonal
            if (initialposition.columns.ordinal != wantedposition.columns.ordinal - 1
                || initialposition.columns.ordinal != wantedposition.columns.ordinal + 1
            ) return false //Invalid Movement

            else {
                if (pieceteam == Colors.WHITE) {
                    if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || !ocupied) return false //Not Encounter
                }

                if (pieceteam == Colors.BLACK) {
                    if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 && !ocupied) return false //Not Encounter
                }
            }
            return true //Valid piece movement
        }
        return true
    }

    override fun moveVerityBishop(
        initialposition: Positions,
        wantedposition: Positions,
    ): Boolean {

        if (initialposition.columns.ordinal == wantedposition.columns.ordinal
            || initialposition.line.ordinal == wantedposition.line.ordinal
        ) return false // Invalid movement

        var line = Lines.values()[initialposition.line.ordinal]
        var column = Columns.values()[initialposition.columns.ordinal]

        if (initialposition.columns.ordinal > wantedposition.columns.ordinal) {

            // Diagonal left_x
            column = Columns.values()[initialposition.columns.ordinal - 1]

            if (initialposition.line.ordinal > wantedposition.line.ordinal) {

                // Diagonal left_down
                line = Lines.values()[initialposition.line.ordinal - 1]

                while (line.ordinal >= wantedposition.line.ordinal + 1
                    && column.ordinal >= wantedposition.columns.ordinal + 1
                ) {

                    if (board.containsKey(Positions(line, column))) return false // Encounter

                    line = Lines.values()[initialposition.line.ordinal - 1]
                    column = Columns.values()[initialposition.columns.ordinal - 1]
                }

            } else {

                // Diagonal left_up
                line = Lines.values()[initialposition.line.ordinal + 1]

                while (line.ordinal <= wantedposition.line.ordinal + 1
                    && column.ordinal >= wantedposition.columns.ordinal + 1
                ) {

                    if (board.containsKey(Positions(line, column))) return false // Encounter

                    line = Lines.values()[initialposition.line.ordinal + 1]
                    column = Columns.values()[initialposition.columns.ordinal - 1]
                }

            }

        } else {

            // Diagonal right_x
            column = Columns.values()[initialposition.columns.ordinal + 1]

            if (initialposition.line.ordinal > wantedposition.line.ordinal) {

                // Diagonal right_down
                line = Lines.values()[initialposition.line.ordinal - 1]

                while (line.ordinal >= wantedposition.line.ordinal + 1
                    && column.ordinal >= wantedposition.columns.ordinal + 1
                ) {

                    if (board.containsKey(Positions(line, column))) return false // Encounter

                    line = Lines.values()[initialposition.line.ordinal - 1]
                    column = Columns.values()[initialposition.columns.ordinal + 1]
                }

            } else {

                // Diagonal right_up
                line = Lines.values()[initialposition.line.ordinal + 1]

                while (line.ordinal <= wantedposition.line.ordinal + 1
                    && column.ordinal >= wantedposition.columns.ordinal + 1
                ) {

                    if (board.containsKey(Positions(line, column))) return false // Encounter

                    line = Lines.values()[initialposition.line.ordinal + 1]
                    column = Columns.values()[initialposition.columns.ordinal + 1]
                }
            }
        }
        return true // Valid movement
    }

    override fun moveVerityKing(
        initialposition: Positions,
        wantedposition: Positions,
        ): Boolean {

        if (wantedposition.columns.ordinal != initialposition.columns.ordinal
            || wantedposition.columns.ordinal != initialposition.columns.ordinal - 1
            || wantedposition.columns.ordinal != initialposition.columns.ordinal + 1
        ) return false // Wrong column

        if (wantedposition.line.ordinal != initialposition.line.ordinal
            || wantedposition.line.ordinal != initialposition.line.ordinal - 1
            || wantedposition.line.ordinal != initialposition.line.ordinal + 1
        ) return false // Wrong line

        return true // Valid movement
    }

    override fun moveVerityKnight(
        initialposition: Positions,
        wantedposition: Positions,
    ): Boolean {

        //Up movement
        if (initialposition.line.ordinal == wantedposition.line.ordinal - 2) {
            if (initialposition.columns.ordinal == wantedposition.columns.ordinal + 1) return true // Valid movement; left
            if (initialposition.columns.ordinal == wantedposition.columns.ordinal - 1) return true // Valid movement; right
        }

        //Down movement
        if (initialposition.line.ordinal == wantedposition.line.ordinal + 2) {
            if (initialposition.columns.ordinal == wantedposition.columns.ordinal + 1) return true // Valid movement; left
            if (initialposition.columns.ordinal == wantedposition.columns.ordinal - 1) return true // Valid movement; right
        }

        //Left movement
        if (initialposition.columns.ordinal == wantedposition.columns.ordinal + 2) {
            if (initialposition.line.ordinal == wantedposition.line.ordinal - 1) return true // Valid movement; up
            if (initialposition.line.ordinal == wantedposition.line.ordinal + 1) return true // Valid movement; down
        }

        //Right movement
        if (initialposition.columns.ordinal == wantedposition.columns.ordinal - 2) {
            if (initialposition.line.ordinal == wantedposition.line.ordinal - 1) return true // Valid movement; up
            if (initialposition.line.ordinal == wantedposition.line.ordinal + 1) return true // Valid movement; down
        }
        return false // Invalid movement
    }
}

