package levels;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one.
 */
public class LevelOne extends AllLevels {
    /**
     * The Height of block subtitles.
     */
    static final double HEIGHT_OF_BLOCK_SUBTITLES = 20;
    /**
     * The Count of ball.
     */
    static final int COUNT_OF_BALL = 1;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 8;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 120;

    /**
     * Instantiates a new Level one.
     *
     * @param widthOfScreen  the width of screen
     * @param heightOfScreen the height of screen
     */
    public LevelOne(int widthOfScreen, int heightOfScreen) {
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
        List<Velocity> listForChangeVelocity = new ArrayList<>(numberOfBalls());
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(0, 7));
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
        return "Direct Hit";
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
                Color.BLACK, Color.BLACK);
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), HEIGHT_OF_BLOCK_SUBTITLES,
                Color.BLACK, Color.LIGHT_GRAY);
        backGroundOfLevels.createCircle(getWidthOfScreen() / 2.0, 150, 100,
                Color.BLUE, Color.BLACK);
        backGroundOfLevels.createCircle(getWidthOfScreen() / 2.0, 150, 70,
                Color.BLUE, Color.BLACK);
        backGroundOfLevels.createCircle(getWidthOfScreen() / 2.0, 150, 40,
                Color.BLUE, Color.BLACK);
        backGroundOfLevels.createLine(new Point(getWidthOfScreen() / 2.0, 150 - 100),
                new Point(getWidthOfScreen() / 2.0, 150 + 100), Color.BLUE);
        backGroundOfLevels.createLine(new Point((getWidthOfScreen() / 2.0) - 100, 150),
                new Point((getWidthOfScreen() / 2.0) + 100, 150), Color.BLUE);
        return backGroundOfLevels;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        List<Block> b = new ArrayList<>();
        int blockSizeH = 30;
        int blockSizeW = 30;
        Block b1 = new Block(new Point((getWidthOfScreen() / 2.0) - blockSizeH / 2.0,
                150 - blockSizeH / 2.0), blockSizeH, blockSizeW, getAllColors()[0]);
        b.add(b1);
        return b;
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
