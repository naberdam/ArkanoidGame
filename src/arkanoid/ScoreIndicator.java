package arkanoid;
import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param s is the string to print on the screen.
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is the draw surface.
     */
    public void drawOn(DrawSurface d) {
        int x = (d.getWidth() / 2) - 20;
        int y = 20;
        d.setColor(Color.BLACK);
        d.drawText(x, y, "Score: " + score.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
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
     * Sets score.
     *
     * @param scores the scores
     */
    public void setScore(Counter scores) {
        this.score = scores;
    }
}

