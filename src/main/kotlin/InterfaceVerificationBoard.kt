interface InterfaceVerificationBoard {
    fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityRook(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityPawn(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean
    fun moveVerityBishop(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean
    fun moveVerityKing(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean
    fun moveVerityKnight(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean

}