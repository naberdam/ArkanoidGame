package animation;

import arkanoid.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

import static java.lang.System.currentTimeMillis;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long currentTime;
    private double counter;

    /**
     * The Size of text 3 2 1 go.
     */
    static final int SIZE_OF_TEXT_3_2_1_GO = 83;
    /**
     * The Distance of text.
     */
    static final int DISTANCE_OF_TEXT = 20;
    /**
     * The Mult of seconds.
     */
    static final int MULT_OF_SECONDS = 1000;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentTime = currentTimeMillis();
        this.counter = countFrom;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if ((currentTimeMillis() - getCurrentTime()) >= (MULT_OF_SECONDS * getNumOfSeconds() / getCounter())) {
            decreaseCountFrom(1);
            setCurrentTime(currentTimeMillis());
        }
        getGameScreen().drawAllOn(d);
        d.setColor(Color.magenta);
        String textToScreen = getCountFrom() + "...";
        if (getCountFrom() == 0) {
            textToScreen = "Go!";
            d.drawText(d.getWidth() / 2 - DISTANCE_OF_TEXT, d.getHeight() / 2 + DISTANCE_OF_TEXT,
                    textToScreen, SIZE_OF_TEXT_3_2_1_GO);
        } else if (getCountFrom() < 0) {
            return;
        } else {
            d.drawText(d.getWidth() / 2 - DISTANCE_OF_TEXT, d.getHeight() / 2 + DISTANCE_OF_TEXT,
                    textToScreen, SIZE_OF_TEXT_3_2_1_GO);
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return (getCountFrom() < 0);
    }

    /**
     * Gets num of seconds.
     *
     * @return the num of seconds
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * Gets count from.
     *
     * @return the count from
     */
    public int getCountFrom() {
        return countFrom;
    }

    /**
     * Gets game screen.
     *
     * @return the game screen
     */
    public SpriteCollection getGameScreen() {
        return gameScreen;
    }

    /**
     * Gets current time.
     *
     * @return the current time
     */
    public long getCurrentTime() {
        return currentTime;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    private double getCounter() {
        return counter;
    }

    /**
     * Sets current time.
     *
     * @param currentTime1 the current time
     */
    public void setCurrentTime(long currentTime1) {
        this.currentTime = currentTime1;
    }

    /**
     * Decrease count from.
     *
     * @param numToDecrease the num to decrease
     */
    public void decreaseCountFrom(int numToDecrease) {
        this.countFrom -= numToDecrease;
    }
}
