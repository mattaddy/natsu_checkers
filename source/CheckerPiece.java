/**
 * Class CheckerPiece represents a single checker on a checker board.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 */
public class CheckerPiece {

    /**
     * The color of this piece.
     */
    private CheckerColor color;

    /**
     * Construct a CheckerPiece object.
     */
    public CheckerPiece(CheckerColor color) {
        this.color = color;
    }

    /**
     * Get the color for this CheckerPiece.
     *
     * @return Color The color for this CheckerPiece.
     */
    public CheckerColor getColor() {
        return color;
    }

    public String toString() {
        return color.toString();
    }
}
