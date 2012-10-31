/**
 * An enumeration of colors.
 */
public enum CheckerColor {
    BLACK, RED;

    public String toString() {
        String s = super.toString();
        return s.substring(0, 1);
    }
}
