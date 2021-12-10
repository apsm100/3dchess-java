public class Bishop extends Piece {
    public Bishop(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♗";
        } else {
            pieceString = "♝";
        }
        vertical = false;
        horizontal = false;
        needsClearPath = true;
    }
}
