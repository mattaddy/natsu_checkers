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
    public static final int ROWS = 8;

    /**
     * The number of columns on the checkerboard.
     */
    public static final int COLUMNS = 8;

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

    /**
     * Get a checker piece at the specified location on the board.
     *
     * @param row    The row of the specified piece.
     * @param column The column of the specified piece.
     *
     * @return CheckerPiece The piece at the specified location.
     */
    public CheckerPiece getPiece(int row, int column) {
        return pieces[row][column];
    }

    /**
     * Print the CheckerBoard (USED FOR DEBUGGING).
     */
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
