public abstract class Piece {
    String pieceString = this.getClass().getSimpleName();
    int positionX;
    int positionY;
    int color;
    int validX;
    int validY;
    Boolean needsClearPath;

    Boolean vertical;
    Boolean horizontal;
    Boolean diagonal; 
    Boolean backwards;
    Boolean specialL;

    public Piece(int x, int y, int c) {
        positionX = x;
        positionY = y;
        color = c;
        needsClearPath = false;
        validX = 100;
        validY = 100;
        vertical = true;
        horizontal = true;
        diagonal = true;
        backwards = true;
        specialL = false;
    }

    public String getPieceString() {
        return pieceString;
    }

    public void move(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public Boolean isValidMove(Tile t) {
        int x = t.positionX;
        int y = t.positionY;
        return isValidVertical(x, y) | isValidHorizontal(x, y) | isValidDiagonal(x, y) | isValidSpecialL(x, y);
    }

    public Boolean isValidVertical(int x, int y) {
        if (!this.vertical) {
            return false;
        } 
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
                return true;
            }
        }
        return false;
    }

    public Boolean isValidHorizontal(int x, int y) {
        if (!this.horizontal) {
            return false;
        } 
        int differenceX;
        int differenceY;
        differenceX = this.positionX - x;
        if (this.positionY > y) {
            differenceY = this.positionY - y;
        } else {
            differenceY = y - this.positionY;
        }
        if (differenceX != 0) {
            return false;
        } else {
            if (differenceY <= validY) {
                return true;
            }
        }
        return false;
    }

    public Boolean isValidDiagonal(int x, int y) {
        if (!this.diagonal) {
            return false;
        } 
        int differenceX;
        int differenceY;
        differenceX = this.positionX - x;
        differenceY = this.positionY - y;
        if (differenceX < 0) {
            differenceX = -differenceX;
        }
        if (differenceY < 0){
            differenceY = -differenceY;
        }
        if (differenceX == differenceY &&  differenceX <= validX && differenceY <= validY) {
            return true;
        }
        return false;
    }

    public Boolean isValidSpecialL(int x, int y) {
        if (!this.specialL) {
            return false;
        }
        int differenceX;
        int differenceY;
        differenceX = this.positionX - x;
        differenceY = this.positionY - y;
        if (differenceX < 0) {
            differenceX = -differenceX;
        }
        if (differenceY < 0){
            differenceY = -differenceY;
        }
        if (differenceX == 2 && differenceY == 1 || differenceX == 1 && differenceY == 2 ) {
            return true;
        }
        return false;
    }
}