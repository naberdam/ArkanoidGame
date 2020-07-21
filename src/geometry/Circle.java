package geometry;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import core.Sprite;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private Point centerCircle;
    private int radius;
    private Color colorOfFrameCircle;
    private Color colorInsideCircle;

    /**
     * Instantiates a new Circle.
     *
     * @param centerCircle       the center circle
     * @param radius             the radius
     * @param colorOfFrameCircle the color of frame circle
     * @param colorInsideCircle  the color inside circle
     */
    public Circle(Point centerCircle, int radius, Color colorOfFrameCircle, Color colorInsideCircle) {
        this.centerCircle = centerCircle;
        this.radius = radius;
        this.colorOfFrameCircle = colorOfFrameCircle;
        this.colorInsideCircle = colorInsideCircle;
    }

    /**
     * Instantiates a new Circle.
     *
     * @param centerX            the center x
     * @param centerY            the center y
     * @param radius             the radius
     * @param colorOfFrameCircle the color of frame circle
     * @param colorInsideCircle  the color inside circle
     */
    public Circle(double centerX, double centerY, int radius, Color colorOfFrameCircle, Color colorInsideCircle) {
        this.centerCircle = new Point(centerX, centerY);
        this.radius = radius;
        this.colorOfFrameCircle = colorOfFrameCircle;
        this.colorInsideCircle = colorInsideCircle;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColorOfFrameCircle());
        d.drawCircle(getX(), getY(), getRadius());
        d.setColor(getColorInsideCircle());
        d.fillCircle(getX(), getY(), getRadius());
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
     * Gets center circle.
     *
     * @return the center circle
     */
    public Point getCenterCircle() {
        return centerCircle;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Gets color of frame circle.
     *
     * @return the color of frame circle
     */
    public Color getColorOfFrameCircle() {
        return colorOfFrameCircle;
    }

    /**
     * Gets color inside circle.
     *
     * @return the color inside circle
     */
    public Color getColorInsideCircle() {
        return colorInsideCircle;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) getCenterCircle().getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) getCenterCircle().getY();
    }
}
