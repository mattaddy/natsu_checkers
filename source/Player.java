import java.awt.Color;
import java.util.ArrayList;

/**
 * Class Player represents a player in the game of checkers. A player can be
 * either black or red.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class Player {

    /**
     * The color of this player.
     */
    private Color color;

    /**
     * Construct a Player object.
     *
     * @param Color The color for this player.
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * Get the Color of this Player.
     *
     * @return Color The Color of this Player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Override equals() method.
     */
    public boolean equals(Object other) {
        Player otherPlayer = (Player) other;
        return color.equals(otherPlayer.getColor());
    }

    public String toString() {
        if (color == Color.RED) {
            return "Red.";
        } else {
            return "Black.";
        }
    }

}
