import java.awt.Dimension;

import javax.swing.JFrame;

public class SwingDisplayer implements Displayer {
    @Override
    public void display(Board board) {
        final DisplayerFrame frame;
        frame = new DisplayerFrame();
        frame.setPreferredSize(new Dimension(board.width * 100, board.height * 100));
        frame.init(board);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void display(ChessBoard3D board) {
        final DisplayerFrame frame;
        frame = new DisplayerFrame();

        frame.setPreferredSize(new Dimension(board.width * 100*3, board.height * 100));
        frame.init(board);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}