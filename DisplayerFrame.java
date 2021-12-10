import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class DisplayerFrame extends JFrame {
    public void init(final Board board) {
        setLayout(new GridLayout(board.getWidth(), board.getHeight()));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                add(board.tiles[i][j], c);
            }
        }
    }

    public void init(final ChessBoard3D board) {
        setLayout(new GridLayout(0, 3));
        JPanel panel = new JPanel(new GridLayout(board.boards[0].getWidth(), board.boards[0].getHeight()));
        JPanel panel1 = new JPanel(new GridLayout(board.boards[0].getWidth(), board.boards[0].getHeight()));
        JPanel panel2 = new JPanel(new GridLayout(board.boards[0].getWidth(), board.boards[0].getHeight()));

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Board 1"));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Board 2"));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Board 3"));
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        for (int i = 0; i < board.boards[0].width; i++) {
            for (int j = 0; j < board.boards[0].height; j++) {
                panel.add(board.boards[0].tiles[i][j], c);
            }
        }
        
        for (int i = 0; i < board.boards[0].width; i++) {
            for (int j = 0; j < board.boards[0].height; j++) {
                panel1.add(board.boards[1].tiles[i][j], c);
            }
        }

        for (int i = 0; i < board.boards[0].width; i++) {
            for (int j = 0; j < board.boards[0].height; j++) {
                panel2.add(board.boards[2].tiles[i][j], c);
            }
        }
        add(panel, c);
        add(panel1, c);
        add(panel2, c);
    }
}
