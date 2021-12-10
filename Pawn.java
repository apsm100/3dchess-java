public class Pawn extends Piece {
    public Pawn(int x, int y, int c) {
        super(x, y, c);
        if (color == 0) {
            pieceString = "♙";
        } else {
            pieceString = "♟";
        }
        validX = 2;
        validY = 0;
        horizontal = false;
        diagonal = false;
        backwards = false;
        needsClearPath = true;
    }

    public void move(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        validX = 1;
    }

    public Boolean isValidMove(Tile t) {
        return isValidVertical(t) | isValidDiagonal(t);
    }

    public Boolean isValidVertical(Tile t) {
        int x = t.positionX;
        int y = t.positionY;
        int differenceX;
        int differenceY;
        if (this.color == 0) {
            differenceX = this.positionX - x;
            differenceY = this.positionY - y;
        } else {
            differenceX = x - this.positionX;
            differenceY = y - this.positionY;
        }
        if (differenceY != 0) {
            return false;
        } else {
            if (!backwards && differenceX < 0) {
                return false;
            }
            if (differenceX < 0) {
                differenceX = -differenceX;
            }
            if (differenceX <= validX) {
                if (t.piece == null) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Boolean isValidDiagonal(Tile t) {
        int x = t.positionX;
        int y = t.positionY;
        int differenceX;
        int differenceY;

        int absX = 0;
        int absY = 0;

        differenceX = this.positionX - x;
        differenceY = this.positionY - y;
        if (differenceX < 0) {
            absX = -(differenceX);
        } else {
            absX = differenceX;
        }
        if (differenceY < 0){
            absY = -(differenceY);
        } else {
            absY = differenceY;
        }

        if ((absX == 1 && absY == 1)  &&  (t.piece != null)) {
            if (this.color == 1 && ((differenceX == -1 && differenceY == 1) || (differenceX == -1 && differenceY == -1))) {
                return true;
            }
            if (this.color == 0 && ((differenceX == 1 && differenceY == 1) || (differenceX == 1 && differenceY == -1))){
                return true;
            }
        
        }
        return false;
    }
}
