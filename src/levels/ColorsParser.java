package levels;

import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param stringOfColor the string of color
     * @return the java . awt . color
     */
    public java.awt.Color colorFromString(String stringOfColor) {
        if (stringOfColor == null || stringOfColor.length() < 2) {
            return null;
        }
        stringOfColor = stringOfColor.substring(0, stringOfColor.length() - 1);
        switch (stringOfColor) {
            case "black":
                return Color.BLACK;
            case "blue":
                return Color.BLUE;
            case "cyan":
                return Color.CYAN;
            case "gray":
                return Color.gray;
            case "lightGray":
                return Color.lightGray;
            case "green":
                return Color.green;
            case "orange":
                return Color.orange;
            case "pink":
                return Color.pink;
            case "red":
                return Color.red;
            case "white":
                return Color.white;
            case "yellow":
                return Color.yellow;
            default:
                return Color.magenta;

        }
    }

    /**
     * Color from rgb java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
    public java.awt.Color colorFromRGB(String s) {
        if (s == null || s.length() < 2) {
            return null;
        }
        String[] rgb = s.split(",");
        int r = Integer.valueOf(rgb[0]);
        int g = Integer.valueOf(rgb[1]);
        //remove the )) from the end
        int b = Integer.valueOf(rgb[2].substring(0, rgb[2].length() - 2));
        return new Color(r, g, b);

    }

    /**
     * Color java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
    public java.awt.Color color(String s) {
        if (s == null || s.length() < 2) {
            return null;
        }
        if (s.startsWith("color(RGB(")) {
            s = s.substring("color(RGB(".length());
            return this.colorFromRGB(s);
        }
        if (s.startsWith("color(")) {
            s = s.substring("color(".length());
            return this.colorFromString(s);
        }

        return Color.magenta;
    }
}
