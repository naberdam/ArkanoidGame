package levels;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level two.
 */
public class LevelTwo extends AllLevels {

    /**
     * The Height of block subtitles.
     */
    static final double HEIGHT_OF_BLOCK_SUBTITLES = 20;
    /**
     * The Angle for start.
     */
    static final double ANGLE_FOR_START = 130;
    /**
     * The Speed of ball.
     */
    static final double SPEED_OF_BALL = 7;
    /**
     * The Angle we want to skip.
     */
    static final double ANGLE_WE_WANT_TO_SKIP = 170;
    /**
     * The Increasing angle each iteration.
     */
    static final double INCREASING_ANGLE_EACH_ITERATION = 10;

    /**
     * The Width of block.
     */
    static final double WIDTH_OF_BLOCK = (152 / 3.0);
    /**
     * The Height of block.
     */
    static final double HEIGHT_OF_BLOCK = 30;
    /**
     * The Count of ball.
     */
    static final int COUNT_OF_BALL = 10;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 9;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 500;
    /**
     * The X of sun circle.
     */
    static final int X_OF_SUN_CIRCLE = 200;
    /**
     * The Y of sun circle.
     */
    static final int Y_OF_SUN_CIRCLE = 120;

    /**
     * The Radius big sun.
     */
    static final int RADIUS_BIG_SUN = 50;
    /**
     * The Radius second sun.
     */
    static final int RADIUS_SECOND_SUN = 40;
    /**
     * The Radius small sun.
     */
    static final int RADIUS_SMALL_SUN = 30;

    /**
     * The Color big sun.
     */
    static final Color COLOR_BIG_SUN = new Color(255, 240, 153);
    /**
     * The Color second sun.
     */
    static final Color COLOR_SECOND_SUN = new Color(255, 255, 0);
    /**
     * The Color small sun.
     */
    static final Color COLOR_SMALL_SUN = new Color(255, 220, 0);

    /**
     * The Color lines from sun.
     */
    static final Color COLOR_LINES_FROM_SUN = new Color(255, 240, 153);

    /**
     * The X start of line.
     */
    static final int X_START_OF_LINE = 20;
    /**
     * The X end of line.
     */
    static final int X_END_OF_LINE = 780;

    /**
     * The Start point of line.
     */
    static final Point START_POINT_OF_LINE = new Point(200, 120);
    /**
     * The Y end of line.
     */
    static final double Y_END_OF_LINE = 250;
    /**
     * The Distance between lines.
     */
    static final double DISTANCE_BETWEEN_LINES = 10;

    /**
     * Instantiates a new Level two.
     *
     * @param widthOfScreen  the width of screen
     * @param heightOfScreen the height of screen
     */
    public LevelTwo(int widthOfScreen, int heightOfScreen) {
        super(widthOfScreen, heightOfScreen);
    }

    @Override
    public int numberOfBalls() {
        return COUNT_OF_BALL;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listForChangeVelocity = new ArrayList<>(numberOfBalls());
        double angle = ANGLE_FOR_START;
        for (int i = 0; i < numberOfBalls(); i++) {
            if (angle == ANGLE_WE_WANT_TO_SKIP) {
                angle += INCREASING_ANGLE_EACH_ITERATION;
                i -= 1;
                continue;
            }
            listForChangeVelocity.add(Velocity.fromAngleAndSpeed(angle, SPEED_OF_BALL));
            angle += INCREASING_ANGLE_EACH_ITERATION;
        }
        return listForChangeVelocity;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        BackGroundOfLevels backGroundOfLevels = new BackGroundOfLevels();
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), getHeightOfScreen(),
                Color.BLACK, Color.white);
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), HEIGHT_OF_BLOCK_SUBTITLES,
                Color.BLACK, Color.LIGHT_GRAY);

        for (double i = X_START_OF_LINE; i <= X_END_OF_LINE; i += DISTANCE_BETWEEN_LINES) {
            Point start = START_POINT_OF_LINE;
            Point end = new Point(i, Y_END_OF_LINE);
            backGroundOfLevels.createLine(start, end, COLOR_LINES_FROM_SUN);
        }
        backGroundOfLevels.createCircle(X_OF_SUN_CIRCLE, Y_OF_SUN_CIRCLE, RADIUS_BIG_SUN, COLOR_BIG_SUN);
        backGroundOfLevels.createCircle(X_OF_SUN_CIRCLE, Y_OF_SUN_CIRCLE, RADIUS_SECOND_SUN, COLOR_SECOND_SUN);
        backGroundOfLevels.createCircle(X_OF_SUN_CIRCLE, Y_OF_SUN_CIRCLE, RADIUS_SMALL_SUN, COLOR_SMALL_SUN);

        return backGroundOfLevels;
    }

    @Override
    public List<Block> blocks() {
        Point tempP;
        Block block;
        int numHits = 2, countForColorOfBlocks = 1;
        List<Block> blockList = new ArrayList<>();
        for (int i = X_START_OF_LINE; i <= getWidthOfScreen() - WIDTH_OF_BLOCK; i += WIDTH_OF_BLOCK) {
            tempP = new Point(i, Y_END_OF_LINE);
            block = new Block(tempP, WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK,
                    peakColorAccordingToLocation(countForColorOfBlocks));
            block.setNumHits(numHits);
            countForColorOfBlocks += 1;
            blockList.add(block);
        }
        return blockList;
    }

    /**
     * Peak color according to location color.
     *
     * @param indexOfBlock the index of block
     * @return the color
     */
    private Color peakColorAccordingToLocation(int indexOfBlock) {
        switch (indexOfBlock) {
            case 1:
            case 2:
                return Color.red;
            case 3:
            case 4:
                return new Color(255, 204, 51);
            case 5:
            case 6:
                return Color.yellow;
            case 7:
            case 8:
            case 9:
                return new Color(0, 204, 0);
            case 10:
            case 11:
                return new Color(0, 0, 204);
            case 12:
            case 13:
                return new Color(153, 102, 0);
            case 14:
            case 15:
                return new Color(51, 204, 255);
            default:
                break;

        }
        return Color.LIGHT_GRAY;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
