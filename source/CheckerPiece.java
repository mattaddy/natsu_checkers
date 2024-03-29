import java.awt.Color;

/**
 * Class CheckerPiece represents a single checker on a checker board.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckerPiece {

    /**
     * The color of this piece.
     */
    private CheckerColor color;

    /**
     * The row this piece is in.
     */
    private int row;

    /**
     * The column this piece is in.
     */
    private int column;

    /**
     * The direction this piece can move.
     */
    private Direction direction;

    /**
     * Construct a CheckerPiece object.
     *
     * @param color     The color of this piece.
     * @param direction The direction this piece can move.
     * @param row       The row of the piece.
     * @param column    The column of the piece.
     */
    public CheckerPiece(CheckerColor color, Direction direction, int row, int column) {
        this.color = color;
        this.direction = direction;
        this.row = row;
        this.column = column;
    }

    /**
     * Construct a CheckerPiece object.
     *
     * @param row    The row of the piece.
     * @param column The column of the piece.
     */
    public CheckerPiece(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the color for this CheckerPiece.
     *
     * @return Color The color for this CheckerPiece.
     */
    public Color getColor() {
        return color.getColor();
    }

    /**
     * Get the row that this piece resides in.
     *
     * @return int The row that this piece resides in.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column that this piece resides in.
     *
     * @return int The column that this piece resides in.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the direction that this piece can move.
     *
     * @return Direction The direction that this piece can move.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Determine if the specified row and column is a valid move for this
     * CheckerPiece.
     *
     * @param row    The row of the desired move.
     * @param column The column of the desired move.
     *
     * @return boolean True if the specified move is valid, false otherwise.
     */
    public boolean isValidMove(int row, int column) {
        if (direction == Direction.UP) {
            if (row == this.row - 1 && (column == this.column - 1 || column == this.column + 1)) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.DOWN) {
            if (row == this.row + 1 && (column == this.column - 1 || column == this.column + 1)) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.BOTH) {
            if ((row == this.row - 1 && (column == this.column - 1 || column == this.column + 1)) || (row == this.row + 1 && (column == this.column - 1 || column == this.column + 1))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Move this piece to the specified row and column.
     *
     * @param row    The row to move this piece to.
     * @param column The column to move this piece to.
     */
    public void move(int row, int column) {
        this.row = row;
        this.column = column;

        if (direction == Direction.UP && row == 0) {
            direction = Direction.BOTH;
        } else if (direction == Direction.DOWN && row == 7) {
            direction = Direction.BOTH;
        }
    }

    /**
     * Determine whether this piece is kinged or not.
     *
     * @return boolean True if this piece is kinged, false otherwise.
     */
    public boolean isKinged() {
        return direction == Direction.BOTH;
    }

    /**
     * Override the toString() method.
     *
     * @return String The string representation of this CheckerPiece.
     */
    public String toString() {
        return color.toString();
    }

    /**
     * An enumerator that represents a direction that a checker can move.
     */
    public enum Direction {
        UP, DOWN, BOTH;
    }
}
