package animation;

import java.io.Serializable;

/**
 * The type Score info.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name1  the name 1
     * @param score1 the score 1
     */
    public ScoreInfo(String name1, int score1) {
        this.name = name1;
        this.score = score1;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "         Score: " + getScore();
    }
}
