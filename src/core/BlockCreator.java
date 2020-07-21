package core;

import arkanoid.Block;

/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xLocation the x location
     * @param yLocation the y location
     * @return the block
     */
// Create a block at the specified location.
    Block create(int xLocation, int yLocation);
}
