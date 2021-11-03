class VerificationBoard():InterfaceVerificationBoard {
    val board=mutableMapOf<Positions, Pieces?>()
    override fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean {
        val ocupied = board.containsKey(wantedposition)

        if (ocupied) {
            val ocupiedcolor = board[wantedposition]?.team
            if (ocupiedcolor == piece.team) {
                return false //Same team
            }
        }
        return when(piece) {
            Pieces.r  -> moveVerityRook(piece,initialposition,wantedposition)
            Pieces.R  -> moveVerityRook(piece,initialposition,wantedposition)
            Pieces.P  -> moveVerityPawn(piece,initialposition,wantedposition, ocupied)
            else      -> moveVerityPawn(piece,initialposition,wantedposition, ocupied)
        }
    }

    override fun moveVerityRook(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean {
        if (piece == Pieces.R || piece == Pieces.r) {
            if (initialposition.line == wantedposition.line) {
                val highercolumn = Math.max(initialposition.columns.ordinal, wantedposition.columns.ordinal)
                val lowercolumn = Math.min(initialposition.columns.ordinal, wantedposition.columns.ordinal)

                for (i in highercolumn - 1 until lowercolumn + 1) {
                    if (board.containsKey(Positions(Lines.values()[i],wantedposition.columns))) return false //Encounter
                }
            }

            else if (initialposition.columns == wantedposition.columns) {
                val higherline = Math.max(initialposition.line.ordinal, wantedposition.line.ordinal)
                val lowerline = Math.min(initialposition.line.ordinal, wantedposition.line.ordinal)

                for (i in higherline - 1 until lowerline + 1) {
                    if (board.containsKey(Positions(Lines.values()[i],wantedposition.columns))) return false //Encounter
                }
            }
            else
                return true //Valid piece movement
        }
         return true
    }

    override fun moveVerityPawn(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean {
        if (piece == Pieces.P || piece == Pieces.p) {
            //Initial pawn position
            if (piece == Pieces.P && initialposition.line == Lines.L2 && initialposition.columns == wantedposition.columns) {
                if (board.containsKey(Positions(Lines.L3, initialposition.columns))
                    || board.containsKey(Positions(Lines.L4, initialposition.columns))
                ) return false //Encounter
            }

            //Move infront
            if (piece == Pieces.P) {
                if (initialposition.columns == wantedposition.columns) {
                    if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 || ocupied) return false //Encounter

                }

                if (piece == Pieces.p) {
                    if (initialposition.columns == wantedposition.columns) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || ocupied) return false //Encounter
                    }
                }

                //Move diagonal
                if (initialposition.columns.ordinal != wantedposition.columns.ordinal - 1
                    || initialposition.columns.ordinal != wantedposition.columns.ordinal + 1
                ) return false //Invalid Movement

                else {
                    if (piece == Pieces.P) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal + 1 || !ocupied) return false //Not Encounter
                    }

                    if (piece == Pieces.p) {
                        if (initialposition.line.ordinal != wantedposition.line.ordinal - 1 && !ocupied) return false //Not Encounter
                    }
                }
                return true //Valid piece movement
            }
        }

        return true
    }
}

