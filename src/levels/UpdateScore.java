package levels;

import animation.Animation;
import animation.HighScoresTable;
import animation.ScoreInfo;
import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.io.File;
import java.io.IOException;

/**
 * The type Update score.
 */
public class UpdateScore implements Animation {
    private GUI gui;
    private boolean stop;
    private HighScoresTable highScoresTable;
    private int score;
    private File file;

    /**
     * Constructor.
     *
     * @param gui             for the sensor or dialog.
     * @param highScoresTable HST
     * @param score           the score for now.
     * @param file1           the file 1
     */
    public UpdateScore(GUI gui, HighScoresTable highScoresTable, int score, File file1) {
        this.gui = gui;
        this.stop = false;
        this.highScoresTable = highScoresTable;
        this.score = score;
        this.file = file1;
    }

    /**
     * do one play in the animation.
     *
     * @param d is the drawsurface to make the play on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //check id the user should be entered to the table.
        if (highScoresTable.getRank(this.score) <= highScoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, this.score);
            highScoresTable.add(scoreInfo);
            try {
                highScoresTable.save(getFile());
            } catch (IOException e) {
                System.out.println("cant save high score table");
            }
        }
        this.stop = true;
    }

    /**
     * chech if the play should pause.
     *
     * @return true is has to stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Is stop boolean.
     *
     * @return the boolean
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * Gets high scores table.
     *
     * @return the high scores table
     */
    public HighScoresTable getHighScoresTable() {
        return highScoresTable;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
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
