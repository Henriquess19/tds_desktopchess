interface InterfaceVerificationBoard {
    fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityRook(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
    fun moveVerityPawn(piece: Pieces, initialposition: Positions, wantedposition: Positions, ocupied:Boolean): Boolean
}