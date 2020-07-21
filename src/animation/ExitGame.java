package animation;

import biuoop.DrawSurface;

/**
 * The type Exit game.
 */
public class ExitGame implements Animation {

    /**
     * Do one frame.
     *
     * @param d is the drawsurface to make the play on.
     */
    public void doOneFrame(DrawSurface d) {
        drawOn(d);
        System.exit(0);
    }

    /**
     * Should stop boolean.
     *
     * @return if we have to stop.
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * draw the BG etc on the drawsurface.
     * @param d is the draw board.
     */
    private void drawOn(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Have a nice day", 32);
    }


}