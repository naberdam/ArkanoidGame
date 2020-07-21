package arkanoid;
import biuoop.DrawSurface;
import core.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<>();
    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        getSpriteList().add(s);
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
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> tempList = new ArrayList<>(getSpriteList());
        for (Sprite sprite: tempList) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param surface the surface
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface surface) {
        List<Sprite> tempList = new ArrayList<>(getSpriteList());
        for (Sprite sprite: tempList) {
            sprite.drawOn(surface);
        }
    }
    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}

