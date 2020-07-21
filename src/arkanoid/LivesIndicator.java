package arkanoid;
import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor.
     *
     * @param s is the counter.
     */
    public LivesIndicator(Counter s) {
        this.lives = s;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = d.getWidth() / 4;
        int y = 20;
        d.setColor(Color.BLACK);
        d.drawText(x, y, "life: " + lives.getValue(), 15);
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
        if (gameLevel == null) {
            return;
        }
        gameLevel.addSprite(this);
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * Sets lives.
     *
     * @param live the live
     */
    public void setLives(Counter live) {
        this.lives = live;
    }
}

