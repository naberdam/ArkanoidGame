package levels;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level four.
 */
public class LevelFour extends AllLevels {

    /**
     * The Height of block subtitles.
     */
    static final double HEIGHT_OF_BLOCK_SUBTITLES = 20;
    /**
     * The Angle of first ball.
     */
    static final double ANGLE_OF_FIRST_BALL = 150;
    /**
     * The Angle of second ball.
     */
    static final double ANGLE_OF_SECOND_BALL = 180;
    /**
     * The Angle of last ball.
     */
    static final double ANGLE_OF_LAST_BALL = 210;
    /**
     * The Speed of ball.
     */
    static final double SPEED_OF_BALL = 7;

    /**
     * The Width of block.
     */
    static final double WIDTH_OF_BLOCK = 76;
    /**
     * The Height of block.
     */
    static final double HEIGHT_OF_BLOCK = 30;

    /**
     * The First color of circle.
     */
    static final Color FIRST_COLOR_OF_CIRCLE = new Color(204, 204, 204);
    /**
     * The Second color of circle.
     */
    static final Color SECOND_COLOR_OF_CIRCLE = new Color(160, 160, 160);
    /**
     * The Third color of circle.
     */
    static final Color THIRD_COLOR_OF_CIRCLE = new Color(190, 190, 190);

    /**
     * The First circle x.
     */
    static final double FIRST_CIRCLE_X = 120;
    /**
     * The First circle y.
     */
    static final double FIRST_CIRCLE_Y = 400;
    /**
     * The First circle radius.
     */
    static final int FIRST_CIRCLE_RADIUS = 20;

    /**
     * The Difference between first point to second in x.
     */
    static final double DIFFERENCE_BETWEEN_FIRST_POINT_TO_SECOND_IN_X = 30;
    /**
     * The Second circle y.
     */
    static final double SECOND_CIRCLE_Y = 390;
    /**
     * The Second circle radius.
     */
    static final int SECOND_CIRCLE_RADIUS = 30;

    /**
     * The Difference between first point to last in x.
     */
    static final double DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X = 60;
    /**
     * The Third circle y.
     */
    static final double THIRD_CIRCLE_Y = 400;
    /**
     * The Third circle radius.
     */
    static final int THIRD_CIRCLE_RADIUS = 30;

    /**
     * The Difference between first point to first under in x.
     */
    static final double DIFFERENCE_BETWEEN_FIRST_POINT_TO_FIRST_UNDER_IN_X = 15;
    /**
     * The Fourth circle y.
     */
    static final double FOURTH_CIRCLE_Y = 430;
    /**
     * The Fourth circle radius.
     */
    static final int FOURTH_CIRCLE_RADIUS = 25;

    /**
     * The Difference between first point to last under in x.
     */
    static final double DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_UNDER_IN_X = 50;
    /**
     * The Fifth circle y.
     */
    static final double FIFTH_CIRCLE_Y = 430;
    /**
     * The Fifth circle radius.
     */
    static final int FIFTH_CIRCLE_RADIUS = 25;

    /**
     * The Distance between start circle to start line.
     */
    static final double DISTANCE_BETWEEN_START_CIRCLE_TO_START_LINE = 10;
    /**
     * The Distance between each line.
     */
    static final double DISTANCE_BETWEEN_EACH_LINE = 8;

    /**
     * The Tendency of each line.
     */
    static final double TENDENCY_OF_EACH_LINE = 30;
    /**
     * The Color of lines from clouds.
     */
    static final Color COLOR_OF_LINES_FROM_CLOUDS = Color.white;
    /**
     * The Count of ball.
     */
    static final int COUNT_OF_BALL = 3;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 12;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 120;


    /**
     * Instantiates a new Level four.
     *
     * @param widthOfScreen  the width of screen
     * @param heightOfScreen the height of screen
     */
    public LevelFour(int widthOfScreen, int heightOfScreen) {
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
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(ANGLE_OF_FIRST_BALL, SPEED_OF_BALL));
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(ANGLE_OF_SECOND_BALL, SPEED_OF_BALL));
        listForChangeVelocity.add(Velocity.fromAngleAndSpeed(ANGLE_OF_LAST_BALL, SPEED_OF_BALL));
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
        return "Final Four";
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
                Color.BLACK, new Color(51, 204, 255));
        backGroundOfLevels.createBlock(0, 0, getWidthOfScreen(), HEIGHT_OF_BLOCK_SUBTITLES,
                Color.BLACK, Color.LIGHT_GRAY);
        for (double i = FIRST_CIRCLE_X + DISTANCE_BETWEEN_START_CIRCLE_TO_START_LINE;
             i <= FIRST_CIRCLE_X + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X
                     - DISTANCE_BETWEEN_START_CIRCLE_TO_START_LINE; i += DISTANCE_BETWEEN_EACH_LINE) {
            Point startLine = new Point(i, FIRST_CIRCLE_Y);
            Point endLine = new Point(i - TENDENCY_OF_EACH_LINE, getHeightOfScreen());
            backGroundOfLevels.createLine(startLine, endLine, COLOR_OF_LINES_FROM_CLOUDS);
        }
        backGroundOfLevels.createCircle(FIRST_CIRCLE_X, FIRST_CIRCLE_Y, FIRST_CIRCLE_RADIUS, FIRST_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(FIRST_CIRCLE_X + DIFFERENCE_BETWEEN_FIRST_POINT_TO_SECOND_IN_X,
                SECOND_CIRCLE_Y, SECOND_CIRCLE_RADIUS, SECOND_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(FIRST_CIRCLE_X + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X,
                THIRD_CIRCLE_Y, THIRD_CIRCLE_RADIUS, THIRD_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(FIRST_CIRCLE_X + DIFFERENCE_BETWEEN_FIRST_POINT_TO_FIRST_UNDER_IN_X,
                FOURTH_CIRCLE_Y, FOURTH_CIRCLE_RADIUS, SECOND_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(FIRST_CIRCLE_X + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_UNDER_IN_X,
                FIFTH_CIRCLE_Y, FIFTH_CIRCLE_RADIUS, THIRD_COLOR_OF_CIRCLE);
        double startX = getWidthOfScreen() - FIRST_CIRCLE_X - (2 * DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X);
        for (double i = startX + DISTANCE_BETWEEN_START_CIRCLE_TO_START_LINE;
             i <= startX + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X
                     - DISTANCE_BETWEEN_START_CIRCLE_TO_START_LINE; i += DISTANCE_BETWEEN_EACH_LINE) {
            Point startLine = new Point(i, FIRST_CIRCLE_Y);
            Point endLine = new Point(i - TENDENCY_OF_EACH_LINE, getHeightOfScreen());
            backGroundOfLevels.createLine(startLine, endLine, COLOR_OF_LINES_FROM_CLOUDS);
        }
        backGroundOfLevels.createCircle(startX, FIRST_CIRCLE_Y, FIRST_CIRCLE_RADIUS, FIRST_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(startX + DIFFERENCE_BETWEEN_FIRST_POINT_TO_SECOND_IN_X,
                SECOND_CIRCLE_Y, SECOND_CIRCLE_RADIUS, SECOND_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(startX + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_IN_X,
                THIRD_CIRCLE_Y, THIRD_CIRCLE_RADIUS, THIRD_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(startX + DIFFERENCE_BETWEEN_FIRST_POINT_TO_FIRST_UNDER_IN_X,
                FOURTH_CIRCLE_Y, FOURTH_CIRCLE_RADIUS, SECOND_COLOR_OF_CIRCLE);
        backGroundOfLevels.createCircle(startX + DIFFERENCE_BETWEEN_FIRST_POINT_TO_LAST_UNDER_IN_X,
                FIFTH_CIRCLE_Y, FIFTH_CIRCLE_RADIUS, THIRD_COLOR_OF_CIRCLE);
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
        int count = 5;
        List<Block> blockList = new ArrayList<>();
        for (int j = 100; j < 250; j += HEIGHT_OF_BLOCK) {
            for (int i = 20; i <= getWidthOfScreen() - WIDTH_OF_BLOCK; i += WIDTH_OF_BLOCK) {
                tempP = new Point(i, j);
                block = new Block(tempP, WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK, getAllColors()[j % 7]);
                block.setNumHits(count);
                blockList.add(block);
            }
            count -= 1;
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
