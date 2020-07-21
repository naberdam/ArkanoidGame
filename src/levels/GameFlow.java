package levels;


import animation.Animation;
import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.HighScoresTable;
import animation.WinScreen;
import arkanoid.GameLevel;
import biuoop.KeyboardSensor;
import core.Counter;

import java.io.File;
import java.util.List;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private int heightOfGui;
    private int widthOfGui;
    private HighScoresAnimation highScoresAnimation;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param fileName     the file name
     * @param ar           the animation runner
     * @param counterLives the counterLives
     * @param height       the height of gui
     * @param width        the width of gui
     * @param highKey      the high key
     * @param key          the keyboard sensor
     */
    public GameFlow(String fileName, AnimationRunner ar, int counterLives, int height, int width,
                    String highKey, KeyboardSensor key) {

        this.animationRunner = ar;
        this.keyboardSensor = key;
        this.lives = new Counter(counterLives);
        this.score = new Counter(0);
        this.heightOfGui = height;
        this.widthOfGui = width;
        this.file = new File(fileName);
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(this.file);
        this.highScoresAnimation = new HighScoresAnimation(highScoresTable, highKey);

    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isThereLife = true;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(getHeightOfGui(), getWidthOfGui(), levelInfo, getAnimationRunner(),
                    getKeyboardSensor(), getLives(), getScore());
            level.initialize();
            while (level.getCounterHitBlocks().getValue() > 0 && level.getCounterLivesIndicator().getValue() > 0) {
                level.playOneTurn();
            }
            if (getLives().getValue() <= 0) {
                isThereLife = false;
                break;
            }
        }
        if (isThereLife) {
            WinScreen winScreen = new WinScreen(getScore().getValue());
            endGame(winScreen);
        } else {
            GameOverScreen gameOverScreen = new GameOverScreen(getScore().getValue());
            endGame(gameOverScreen);
        }
        //add score to the table
        UpdateScore updateScore = new UpdateScore(animationRunner.getGui(),
                this.highScoresAnimation.getHighScoresTable(), score.getValue(), getFile());
        while (!updateScore.shouldStop()) {
            this.animationRunner.run(updateScore);
        }
        getAnimationRunner().run(new KeyPressStoppableAnimation(getKeyboardSensor(), SPACE_KEY,
                getHighScoresAnimation()));

    }

    /**
     * End game.
     *
     * @param endScreen the end screen
     */
    public void endGame(Animation endScreen) {
        KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(getKeyboardSensor(),
                SPACE_KEY, endScreen);
        getAnimationRunner().run(keyPressStoppableAnimation);
    }

    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }

    /**
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
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
     * Gets high scores animation.
     *
     * @return the high scores animation
     */
    public HighScoresAnimation getHighScoresAnimation() {
        return highScoresAnimation;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }
}
