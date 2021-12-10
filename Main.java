/**
 * Amrit Manhas
 * A01247201
 * The driver program. Displays a chess board with pieces that can move.
 * 
 */
public class Main {
    /**
     * The entry point to the program.
     *
     */
    public static void main(String[] argv) {
        final SwingDisplayer displayer;
        final int x = 8;
        final int y = 8;
        displayer = new SwingDisplayer();

        if (argv.length > 0) {
            if (argv[0].equals("-n")) {
                final ChessBoard board;
                board = new ChessBoard(x, y);
                board.createBoard();
                board.setPieces();
                displayer.display(board);
            }
        } else {
            final ChessBoard3D board;
            board = new ChessBoard3D(x, y);
            board.createBoard();
            displayer.display(board);
        }
    }
}
