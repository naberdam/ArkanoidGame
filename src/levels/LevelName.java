package levels;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import core.Sprite;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (3 * d.getWidth()) / 4;
        int y = 20;
        d.setColor(Color.BLACK);
        d.drawText(x, y, getLevelName(), 15);
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
        if (gameLevel == null) {
            return;
        }
        gameLevel.addSprite(this);
    }
}
