public class Knight extends Piece {
    public Knight(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♘";
        } else {
            pieceString = "♞";
        }
        diagonal = false;
        vertical = false;
        horizontal = false;
        specialL = true;
    }
}
