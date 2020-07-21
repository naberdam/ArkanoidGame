package geometry;
import arkanoid.GameLevel;
import biuoop.DrawSurface;
import core.Sprite;

import java.util.List;
import java.awt.Color;

/**
 * The type Line.
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color colorOfLine;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double x1 = (this.start.getX() + this.end.getX()) / 2.0;
        double y1 = (this.start.getY() + this.end.getY()) / 2.0;
        Point mid = new Point(x1, y1);
        return mid;
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Find incline linear double.
     *
     * @return the double
     */
    public double findInclineLinear() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Find free num in equation double.
     *
     * @param incline the incline
     * @return the double
     */
    public double findFreeNumInEquation(double incline) {
        return (this.start.getY() - (incline * this.start.getX()));
    }

    /**
     * Cut point point.
     *
     * @param other the other
     * @return the point
     */
    public Point cutPoint(Line other) {
        double xIntersection, yIntersection, inclineThis, inclineOther, freeThis, freeOther;
        if (this.start.getX() == this.end.getX()) {
            inclineOther = other.findInclineLinear();
            freeOther = other.findFreeNumInEquation(inclineOther);
            xIntersection = this.start.getX();
            yIntersection = (xIntersection * inclineOther) + freeOther;
        } else if (other.start.getX() == other.end.getX()) {
            inclineThis = this.findInclineLinear();
            freeThis = this.findFreeNumInEquation(inclineThis);
            xIntersection = other.start.getX();
            yIntersection = (xIntersection * inclineThis) + freeThis;
        } else {
            inclineOther = other.findInclineLinear();
            freeOther = other.findFreeNumInEquation(inclineOther);
            inclineThis = this.findInclineLinear();
            freeThis = this.findFreeNumInEquation(inclineThis);
            if (inclineOther == inclineThis) {
                return null;
            }
            xIntersection = (freeOther - freeThis) / (inclineThis - inclineOther);
            yIntersection = (xIntersection * inclineOther) + freeOther;
        }
        return (new Point(xIntersection, yIntersection));
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (other != null && this != null) {
            if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
                return false;
            }
            Point cuttingPoint = this.cutPoint(other);
            if (cuttingPoint == null) {
                return false;
            }
            return ((Math.min(this.start.getX(), this.end.getX()) <= cuttingPoint.getX())
                    && (Math.min(this.start.getY(), this.end.getY()) <= cuttingPoint.getY())
                    && (Math.max(this.start.getX(), this.end.getX()) >= cuttingPoint.getX())
                    && (Math.max(this.start.getY(), this.end.getY()) >= cuttingPoint.getY())
                    && (Math.min(other.start.getX(), other.end.getX()) <= cuttingPoint.getX())
                    && (Math.min(other.start.getY(), other.end.getY()) <= cuttingPoint.getY())
                    && (Math.max(other.start.getX(), other.end.getX()) >= cuttingPoint.getX())
                    && (Math.max(other.start.getY(), other.end.getY()) >= cuttingPoint.getY()));
        }
        return false;
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            return this.cutPoint(other);
        }
        return null;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((!this.start.equals(other.start)) && (!this.start.equals(other.end))) {
            return false;
        }
        if ((!this.end.equals(other.start)) && (!this.end.equals(other.end))) {
            return false;
        }
        return true;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rectForCheckIntersections the rect for check intersections
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rectForCheckIntersections) {
        List<Point> listOfIntersectionPoints = rectForCheckIntersections.intersectionPoints(this);
        if (listOfIntersectionPoints == null) {
            return null;
        }
        Point closestPoint = null;
        boolean isThereIntersectionPoint = false;
        double distanceFromTheClosestPoint = start().distance(end());
        for (Point checkPointMaxOrMin : listOfIntersectionPoints) {
            double newDistance = start().distance(checkPointMaxOrMin);
            if ((newDistance < distanceFromTheClosestPoint) /*&& (newDistance > EPSILON)*/) {
                closestPoint = checkPointMaxOrMin;
                distanceFromTheClosestPoint = newDistance;
                isThereIntersectionPoint = true;
            }
        }
        if (!isThereIntersectionPoint) {
            return null;
        }
        return closestPoint;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColorOfLine());
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        if (gameLevel == null) {
            return;
        }
        gameLevel.addSprite(this);
    }

    /**
     * Gets color of line.
     *
     * @return the color of line
     */
    public Color getColorOfLine() {
        return colorOfLine;
    }

    /**
     * Sets color of line.
     *
     * @param colorOfLines the color of lines
     */
    public void setColorOfLine(Color colorOfLines) {
        this.colorOfLine = colorOfLines;
    }
}
