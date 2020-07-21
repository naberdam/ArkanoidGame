package levels.blockDetails;

import arkanoid.Block;
import core.BlockCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidths  the spacer widths
     * @param blockCreators the block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths,
                                    Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s the symbol
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol..
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    the symbol
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);

    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return spacerWidths.get(s);
    }

    /**
     * Gets block line.
     *
     * @param line the line
     * @param x    the x
     * @param y    the y
     * @return the block line
     */
    public List<Block> getBlockLine(String line, int x, int y) {
        List<Block> blockList = new ArrayList<>();
        int xpos = x;
        int lastWidth;
        for (int i = 0; i < line.length(); i++) {
            String s = line.substring(i, i + 1);
            if (isBlockSymbol(s)) {
                Block b = getBlock(s, xpos, y);
                lastWidth = (int) b.getBlock().getWidthOfRec();
                blockList.add(b);
                xpos += (lastWidth);
            } else if (isSpaceSymbol(s)) {
                xpos += getSpaceWidth(s);
            }
        }
        return blockList;
    }

    /**
     * Gets block table.
     *
     * @param linesList the lines list
     * @param x         the x
     * @param y         the y
     * @param rowHeight the row height
     * @return the block table
     */
    public List<Block> getBlockTable(List<String> linesList, int x, int y, int rowHeight) {
        List<Block> blockList = new ArrayList<>();
        int ypos = y;
        for (int i = 0; i < linesList.size(); i++) {
            if (i != 0) {
                ypos += rowHeight;
            }
            blockList.addAll(getBlockLine(linesList.get(i), x, ypos));
        }
        return blockList;
    }
}

