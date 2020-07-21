package geometry;
import java.util.List;
import java.util.ArrayList;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;
    private double widthOfRec;
    private double heightOfRec;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.widthOfRec = width;
        this.heightOfRec = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param xUpperLeft the x upper left
     * @param yUpperLeft the y upper left
     * @param width      the width
     * @param height     the height
     */
    public Rectangle(double xUpperLeft, double yUpperLeft, double width, double height) {
        this.upperLeft = new Point(xUpperLeft, yUpperLeft);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.widthOfRec = width;
        this.heightOfRec = height;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listOfIntersectionPoints = new ArrayList<>();
        Line upLimit = new Line(this.getUpperLeft(), this.getUpperRight());
        if (line.isIntersecting(upLimit)) {
            listOfIntersectionPoints.add(line.intersectionWith(upLimit));
        }
        Line downLimit = new Line(this.getDownLeft(), this.getDownRight());
        if (line.isIntersecting(downLimit)) {
            listOfIntersectionPoints.add(line.intersectionWith(downLimit));
        }
        Line leftLimit = new Line(this.getUpperLeft(), this.getDownLeft());
        if (line.isIntersecting(leftLimit)) {
            listOfIntersectionPoints.add(line.intersectionWith(leftLimit));
        }
        Line rightLimit = new Line(this.getUpperRight(), this.getDownRight());
        if (line.isIntersecting(rightLimit)) {
            listOfIntersectionPoints.add(line.intersectionWith(rightLimit));
        }
        if (!listOfIntersectionPoints.isEmpty()) {
            return listOfIntersectionPoints;
        }
        return null;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidthOfRec() {
        return widthOfRec;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeightOfRec() {
        return heightOfRec;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Gets down left.
     *
     * @return the down left
     */
    public Point getDownLeft() {
        return this.downLeft;
    }

    /**
     * Gets down right.
     *
     * @return the down right
     */
    public Point getDownRight() {
        return this.downRight;
    }

    /**
     * Sets upper left.
     *
     * @param width  the width
     * @param height the height
     */
    public void updateRectangle(double width, double height) {
        this.upperRight = new Point(getUpperLeft().getX() + width, getUpperLeft().getY());
        this.downLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + height);
        this.downRight = new Point(getUpperLeft().getX() + width, getUpperLeft().getY() + height);
    }

    /**
     * Sets location.
     *
     * @param x the x
     * @param y the y
     */
    public void setLocation(double x, double y) {
        this.getUpperLeft().setX(x);
        this.getUpperLeft().setY(y);
        updateRectangle();
    }

    /**
     * Update rectangle.
     */
    public void updateRectangle() {
        this.downLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeightOfRec());
        this.downRight = new Point(getUpperLeft().getX() + getWidthOfRec(), getDownLeft().getY());
        this.upperRight = new Point(getUpperLeft().getX() + getWidthOfRec(), getUpperLeft().getY());
    }
}
