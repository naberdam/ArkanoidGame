package levels;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level three.
 */
public class LevelThree extends AllLevels {

    /**
     * The Num hits of top line blocks.
     */
    static final int NUM_HITS_OF_TOP_LINE_BLOCKS = 2;
    /**
     * The Num hits of other lines blocks.
     */
    static final int NUM_HITS_OF_OTHER_LINES_BLOCKS = 1;
    /**
     * The How many blocks line.
     */
    static final int HOW_MANY_BLOCKS_LINE = 5;
    /**
     * The Distance from the y upper limit.
     */
    static final int DISTANCE_FROM_THE_Y_UPPER_LIMIT = 180;
    /**
     * The Number of blocks that decrease each line.
     */
    static final int NUMBER_OF_BLOCKS_THAT_DECREASE_EACH_LINE = 2;
    /**
     * The Width of block.
     */
    static final double WIDTH_OF_BLOCK = 60;
    /**
     * The Height of block.
     */
    static final double HEIGHT_OF_BLOCK = 30;
    /**
     * The Size of block frame.
     */
    static final int SIZE_OF_BLOCK_FRAME = 20;
    /**
     * The Safety decrease block.
     */
    static final int SAFETY_DECREASE_BLOCK = 2;
    /**
     * The Height of block subtitles.
     */
    static final double HEIGHT_OF_BLOCK_SUBTITLES = 20;
    /**
     * The Start x of lower block.
     */
    static final double START_X_OF_LOWER_BLOCK = 80;
    /**
     * The Start y of lower block.
     */
    static final double START_Y_OF_LOWER_BLOCK = 450;
    /**
     * The Width of lower block.
     */
    static final double WIDTH_OF_LOWER_BLOCK = 110;
    /**
     * The Height of lower block.
     */
    static final double HEIGHT_OF_LOWER_BLOCK = 150;

    /**
     * The Start x of middle block.
     */
    static final double START_X_OF_MIDDLE_BLOCK = 120;
    /**
     * The Start y of middle block.
     */
    static final double START_Y_OF_MIDDLE_BLOCK = 400;
    /**
     * The Width of middle block.
     */
    static final double WIDTH_OF_MIDDLE_BLOCK = 30;
    /**
     * The Height of middle block.
     */
    static final double HEIGHT_OF_MIDDLE_BLOCK = 50;

    /**
     * The Start x of higher block.
     */
    static final double START_X_OF_HIGHER_BLOCK = 130;
    /**
     * The Start y of higher block.
     */
    static final double START_Y_OF_HIGHER_BLOCK = 250;
    /**
     * The Width of higher block.
     */
    static final double WIDTH_OF_HIGHER_BLOCK = 10;
    /**
     * The Height of higher block.
     */
    static final double HEIGHT_OF_HIGHER_BLOCK = 150;

    /**
     * The Start x center circle.
     */
    static final double START_X_CENTER_CIRCLE = 135;
    /**
     * The Start y center circle.
     */
    static final double START_Y_CENTER_CIRCLE = 235;
    /**
     * The Radius of biggest circle.
     */
    static final int RADIUS_OF_BIGGEST_CIRCLE = 15;
    /**
     * The Radius of middle circle.
     */
    static final int RADIUS_OF_MIDDLE_CIRCLE = 10;
    /**
     * The Radius of smallest circle.
     */
    static final int RADIUS_OF_SMALLEST_CIRCLE = 5;

    /**
     * The Width of small block.
     */
    static final double WIDTH_OF_SMALL_BLOCK = 10;
    /**
     * The Height of small block.
     */
    static final double HEIGHT_OF_SMALL_BLOCK = 15;
    /**
     * The Color of small block.
     */
    static final Color COLOR_OF_SMALL_BLOCK = Color.white;
    /**
     * The Space between small blocks in lower block.
     */
    static final double SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK = 10;

    /**
     * The Color of biggest circle.
     */
    static final Color COLOR_OF_BIGGEST_CIRCLE = Color.orange;
    /**
     * The Color of middle circle.
     */
    static final Color COLOR_OF_MIDDLE_CIRCLE = Color.red;
    /**
     * The Color of smallest circle.
     */
    static final Color COLOR_OF_SMALLEST_CIRCLE = Color.white;

    /**
     * The Angle of first ball.
     */
    static final double ANGLE_OF_FIRST_BALL = 210;
    /**
     * The Angle of second ball.
     */
    static final double ANGLE_OF_SECOND_BALL = 150;
    /**
     * The Speed of ball.
     */
    static final double SPEED_OF_BALL = 7;
    /**
     * The Color of gui.
     */
    static final Color COLOR_OF_GUI = new Color(0, 153, 0);
    /**
     * The Count of ball.
     */
    static final int COUNT_OF_BALL = 2;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 10;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 120;


    /**
     * Instantiates a new Level three.
     *
     * @param widthOfScreen  the width of screen
     * @param heightOfScreen the height of screen
     */
    public LevelThree(int widthOfScreen, int heightOfScreen) {
        super(widthOfScreen, heightOfScreen);
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return COUNT_OF_BALL;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listForChangeVelocity = new ArrayList<>(this.numberOfBalls());
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(ANGLE_OF_FIRST_BALL, SPEED_OF_BALL));
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(ANGLE_OF_SECOND_BALL, SPEED_OF_BALL));
        return listForChangeVelocity;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        BackGroundOfLevels backGroundOfLevels = new BackGroundOfLevels();
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), getHeightOfScreen(),
                Color.BLACK, COLOR_OF_GUI);
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), HEIGHT_OF_BLOCK_SUBTITLES,
                Color.BLACK, Color.LIGHT_GRAY);
        backGroundOfLevels.createBlock(START_X_OF_LOWER_BLOCK, START_Y_OF_LOWER_BLOCK, WIDTH_OF_LOWER_BLOCK,
                HEIGHT_OF_LOWER_BLOCK, Color.black, Color.black);
        for (double i = START_Y_OF_LOWER_BLOCK + SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK;
             i < getHeightOfScreen();
             i += SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK + HEIGHT_OF_SMALL_BLOCK) {
            for (double j = START_X_OF_LOWER_BLOCK + SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK;
                 j < START_X_OF_LOWER_BLOCK + WIDTH_OF_LOWER_BLOCK - SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK;
                 j += SPACE_BETWEEN_SMALL_BLOCKS_IN_LOWER_BLOCK + WIDTH_OF_SMALL_BLOCK) {
                backGroundOfLevels.createBlock(j, i, WIDTH_OF_SMALL_BLOCK, HEIGHT_OF_SMALL_BLOCK,
                        COLOR_OF_SMALL_BLOCK, COLOR_OF_SMALL_BLOCK);
            }
        }
        backGroundOfLevels.createBlock(START_X_OF_MIDDLE_BLOCK, START_Y_OF_MIDDLE_BLOCK, WIDTH_OF_MIDDLE_BLOCK,
                HEIGHT_OF_MIDDLE_BLOCK, Color.DARK_GRAY, Color.DARK_GRAY);
        backGroundOfLevels.createBlock(START_X_OF_HIGHER_BLOCK, START_Y_OF_HIGHER_BLOCK, WIDTH_OF_HIGHER_BLOCK,
                HEIGHT_OF_HIGHER_BLOCK, Color.GRAY, Color.GRAY);
        backGroundOfLevels.createCircle(START_X_CENTER_CIRCLE, START_Y_CENTER_CIRCLE, RADIUS_OF_BIGGEST_CIRCLE,
                COLOR_OF_BIGGEST_CIRCLE, COLOR_OF_BIGGEST_CIRCLE);
        backGroundOfLevels.createCircle(START_X_CENTER_CIRCLE, START_Y_CENTER_CIRCLE, RADIUS_OF_MIDDLE_CIRCLE,
                COLOR_OF_MIDDLE_CIRCLE, COLOR_OF_MIDDLE_CIRCLE);
        backGroundOfLevels.createCircle(START_X_CENTER_CIRCLE, START_Y_CENTER_CIRCLE, RADIUS_OF_SMALLEST_CIRCLE,
                COLOR_OF_SMALLEST_CIRCLE, COLOR_OF_SMALLEST_CIRCLE);
        return backGroundOfLevels;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        Point tempP;
        Block block;
        List<Block> blockList = new ArrayList<>();
        int numBlocksInTopLine = (int) ((getWidthOfScreen() / WIDTH_OF_BLOCK) - SAFETY_DECREASE_BLOCK);
        double xStartOfBlocks = getWidthOfScreen() - SIZE_OF_BLOCK_FRAME - (2 * WIDTH_OF_BLOCK);
        for (int j = 0; j < HOW_MANY_BLOCKS_LINE; j++) {
            for (int i = 0; i < numBlocksInTopLine - (j * NUMBER_OF_BLOCKS_THAT_DECREASE_EACH_LINE); i++) {
                tempP = new Point(xStartOfBlocks - i * WIDTH_OF_BLOCK, (j * HEIGHT_OF_BLOCK)
                        + DISTANCE_FROM_THE_Y_UPPER_LIMIT);
                //top line
                if (j == 0) {
                    block = new Block(tempP, WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK, getAllColors()[j]);
                    block.setNumHits(NUM_HITS_OF_TOP_LINE_BLOCKS);
                    //other line
                } else {
                    block = new Block(tempP, WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK, getAllColors()[j]);
                    block.setNumHits(NUM_HITS_OF_OTHER_LINES_BLOCKS);
                }
                blockList.add(block);
            }
        }
        return blockList;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
