public abstract class Board {
    int width;
    int height;
    int tileSizeX = 5;
    int tileSizeY = 5;
    Tile tiles[][];
    Piece pieces[][];
    Piece heldPiece;
    Tile heldTile;
    int turn = 0;

    public Board(int w, int h) {
        width = w;
        height = h;
        tiles = new Tile[w][h];
    }

    public abstract void createBoard();

    public abstract void movePiece(Piece p, Tile t);

    public abstract void grabPiece(Piece p);

    public abstract void grabTile(Tile t);

    public abstract void movePiece(Tile tFrom, Tile tDest);

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void changeTurns() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }
}
