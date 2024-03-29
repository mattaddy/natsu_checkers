import java.awt.Color;
import java.util.*;

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
                if (row % 2 != column % 2) {
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
     * Get all the red pieces on the board.
     *
     * @return CheckerPiece[] An array of all the red pieces.
     */
    public List<CheckerPiece> getRedPieces() {
        ArrayList<CheckerPiece> redPieces = new ArrayList<CheckerPiece>();
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                CheckerPiece piece = pieces[row][column];
                if (piece != null && piece.getColor().equals(Color.RED)) {
                    redPieces.add(piece);
                }
            }
        }
        return redPieces;
    }

    /**
     * Get all the black pieces on the board.
     *
     * @return CheckerPiece[] An array of all the black pieces.
     */
    public List<CheckerPiece> getBlackPieces() {
        ArrayList<CheckerPiece> blackPieces = new ArrayList<CheckerPiece>();
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                CheckerPiece piece = pieces[row][column];
                if (piece != null && piece.getColor().equals(Color.BLACK)) {
                    blackPieces.add(piece);
                }
            }
        }
        return blackPieces;
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

}
