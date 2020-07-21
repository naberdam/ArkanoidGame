package arkanoid;
import biuoop.DrawSurface;

import core.Collidable;
import core.HitListener;
import core.HitNotifier;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private Color colorOfBlock;
    private int numHits;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Color scopeColor;
    private boolean blockOrNot = true;

    /**
     * Instantiates a new Block.
     *
     * @param newBlock the new block
     * @param color    the color
     */
    public Block(Rectangle newBlock, java.awt.Color color) {
        this.block = newBlock;
        this.colorOfBlock = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param xUpperLeft the x upper left
     * @param yUpperLeft the y upper left
     * @param width      the width
     * @param height     the height
     */
    public Block(double xUpperLeft, double yUpperLeft, double width, double height) {
        this.block = new Rectangle(new Point(xUpperLeft, yUpperLeft), width, height);
    }


    /**
     * Instantiates a new Block.
     *
     * @param xUpperLeft the x upper left
     * @param yUpperLeft the y upper left
     * @param width      the width
     * @param height     the height
     * @param color      the color
     */
    public Block(double xUpperLeft, double yUpperLeft, double width, double height, java.awt.Color color) {
        this(new Point(xUpperLeft, yUpperLeft), width, height, color);
    }

    /**
     * Instantiates a new Block.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.colorOfBlock = color;
    }

    /**
     * Sets hit listeners.
     *
     * @param hitListener the hit listeners
     */
    public void setHitListeners(List<HitListener> hitListener) {
        this.hitListeners = hitListener;
    }

    /**
     * Gets hit listeners.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * Gets color of block.
     *
     * @return the color of block
     */
    public Color getColorOfBlock() {
        return colorOfBlock;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Rectangle getBlock() {
        return block;
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.numHits -= 1;
        // change dx
        if ((Math.abs(collisionPoint.getX() - this.getCollisionRectangle().getUpperLeft().getX()) <= 0.00001
                && currentVelocity.getHeadWayInAxisX() > 0)
                || (Math.abs(collisionPoint.getX() - (this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidthOfRec())) <= 0.00001
                 && currentVelocity.getHeadWayInAxisX() < 0)) {
            currentVelocity = new Velocity(-currentVelocity.getHeadWayInAxisX(), currentVelocity.getHeadWayInAxisY());
        }
        // change dy
        if ((Math.abs(collisionPoint.getY() - this.getCollisionRectangle().getUpperLeft().getY()) <= 0.00001
                && currentVelocity.getHeadWayInAxisY() > 0)
                || (Math.abs(collisionPoint.getY() - (this.getCollisionRectangle().getUpperLeft().getY()
                + this.getCollisionRectangle().getHeightOfRec())) <= 0.00001
                && currentVelocity.getHeadWayInAxisY() < 0)) {
            currentVelocity = new Velocity(currentVelocity.getHeadWayInAxisX(), -currentVelocity.getHeadWayInAxisY());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * Draw on The Block.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColorOfBlock());
        surface.fillRectangle((int) getBlock().getUpperLeft().getX(), (int) getBlock().getUpperLeft().getY(),
                (int) getBlock().getWidthOfRec(), (int) getBlock().getHeightOfRec());
        drawOnScope(surface);
    }

    /**
     * Draw on scope.
     *
     * @param surface the surface
     */
    protected void drawOnScope(DrawSurface surface) {
        if (getScopeColor() != null) {
            surface.setColor(getScopeColor());
            surface.drawRectangle((int) getBlock().getUpperLeft().getX(), (int) getBlock().getUpperLeft().getY(),
                    (int) getBlock().getWidthOfRec(), (int) getBlock().getHeightOfRec());
        }

    }

    /**
     * Do Nothing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add to gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Sets num hits.
     *
     * @param hitNum the hit num
     */
    public void setNumHits(int hitNum) {
        this.numHits = hitNum;
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        if (gameLevel == null) {
            return;
        }
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        if (hl == null) {
            return;
        }
        getHitListeners().add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (hl == null) {
            return;
        }
        getHitListeners().remove(hl);
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        if (getHitListeners().isEmpty()) {
            return;
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return numHits;
    }

    /**
     * Sets location.
     *
     * @param x the x
     * @param y the y
     */
    protected void setLocation(int x, int y) {
        this.block.setLocation((double) x, (double) y);
    }

    /**
     * Gets scope color.
     *
     * @return the scope color
     */
    public Color getScopeColor() {
        return scopeColor;
    }

    /**
     * Sets scope color.
     *
     * @param scopeColors the scope color
     */
    public void setScopeColor(Color scopeColors) {
        this.scopeColor = scopeColors;
    }

    /**
     * Is block or not boolean.
     *
     * @return the boolean
     */
    public boolean isBlockOrNot() {
        return blockOrNot;
    }

    /**
     * Sets block or not.
     *
     * @param blockOrNots the block or not
     */
    public void setBlockOrNot(boolean blockOrNots) {
        this.blockOrNot = blockOrNots;
    }
}

