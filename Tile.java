import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
public class Tile extends JButton {
    int color;
    int width;
    int height;
    int positionX;
    int positionY;
    int boardNum;
    Piece piece;
    Boolean highlighted;

    public Tile(TileListener tl, int c, int sizeX, int sizeY, int posX, int posY, int boardNum) {
        addMouseListener(tl);
        this.color = c;
        this.setBorderPainted(false);
        this.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
        this.setFocusPainted(false);
        width = sizeX;
        height = sizeY;
        this.positionX = posX;
        this.positionY = posY;
        this.boardNum = boardNum;
        setColor();
    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.setText(piece.getPieceString());
    }

    public void removePiece() {
        this.piece = null;
        setColor();
        this.setText("");
    }

    public void setColor() {
        highlighted = false;
        if (color == 0) {
            this.setBackground(Color.lightGray);
        } else {
            this.setBackground(Color.WHITE);
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public Boolean hasPiece() {
        return (piece != null);
    }
}
