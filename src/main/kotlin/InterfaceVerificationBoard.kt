interface InterfaceVerificationBoard {
    fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityRook(initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityPawn(pieceteam:Colors ,initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean
    fun moveVerityBishop(initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityKing(initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityKnight(initialposition: Positions, wantedposition: Positions): Boolean
}