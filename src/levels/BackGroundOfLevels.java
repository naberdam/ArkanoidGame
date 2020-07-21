package levels;

import arkanoid.Block;
import arkanoid.GameLevel;
import biuoop.DrawSurface;
import core.Sprite;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import levels.blockDetails.BlockInBackGround;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Back ground of levels.
 */
public class BackGroundOfLevels implements Sprite {
    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite s: getSpriteList()) {
            s.drawOn(d);
        }
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        for (Sprite s: getSpriteList()) {
            s.addToGame(gameLevel);
        }
    }

    /**
     * Gets sprite list.
     *
     * @return the sprite list
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * Create circle.
     *
     * @param centerX     the center x
     * @param centerY     the center y
     * @param radius      the radius
     * @param colorFrame  the color frame
     * @param colorInside the color inside
     */
    public void createCircle(double centerX, double centerY, int radius, Color colorFrame, Color colorInside) {
        Circle circle = new Circle(centerX, centerY, radius, colorFrame, colorInside);
        getSpriteList().add(circle);
    }

    /**
     * Create circle.
     *
     * @param centerX     the center x
     * @param centerY     the center y
     * @param radius      the radius
     * @param colorInside the color inside
     */
    public void createCircle(double centerX, double centerY, int radius, Color colorInside) {
        Circle circle = new Circle(centerX, centerY, radius, colorInside, colorInside);
        getSpriteList().add(circle);
    }

    /**
     * Create block.
     *
     * @param xUpperLeft  the x upper left
     * @param yUpperLeft  the y upper left
     * @param width       the width
     * @param height      the height
     * @param colorFrame  the color frame
     * @param colorInside the color inside
     */
    public void createBlock(double xUpperLeft, double yUpperLeft, double width, double height,
                            Color colorFrame, Color colorInside) {
        BlockInBackGround blockInBackGround = new BlockInBackGround(xUpperLeft, yUpperLeft, width, height, colorInside);
        blockInBackGround.setFrameColor(colorFrame);
        getSpriteList().add(blockInBackGround);
    }

    /**
     * Create line.
     *
     * @param start the start
     * @param end   the end
     * @param color the color
     */
    public void createLine(Point start, Point end, Color color) {
        Line line = new Line(start, end);
        line.setColorOfLine(color);
        getSpriteList().add(line);
    }

    /**
     * Draw block.
     *
     * @param point  the point
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public void drawBlock(Point point, double height, double width, Color color) {
        Block b = new Block(point, height, width, color);
        b.setBlockOrNot(false);
        getSpriteList().add(b);
    }
}
