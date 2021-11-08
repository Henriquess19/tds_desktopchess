/**
 * The Verification board contract
 */

interface InterfaceVerificationBoard {
    /**
     * With piece given, guarantee if the movement its possible or not
     * @param [piece] piece to be checked
     * @param [initialposition] initial position of the piece
     * @param [wantedposition] where the piece is supposed to be moved
     * @return []
     */
    fun moveVerity(piece: Pieces, initialposition: Positions, wantedposition: Positions): Boolean
}