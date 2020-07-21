package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {

    private int countScore;

    /**
     * The Size of text.
     */
    static final int SIZE_OF_TEXT = 80;
    /**
     * The X of you win.
     */
    static final int X_OF_YOU_WIN = 200;
    /**
     * The Y of you win.
     */
    static final int Y_OF_YOU_WIN = 150;
    /**
     * The X of your score is.
     */
    static final int X_OF_YOUR_SCORE_IS = 90;
    /**
     * The Y of your score is.
     */
    static final int Y_OF_YOUR_SCORE_IS = 320;

    /**
     * Instantiates a new Win screen.
     *
     * @param countScore     the count score
     */
    public WinScreen(int countScore) {
        this.countScore = countScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawText(X_OF_YOU_WIN, Y_OF_YOU_WIN, "You Win!!!", SIZE_OF_TEXT);
        d.drawText(X_OF_YOUR_SCORE_IS, Y_OF_YOUR_SCORE_IS, "Your Score Is: " + getCountScore(), SIZE_OF_TEXT);
//        if (getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY)) {
//            setStopScreen(false);
//        }

    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Gets count score.
     *
     * @return the count score
     */
    public int getCountScore() {
        return countScore;
    }
}
