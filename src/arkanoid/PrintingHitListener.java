package arkanoid;

import core.HitListener;

/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * print event of hitting.
     *
     * @param beingHit the block.
     * @param hitter   the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
