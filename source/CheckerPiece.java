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
    private Color color;

    /**
     * Construct a CheckerPiece object.
     */
    public CheckerPiece(Color color) {
        this.color = color;
    }

    /**
     * Get the color for this CheckerPiece.
     *
     * @return Color The color for this CheckerPiece.
     */
    public Color getColor() {
        return color;
    }

    public String toString() {
        return color.toString();
    }
}
