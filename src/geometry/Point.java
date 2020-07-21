package geometry;

import biuoop.DrawSurface;

/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;
    /**
     * The Radius of fill color point.
     */
    static final int RADIUS_OF_FILL_COLOR_POINT = 3;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double x1 = (this.x - other.getX()) * (this.x - other.getX());
        double y1 = (this.y - other.getY()) * (this.y - other.getY());
        return Math.sqrt((x1 + y1));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Point in range of line boolean.
     *
     * @param line the line
     * @return the boolean
     */
    public boolean pointInRangeOfLine(Line line) {
        if (this.getX() < line.start().getX() || this.getY() > line.end().getY()) {
            return false;
        }
        return true;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Draw point.
     *
     * @param d                   the d
     * @param colorToFillThePoint the color to fill the point
     */
    public void drawPoint(DrawSurface d, java.awt.Color colorToFillThePoint) {
        int xPoint = (int) (this.x + 1);
        int yPoint = (int) (this.y + 1);
        d.setColor(colorToFillThePoint);
        d.fillCircle(xPoint, yPoint, RADIUS_OF_FILL_COLOR_POINT);
    }

    /**
     * Sets x.
     *
     * @param newX the new x
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Sets y.
     *
     * @param newY the new y
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
