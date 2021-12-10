import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
public class TileListener implements MouseListener {
    Board board;
    ChessBoard3D board3D;
   
    public TileListener(Board b) {
        board = b;
    }

    public TileListener(ChessBoard3D b) {
        board3D = b;
        board = board3D.boards[0];
    }

    public void showPossibleMoves(Piece p) {
        Tile tile;
        for (int i = 0; i < board.tiles.length; i++) {
            for (int j = 0; j < board.tiles[i].length; j++) {
                tile = board.tiles[i][j];
                if (p.isValidMove(tile)) {
                    if (tile.piece != null && p.color == tile.piece.color) {
                    } else {
                        if (tile.color == 1) {
                            tile.setBackground(new Color(119, 195, 137));
                        } else {
                            tile.setBackground(new Color(102, 166, 117));
                        }
                        tile.highlighted = true;
                    }
                } 
            }
 
        }
        checkClearPaths(p);
    }

    public void showPossibleMoves3D(Tile t) {
        Piece p = t.piece;
        Tile tile;
        for (int z = 0; z < 3; z++) {
            board = board3D.boards[z];
            for (int i = 0; i < board.tiles.length; i++) {
                for (int j = 0; j < board.tiles[i].length; j++) {
                    tile = board.tiles[i][j];
                    if (p.isValidMove(tile)) {
                        if ((tile.positionX == board3D.heldTile.positionX && tile.positionY == board3D.heldTile.positionY) || (tile.piece != null && p.color == tile.piece.color)) {
                        } else {
                            if (tile.color == 1) {
                                tile.setBackground(new Color(119, 195, 137));
                            } else {
                                tile.setBackground(new Color(102, 166, 117));
                            }
                            tile.highlighted = true;
                        }
                    } 
                }
            }
        }
        for (int z = 0; z < 3; z++) {
            board = board3D.boards[z];
            if (z == t.boardNum) {
                checkClearPaths(p);
            }
            resetTiles3D(z, t);
        }
    }

    public void resetTiles3D(int boardNum, Tile p) {
        if (boardNum != p.boardNum) {
            int difference = boardNum - p.boardNum;
            if (difference < 0) {
                difference = -(difference);
            }
            resetTilesVertical3D(difference, p);
            resetTilesHorizontal3D(difference, p);
            resetTilesDiagonal3D(difference, p);
        }
    }

    public void resetTilesVertical3D(int difference, Tile p) {
        if (difference == 1) {
            for (int i = p.positionX - 2 ; i >= 0; i--) {
                resetTilesFromX(0, i, p.positionY);
            }
            for (int i = p.positionX + 2; i < board.height; i++) {
                resetTilesFromX(i, board.height - 1, p.positionY);
            }
        } else if (difference == 2) {
            for (int i = p.positionX - 3; i >= 0; i--) {
                resetTilesFromX(0, i, p.positionY);
            }
            if (p.positionX - 1 >= 0) {
                board.tiles[p.positionX - 1][p.positionY].setColor();
            }
            for (int i = p.positionX + 3; i < board.height; i++) {
                resetTilesFromX(i, board.height - 1, p.positionY);
            }
            if (p.positionX + 1 < board.height) {
                board.tiles[p.positionX + 1][p.positionY].setColor();
            }
        }
    }

    public void resetTilesHorizontal3D(int difference, Tile p) {
        if (difference == 1) {
            for (int i = p.positionY - 2; i >= 0; i--) {
                resetTilesFromY(0, i, p.positionX);
            }
            for (int i = p.positionY + 2; i < board.width; i++) {
                resetTilesFromY(i, board.width - 1, p.positionX);
            } 
        } else if (difference == 2) {
            for (int i = p.positionY - 3; i >= 0; i--) {
                resetTilesFromY(0, i, p.positionX);
            }
            if (p.positionY - 1 >= 0) {
                board.tiles[p.positionX][p.positionY - 1].setColor();
            }
            for (int i = p.positionY + 3; i < board.width; i++) {
                resetTilesFromY(i, board.width - 1, p.positionX);
            } 
            if (p.positionY + 1 < board.width) {
                board.tiles[p.positionX][p.positionY + 1].setColor();
            }
        }
    }

    public void resetTilesDiagonal3D(int difference, Tile p) {
        int y = p.positionY;
        int x = p.positionX;
        if (difference == 1) { 
            resetTilesFromDiagonal(x - 1, y + 1, "TopRight");
            resetTilesFromDiagonal(x - 1, y - 1, "TopLeft");
            resetTilesFromDiagonal(x + 1, y - 1, "BottomLeft");
            resetTilesFromDiagonal(x + 1, y + 1, "BottomRight");
        } else if (difference == 2) {
            resetTilesFromDiagonal(x - 2, y + 2, "TopRight");
            if ((x - 1 >= 0) && (y + 1 < board.width)) {
                board.tiles[x - 1][y + 1].setColor();
            }
            resetTilesFromDiagonal(x - 2, y - 2, "TopLeft");
            if ((x - 1 >= 0) && (y - 1 >= 0)) {
                board.tiles[x - 1][y - 1].setColor();
            }
            resetTilesFromDiagonal(x + 2, y - 2, "BottomLeft");
            if ((x + 1 < board.height) && (y - 1 >= 0)) {
                board.tiles[x + 1][y - 1].setColor();
            }
            resetTilesFromDiagonal(x + 2, y + 2, "BottomRight");
            if ((x + 1 < board.height) && (y + 1 < board.width)) {
                board.tiles[x + 1][y + 1].setColor();
            }
        }
    }

    public void resetTileColors() {
        if (board3D != null) {
            resetTileColors3D();
        } else {
            for (int i = 0; i < board.tiles.length; i++) {
                for (int j = 0; j < board.tiles[i].length; j++) {
                    board.tiles[i][j].setColor();
                }
            }
        }
    }

    public void resetTileColors3D() {
        for (int z = 0; z < 3; z++) {
            board = board3D.boards[z];
            for (int i = 0; i < board.tiles.length; i++) {
                for (int j = 0; j < board.tiles[i].length; j++) {
                    board.tiles[i][j].setColor();
                }
            }
        }
    }

    public void checkClearPaths(Piece p) {
        if (!p.needsClearPath) {
            return;
        }
        checkClearVertical(p);
        checkClearHorizontal(p);
        checkClearDiagonal(p);
    }

    public void resetTilesFromX(int start, int end, int y) {
        for (int i = start; i <= end; i++) {
            board.tiles[i][y].setColor();
        }
    }

    public void resetTilesFromY(int start, int end, int x) {
        for (int i = start; i <= end; i++) {
            board.tiles[x][i].setColor();
        }
    }

    public void resetTilesFromDiagonal(int x, int y, String direction) {
        if (direction == "TopRight") {
            while (y < board.width - 1 && x > 0) {
                y++;
                x--;
                board.tiles[x][y].setColor();
            }
        } else if (direction == "TopLeft") {
            while (y > 0 && x > 0) {
                y--;
                x--;
                board.tiles[x][y].setColor();
            }
        } else if (direction == "BottomLeft") {
            while (y > 0 && x < board.height - 1) {
                y--;
                x++;
                board.tiles[x][y].setColor();
            }
        } else if (direction == "BottomRight") {
            while (y < board.width - 1 && x < board.height - 1) {
                y++;
                x++;
                board.tiles[x][y].setColor();
            }
        }
    }

    public void checkClearVertical(Piece p) {
        if (!p.vertical) {
            return;
        } 
        for (int i = p.positionX - 1; i >= 0; i--) {
            if (board.tiles[i][p.positionY].piece != null) {
                if (board.tiles[i][p.positionY].piece.color != p.color) {
                    i = i - 1;
                }
                resetTilesFromX(0, i, p.positionY);
            }
        }
        for (int i = p.positionX + 1; i < board.height; i++) {
            if (board.tiles[i][p.positionY].piece != null) {
                if (board.tiles[i][p.positionY].piece.color != p.color) {
                    i = i + 1;
                }
                resetTilesFromX(i, board.height - 1, p.positionY);
            }
        }
    }

    public void checkClearHorizontal(Piece p) {
        if (!p.horizontal) {
            return;
        }
        for (int i = p.positionY - 1; i >= 0; i--) {
            if (board.tiles[p.positionX][i].piece != null) {
                if (board.tiles[p.positionX][i].piece.color != p.color) {
                    i = i - 1;
                }
                resetTilesFromY(0, i, p.positionX);
            }
        }
        for (int i = p.positionY + 1; i < board.width; i++) {
            if (board.tiles[p.positionX][i].piece != null) {
                if (board.tiles[p.positionX][i].piece.color != p.color) {
                    i = i + 1;
                }
                resetTilesFromY(i, board.width - 1, p.positionX);
            }
        } 
    }

    public void checkClearDiagonal(Piece p) {
        if (!p.diagonal) {
            return;
        }
        int y = p.positionY;
        int x = p.positionX;

        while (y < board.width - 1 && x > 0) {
            y++;
            x--;
            if (board.tiles[x][y].piece != null) {
                resetTilesFromDiagonal(x, y, "TopRight");
            }
        }
        y = p.positionY;
        x = p.positionX;
        while (y > 0 && x > 0) {
            y--;
            x--;
            if (board.tiles[x][y].piece != null) {
                resetTilesFromDiagonal(x, y, "TopLeft");
            }
        }
        y = p.positionY;
        x = p.positionX;
        while (y > 0 && x < board.height - 1) {
            y--;
            x++;
            if (board.tiles[x][y].piece != null) {
                resetTilesFromDiagonal(x, y, "BottomLeft");
            }
        }
        y = p.positionY;
        x = p.positionX;
        while (y < board.width - 1 && x < board.height - 1) {
            y++;
            x++;
            if (board.tiles[x][y].piece != null) {
                resetTilesFromDiagonal(x, y, "BottomRight");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (board3D != null) {
            threeDClick(e);
        } else {
            click(e);
        }
    }

    public void click(MouseEvent e) {
        Tile tile = (Tile) e.getSource();
        if (tile.piece == board.heldPiece) {
            board.heldPiece = null;
            resetTileColors();
            return;
        }
        if (board.heldPiece != null) {
            if (tile.highlighted) {
                board.movePiece(board.heldPiece, tile);
                board.changeTurns();
                board.heldPiece = null;
                resetTileColors();
            }
        } else {
            if (tile.piece != null && tile.piece.color == board.turn) {
                board.grabPiece(tile.piece);
                showPossibleMoves(tile.piece);
                tile.setBackground(Color.GRAY);
            }
        }
    }

    public void threeDClick (MouseEvent e) {
        Tile tile = (Tile) e.getSource();
        if (tile == board3D.heldTile) {
            board3D.heldTile = null;
            resetTileColors();
            return;
        }
        if (board3D.heldTile != null) {
            if (tile.highlighted) {
                board3D.movePiece(board3D.heldTile, tile);
                board3D.changeTurns();
                board3D.heldTile = null;
                resetTileColors();
            }
        } else {
            if (tile.piece != null && tile.piece.color == board3D.turn) {
                board3D.grabTile(tile);
                showPossibleMoves3D(tile);
                tile.setBackground(Color.GRAY);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
