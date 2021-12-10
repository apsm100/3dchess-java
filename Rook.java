public class Rook extends Piece {
    public Rook(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♖";
        } else {
            pieceString = "♜";
        }
        diagonal = false;
        needsClearPath = true;
    }
}
