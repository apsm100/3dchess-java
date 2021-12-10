public class ChessBoard extends Board {
    TileListener tl;
    int boardNum;
    public ChessBoard(int w, int h) {
        super(w, h);
        boardNum = 0;
        tl = new TileListener(this);
    }

    public void createBoard() {
        int tileColor = 0;
        int altColor = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i % 2 == 0) {
                    if (altColor == 0) {
                        tileColor = 0;
                    } else {
                        tileColor = 1;
                    }
                } else {
                    if (altColor == 0) {
                        tileColor = 1;
                    } else {
                        tileColor = 0;
                    }
                }
                if (altColor == 1) {
                    altColor = 0;
                } else {
                    altColor = 1;
                }
                tiles[i][j] = new Tile(tl, tileColor, tileSizeX, tileSizeY, i, j, boardNum);
            }
        }
    }

    public void createBoard(int boardNum, TileListener tl) {
        this.boardNum = boardNum;
        this.tl = tl;
        createBoard();
    }

    public void setPieces() {
        int color;
        for (int x = 0; x < 8; x += 7) {
            if (x == 0) {
                color = 1;
            } else {
                color = 0;
            }
            tiles[x][0].setPiece(new Rook(x, 0, color));
            tiles[x][1].setPiece(new Knight(x, 1, color));
            tiles[x][2].setPiece(new Bishop(x, 2, color));
            tiles[x][3].setPiece(new King(x, 3, color));
            tiles[x][4].setPiece(new Queen(x, 4, color));
            tiles[x][5].setPiece(new Bishop(x, 5, color));
            tiles[x][6].setPiece(new Knight(x, 6, color));
            tiles[x][7].setPiece(new Rook(x, 7, color));
        }
        for (int i = 0; i < 8; i++) {
            tiles[1][i].setPiece(new Pawn(1, i, 1));
            tiles[6][i].setPiece(new Pawn(6, i, 0));
        }
    }

    public void movePiece(Piece p, Tile t) {
        removePiece(p);
        if ((t.positionX == this.height - 1 || t.positionX == 0 )&& p.getClass().getSimpleName() == "Pawn"){
            // Promote to Queen
            t.setPiece(new Queen(t.positionX, t.positionY, p.color));
        } else {
            p.move(t.positionX,  t.positionY);
            t.setPiece(p);
        }
    }

    public void grabPiece(Piece piece) {
        heldPiece = piece;
    }

    public void removePiece(Piece p) {
        tiles[p.positionX][p.positionY].removePiece();
    }

    @Override
    public void grabTile(Tile t) {}

    @Override
    public void movePiece(Tile tFrom, Tile tDest) {}
}
