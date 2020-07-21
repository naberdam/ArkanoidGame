package levels.blockDetails;

import arkanoid.Block;
import arkanoid.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block in back ground.
 */
public class BlockInBackGround extends Block {
    private Color frameColor;

    /**
     * Instantiates a new Block in back ground.
     *
     * @param newBlock the new block
     * @param color    the color
     */
    public BlockInBackGround(Rectangle newBlock, Color color) {
        super(newBlock, color);
    }

    /**
     * Instantiates a new Block in back ground.
     *
     * @param xUpperLeft the x upper left
     * @param yUpperLeft the y upper left
     * @param width      the width
     * @param height     the height
     * @param color      the color
     */
    public BlockInBackGround(double xUpperLeft, double yUpperLeft, double width, double height, Color color) {
        super(xUpperLeft, yUpperLeft, width, height, color);
    }

    /**
     * Instantiates a new Block in back ground.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public BlockInBackGround(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColorOfBlock());
        surface.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidthOfRec(),
                (int) getCollisionRectangle().getHeightOfRec());
        surface.setColor(getFrameColor());
        surface.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidthOfRec(),
                (int) getCollisionRectangle().getHeightOfRec());
    }

    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        if (gameLevel == null) {
            return;
        }
        gameLevel.addSprite(this);
    }

    /**
     * Gets frame color.
     *
     * @return the frame color
     */
    public Color getFrameColor() {
        return frameColor;
    }

    /**
     * Sets frame color.
     *
     * @param frameColors the frame color
     */
    public void setFrameColor(Color frameColors) {
        this.frameColor = frameColors;
    }
}
