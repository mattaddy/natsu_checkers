import java.awt.Color;

/**
 * An enumeration of colors.
 */
public enum CheckerColor {
    BLACK(Color.BLACK), RED(Color.RED);

    private Color color;

    private CheckerColor(Color color) {
        this.color = color;
    }

    /**
     * Get the Java Color for this CheckerColor.
     *
     * @return Color The color of this checker.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Override the toString() method.
     *
     * @return String The string representation of this CheckerColor object.
     */
    public String toString() {
        String s = super.toString();
        return s.substring(0, 1);
    }
}
