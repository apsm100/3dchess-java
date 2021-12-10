public class Queen extends Piece {
    public Queen(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♕";
        } else {
            pieceString = "♛";
        }
        needsClearPath = true;
    }
}
