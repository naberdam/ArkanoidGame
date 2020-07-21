package core;

import geometry.Point;

/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double headWayInAxisX;
    private double headWayInAxisY;

    /**
     * Instantiates a new Velocity.
     *
     * @param headWayInAxisX the head way in axis x
     * @param headWayInAxisY the head way in axis y
     */
// constructor
    public Velocity(double headWayInAxisX, double headWayInAxisY) {
        this.headWayInAxisX = headWayInAxisX;
        this.headWayInAxisY = headWayInAxisY;
    }

    /**
     * Apply to point point.
     *
     * @param p the p
     * @return the point
     */
// Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        if (p != null) {
            return (new Point(p.getX() + this.headWayInAxisX, p.getY() + this.headWayInAxisY));
        }
        return null;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle -= 90;
        angle *= Math.PI / (180);
        double nextStepX = speed * Math.cos(angle);
        double nextStepY = speed * Math.sin(angle);
        return new Velocity(nextStepX, nextStepY);
    }

    /**
     * Gets head way in axis x.
     *
     * @return the head way in axis x
     */
    public double getHeadWayInAxisX() {
        return this.headWayInAxisX;
    }

    /**
     * Gets head way in axis y.
     *
     * @return the head way in axis y
     */
    public double getHeadWayInAxisY() {
        return this.headWayInAxisY;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt((Math.pow(getHeadWayInAxisX(), 2)) + (Math.pow(getHeadWayInAxisY(), 2)));
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public Double getAngle() {
        return Math.toDegrees(Math.atan(getHeadWayInAxisY() / getHeadWayInAxisY()));
    }
}
