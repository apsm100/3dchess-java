public class King extends Piece {
    public King(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♔";
        } else {
            pieceString = "♚";
        }
        validX = 1;
        validY = 1;
    }
}
