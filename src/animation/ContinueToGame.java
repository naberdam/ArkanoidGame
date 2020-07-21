package animation;

import biuoop.DrawSurface;
import core.Task;
import levels.GameFlow;
import levels.LevelInformation;

import java.util.List;

/**
 * The type Continue to game.
 */
public class ContinueToGame implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     *
     * @param gf is the game flow of the levels.
     * @param l  is the list of the levels with theirs details.
     */
    public ContinueToGame(GameFlow gf, List<LevelInformation> l) {
        this.gameFlow = gf;
        this.levels = l;
    }

    /**
     * Run void.
     *
     * @return the void
     */
    @Override
    public Void run() {
        gameFlow.runLevels(this.levels);
        return null;
    }

    /**
     * Do one frame.
     *
     * @param d is the drawsurface to make the play on.
     */
    public void doOneFrame(DrawSurface d) {
        //no in use
    }

    /**
     * Should stop boolean.
     *
     * @return if we have to stop.
     */
    public boolean shouldStop() {
        return true;
    }


}
