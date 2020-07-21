package levels.blockDetails;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import core.Sprite;

import java.awt.Image;

/**
 * The type Back ground image.
 */
public class BackGroundImage implements Sprite {
    private Image image;

    /**
     * Instantiates a new Back ground image.
     *
     * @param img the img
     */
    public BackGroundImage(Image img) {
        this.image = img;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add the sprite to the gameLevel.
     *
     * @param gameLevel is the module.
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}

