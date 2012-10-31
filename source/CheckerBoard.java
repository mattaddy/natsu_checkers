/**
 * Class CheckerBoard represents the current state of the board for a game of
 * checkers.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 */
public class CheckerBoard {

    /**
     * The number of rows on the checkerboard.
     */
    private static final int ROWS = 8;

    /**
     * The number of columns on the checkerboard.
     */
    private static final int COLUMNS = 8;

    /**
     * The collection of checker pieces on this board.
     */
    CheckerPiece[][] pieces;

    /**
     * Construct a new CheckerBoard object.
     */
    public CheckerBoard() {
        pieces = new CheckerPiece[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (row % 2 == column % 2) {
                    if (row <= 2) {
                        pieces[row][column] = new CheckerPiece(CheckerColor.BLACK);
                    } else if (row >= (COLUMNS - 3)) {
                        pieces[row][column] = new CheckerPiece(CheckerColor.RED);
                    }
                }
            }
        }
    }

    public void print() {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                System.out.print("[");
                if (pieces[column][row] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(pieces[column][row]);
                }
                System.out.print("]");
            }
            System.out.println("");
        }
    }

}
