package animation;

import core.Task;

/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor.
     *
     * @param runner              is the runner.
     * @param highScoresAnimation is the HST.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * run this mission.
     *
     * @return null. void
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}
