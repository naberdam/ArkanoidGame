package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param games        the games
     * @param removedBlock the removed block
     */
    public BlockRemover(GameLevel games, Counter removedBlock) {
        this.gameLevel = games;
        this.remainingBlocks = removedBlock;
    }

    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
// Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 0) {
            beingHit.removeFromGame(gameLevel);
            getRemainingBlocks().decrease(1);
            beingHit.removeHitListener(this);
        }
    }

    /**
     * Gets gameLevel.
     *
     * @return the gameLevel
     */
    public GameLevel getGameLevel() {
        return gameLevel;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
