public class ChessBoard3D extends Board  {
    Tile heldTile;
    ChessBoard boards[] = new ChessBoard[3];
    TileListener tlAll;

    public ChessBoard3D(int w, int h) {
        super(w, h);
        boards[0] = new ChessBoard(w, h);
        boards[1] = new ChessBoard(w, h);
        boards[2] = new ChessBoard(w, h);
    }

    @Override
    public void createBoard() {
        tlAll = new TileListener(this);
        boards[0].createBoard(0, tlAll);
        boards[1].createBoard(1, tlAll);
        boards[2].createBoard(2, tlAll);
        boards[0].setPieces();
    }

    @Override
    public void movePiece(Piece p, Tile t) {}

    public void movePiece(Tile tFrom, Tile tDest) {
        Piece p = tFrom.piece;
        p.move(tDest.positionX,  tDest.positionY);
        removePiece(tFrom);
        tDest.setPiece(p);
    }

    public void removePiece(Tile t) {
        t.removePiece();
    }

    @Override
    public void grabPiece(Piece p) {
        heldPiece = p;
    }

    @Override
    public void grabTile(Tile t) {
        heldTile = t;
    }
    
}