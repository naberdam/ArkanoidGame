package arkanoid;

import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import animation.Animation;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.Counter;
import core.Sprite;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import levels.LevelName;

import java.util.List;
import java.awt.Color;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private int heightOfGui;
    private int widthOfGui;
    private Counter counterHitBlocks;
    private Counter counterBallsOnScreen;
    private Counter counterScore;
    private Counter counterLivesIndicator;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private LivesIndicator livesIndicator;
    private Paddle paddle;
    private boolean running;
    private Ball[] ballArray;
    private List<Block> blockCollidibleList;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private LevelName levelName;
    private Color[] allColors = {Color.RED, Color.BLUE, Color.GREEN, Color.pink, Color.YELLOW,
            Color.DARK_GRAY, Color.LIGHT_GRAY};
    /**
     * The Height of paddle.
     */
    static final double HEIGHT_OF_PADDLE = 25;
    /**
     * The Radius of ball.
     */
    static final int RADIUS_OF_BALL = 7;
    /**
     * The Height of block.
     */
    static final double HEIGHT_OF_BLOCK = 30;
    /**
     * The Size of block frame.
     */
    static final int SIZE_OF_BLOCK_FRAME = 20;
    /**
     * The Start of up block frame.
     */
    static final int START_OF_UP_BLOCK_FRAME = 20;
    /**
     * The Color of block frame.
     */
    static final Color COLOR_OF_BLOCK_FRAME = Color.darkGray;

    /**
     * The Initialize num hits of blocks frames.
     */
    static final int INITIALIZE_NUM_HITS_OF_BLOCKS_FRAMES = 0;
    /**
     * The Color of balls.
     */
    static final Color COLOR_OF_BALLS = Color.white;
    /**
     * The Safety space paddle from balls.
     */
    static final double SAFETY_SPACE_PADDLE_FROM_BALLS = 10;
    /**
     * The Block indicator name level score and lifes.
     */
    static final Block BLOCK_INDICATOR_NAME_LEVEL_SCORE_AND_LIFES =
            new Block(0, 0, 800, 20, Color.white);


    /**
     * Instantiates a new GameLevel.
     *
     * @param height           the height
     * @param width            the width
     * @param levelInformation the level information
     * @param runner           the runner
     * @param keyboard         the keyboard
     * @param countLives       the count lives
     * @param countScore       the count score
     */
    public GameLevel(int height, int width, LevelInformation levelInformation,
                     AnimationRunner runner, KeyboardSensor keyboard, Counter countLives, Counter countScore) {
        this.heightOfGui = height;
        this.widthOfGui = width;
        this.counterLivesIndicator = countLives;
        this.livesIndicator = new LivesIndicator(counterLivesIndicator);
        this.counterScore = countScore;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.blockCollidibleList = levelInformation.blocks();
        this.levelName = new LevelName(levelInformation.levelName());
    }

    /**
     * Gets height of gui.
     *
     * @return the height of gui
     */
    public int getHeightOfGui() {
        return heightOfGui;
    }

    /**
     * Gets width of gui.
     *
     * @return the width of gui
     */
    public int getWidthOfGui() {
        return widthOfGui;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Get all colors color [ ].
     *
     * @return the color [ ]
     */
    public Color[] getAllColors() {
        return allColors;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return getRunner().getGui();
    }

    /**
     * Gets level information.
     *
     * @return the level information
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public LevelName getLevelName() {
        return levelName;
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        getLevelInformation().getBackground().addToGame(this);
        paddleAddToGame();
        makeBlockFrames();
        makeIndicators();
        blocksAddToGame();
    }

    /**
     * Play one turn.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        getPaddle().takePaddleToCenter();
        ballArrayAddToGame();
        makeDeathRegion();
        getRunner().run(new CountdownAnimation(2, 3, getSprites()));
        this.running = true;
        getRunner().run(this);
    }

    /**
     * Gets runner.
     *
     * @return the runner
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * Sets running.
     *
     * @param runnings the running
     */
    public void setRunning(boolean runnings) {
        this.running = runnings;
    }

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Do one frame.
     *
     * @param drawSurface the draw surface
     */
    public void doOneFrame(DrawSurface drawSurface) {
        getSprites().drawAllOn(drawSurface);
        getSprites().notifyAllTimePassed();
        if (getCounterHitBlocks().getValue() <= 0) {
            allBlocksGone();
            setRunning(false);
        }
        if (getCounterBallsOnScreen().getValue() <= 0) {
            getCounterLivesIndicator().decrease(1);
            setRunning(false);
        }
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(getKeyboard(),
                    SPACE_KEY, new PauseScreen(getKeyboard()));
            this.runner.run(keyPressStoppableAnimation);
        }
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return !isRunning();
    }

    /**
     * Sets counter balls on screen.
     *
     * @param counterBallsOnScreens the counter balls on screens
     */
    public void setCounterBallsOnScreen(Counter counterBallsOnScreens) {
        this.counterBallsOnScreen = counterBallsOnScreens;
    }

    /**
     * All blocks gone.
     */
    public void allBlocksGone() {
        getCounterScore().increase(100);
    }


    /**
     * Sets counter score.
     *
     * @param counterScores the counter scores
     */
    public void setCounterScore(Counter counterScores) {
        this.counterScore = counterScores;
    }

    /**
     * Paddle add to game.
     */
    public void paddleAddToGame() {
        Point pointPaddle = new Point((getWidthOfGui() / 2.0) - getLevelInformation().paddleWidth() / 2.0,
                getHeightOfGui() - HEIGHT_OF_BLOCK);
        Rectangle tempRect = new Rectangle(pointPaddle, getLevelInformation().paddleWidth(), HEIGHT_OF_PADDLE);
        this.paddle = new Paddle(tempRect, getGui(), SIZE_OF_BLOCK_FRAME,
                getWidthOfGui() - SIZE_OF_BLOCK_FRAME, getLevelInformation().paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Gets paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Ball array add to game ball [ ].
     */
    public void ballArrayAddToGame() {
        this.ballArray = new Ball[getLevelInformation().numberOfBalls()];
        this.counterBallsOnScreen = new Counter(getLevelInformation().numberOfBalls());
        Point centerBall = new Point(getPaddle().getCollisionRectangle().getUpperRight().getX()
                - (getPaddle().getCollisionRectangle().getWidthOfRec() / 2),
                getPaddle().getCollisionRectangle().getUpperRight().getY() - SAFETY_SPACE_PADDLE_FROM_BALLS);

        for (int i = 0; i < getLevelInformation().numberOfBalls(); i++) {
            ballArray[i] = new Ball(centerBall, RADIUS_OF_BALL, COLOR_OF_BALLS);
            ballArray[i].setFrameGui(SIZE_OF_BLOCK_FRAME, SIZE_OF_BLOCK_FRAME * 2,
                    getWidthOfGui() - SIZE_OF_BLOCK_FRAME, getHeightOfGui());
            ballArray[i].setVelocity(getLevelInformation().initialBallVelocities().get(i));
            ballArray[i].addToGame(this);
        }
    }

    /**
     * Make death region.
     */
    public void makeDeathRegion() {
        BallRemover ballRemover = new BallRemover(this, getCounterBallsOnScreen());
        Rectangle recOfDownBlock = new Rectangle(0, getHeightOfGui(),
                getWidthOfGui(), SIZE_OF_BLOCK_FRAME);
        Block downBlock = new Block(recOfDownBlock, COLOR_OF_BLOCK_FRAME);
        downBlock.addToGame(this);
        downBlock.addHitListener(ballRemover);
    }

    /**
     * Get ball array ball [ ].
     *
     * @return the ball [ ]
     */
    public Ball[] getBallArray() {
        return ballArray;
    }

    /**
     * Gets counter score.
     *
     * @return the counter score
     */
    public Counter getCounterScore() {
        return counterScore;
    }


    /**
     * Gets block collidible list.
     *
     * @return the block collidible list
     */
    public List<Block> getBlockCollidibleList() {
        return blockCollidibleList;
    }

    /**
     * Blocks add to game.
     */
    public void blocksAddToGame() {
        this.counterHitBlocks = new Counter(getBlockCollidibleList().size());
        BlockRemover blockRemover = new BlockRemover(this, getCounterHitBlocks());
        PrintingHitListener printingHitListener = new PrintingHitListener();
        this.scoreTrackingListener = new ScoreTrackingListener(getCounterScore());
        for (Block b : getBlockCollidibleList()) {
            b.addHitListener(blockRemover);
            b.addHitListener(printingHitListener);
            b.addHitListener(getScoreTrackingListener());
            b.addToGame(this);
        }
    }

    /**
     * Gets score tracking listener.
     *
     * @return the score tracking listener
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    /**
     * Make score indicator.
     */
    public void makeIndicators() {
        this.scoreIndicator = new ScoreIndicator(getCounterScore());
        Block blockIndicator = BLOCK_INDICATOR_NAME_LEVEL_SCORE_AND_LIFES;
        getSprites().addSprite(blockIndicator);
        getScoreIndicator().addToGame(this);
        getLivesIndicator().addToGame(this);
        getLevelName().addToGame(this);
    }

    /**
     * Gets counter lives indicator.
     *
     * @return the counter lives indicator
     */
    public Counter getCounterLivesIndicator() {
        return counterLivesIndicator;
    }

    /**
     * Gets lives indicator.
     *
     * @return the lives indicator
     */
    public LivesIndicator getLivesIndicator() {
        return livesIndicator;
    }

    /**
     * Gets score indicator.
     *
     * @return the score indicator
     */
    public ScoreIndicator getScoreIndicator() {
        return scoreIndicator;
    }

    /**
     * Make block frames.
     */
    public void makeBlockFrames() {
        Block[] blockFrame = new Block[3];
        Rectangle upBlock = new Rectangle(START_OF_UP_BLOCK_FRAME, START_OF_UP_BLOCK_FRAME, getWidthOfGui(),
                SIZE_OF_BLOCK_FRAME);
        blockFrame[0] = new Block(upBlock, COLOR_OF_BLOCK_FRAME);
        Rectangle leftBlock = new Rectangle(0, 0, SIZE_OF_BLOCK_FRAME, getHeightOfGui());
        blockFrame[1] = new Block(leftBlock, COLOR_OF_BLOCK_FRAME);
        Rectangle rightBlock = new Rectangle(getWidthOfGui() - SIZE_OF_BLOCK_FRAME, 0,
                SIZE_OF_BLOCK_FRAME, getHeightOfGui());
        blockFrame[2] = new Block(rightBlock, COLOR_OF_BLOCK_FRAME);
        for (int i = 0; i < 3; i++) {
            this.addSprite(blockFrame[i]);
            blockFrame[i].setNumHits(INITIALIZE_NUM_HITS_OF_BLOCKS_FRAMES);
        }

    }


    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        getEnvironment().removeCollidable(c);
        getBlockCollidibleList().remove(c);
    }

    /**
     * Gets counterHitBlocks.
     *
     * @return the counterHitBlocks
     */
    public Counter getCounterHitBlocks() {
        return counterHitBlocks;
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        if (s == null) {
            return;
        }
        getSprites().removeSprite(s);
    }

    /**
     * Gets counter balls on screen.
     *
     * @return the counter balls on screen
     */
    public Counter getCounterBallsOnScreen() {
        return counterBallsOnScreen;
    }

}
