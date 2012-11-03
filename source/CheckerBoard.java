import java.awt.Color;

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
                        pieces[row][column] = new CheckerPiece(CheckerColor.BLACK, CheckerPiece.Direction.DOWN, row, column);
                    } else if (row >= (COLUMNS - 3)) {
                        pieces[row][column] = new CheckerPiece(CheckerColor.RED, CheckerPiece.Direction.UP, row, column);
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
     * Move a piece on the board.
     *
     * @param piece  The piece to move.
     * @param row    The row to move the piece to.
     * @param column The column to move the piece to.
     *
     * @return boolean True if the move is possible, false otherwise.
     */
    public boolean movePiece(CheckerPiece piece, int row, int column) {
        if (piece.isValidMove(row, column)) {
            pieces[row][column] = piece;
            pieces[piece.getRow()][piece.getColumn()] = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Jump a piece on the board.
     *
     * @param piece  The piece to move.
     * @param row    The row to move the piece to.
     * @param column The column to move the piece to.
     */
    public CheckerPiece jumpPiece(CheckerPiece piece, int row, int column) {
        CheckerPiece pieceToJump = null;
        boolean pieceJumped = false;

        if (piece.getDirection() == CheckerPiece.Direction.UP) {
            if (piece.getRow() > row) {
                if (piece.getRow() - row == 2) {
                    if (column > piece.getColumn()) {
                        pieceToJump = getPiece(row + 1, column - 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row + 1][column - 1] = null;
                            pieceJumped = true;
                        }
                    } else if (column < piece.getColumn()) {
                        pieceToJump = getPiece(row + 1, column + 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row + 1][column + 1] = null;
                            pieceJumped = true;
                        }
                    }
                }
            }

        } else if (piece.getDirection() == CheckerPiece.Direction.DOWN) {
            if (piece.getRow() < row) {
                if (row - piece.getRow() == 2) {
                    if (column > piece.getColumn()) {
                        pieceToJump = getPiece(row - 1, column - 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row - 1][column - 1] = null;
                            pieceJumped = true;
                        }
                    } else if (column < piece.getColumn()) {
                        pieceToJump = getPiece(row - 1, column + 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row - 1][column + 1] = null;
                            pieceJumped = true;
                        }
                    }
                }
            }

        } else if (piece.getDirection() == CheckerPiece.Direction.BOTH) {
            if (piece.getRow() > row) {
                if (piece.getRow() - row == 2) {
                    if (column > piece.getColumn()) {
                        pieceToJump = getPiece(row + 1, column - 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row + 1][column - 1] = null;
                            pieceJumped = true;
                        }
                    } else if (column < piece.getColumn()) {
                        pieceToJump = getPiece(row + 1, column + 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row + 1][column + 1] = null;
                            pieceJumped = true;
                        }
                    }
                }
            } else if (piece.getRow() < row) {
                if (row - piece.getRow() == 2) {
                    if (column > piece.getColumn()) {
                        pieceToJump = getPiece(row - 1, column - 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row - 1][column - 1] = null;
                            pieceJumped = true;
                        }
                    } else if (column < piece.getColumn()) {
                        pieceToJump = getPiece(row - 1, column + 1);

                        if (pieceToJump != null && !pieceToJump.getColor().equals(piece.getColor())) {
                            pieces[row][column] = piece;
                            pieces[piece.getRow()][piece.getColumn()] = null;
                            pieces[row - 1][column + 1] = null;
                            pieceJumped = true;
                        }
                    }
                }
            }
        }

        if (pieceJumped) {
            return pieceToJump;
        } else {
            return null;
        }
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
