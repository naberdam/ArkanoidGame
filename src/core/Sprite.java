package core;
import arkanoid.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();

    /**
     * Add to gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    void addToGame(GameLevel gameLevel);
}
