package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Menu;
import core.Task;
import levels.GameFlow;
import levels.LevelInformation;
import levels.LevelSpecificationReader;

import java.io.File;
import java.util.List;
import java.util.TreeMap;

/**
 * The type Before game begins.
 */
public class BeforeGameBegins implements Animation {


    private KeyboardSensor keyboardSensor;
    private HighScoresAnimation highScoresAnimation;
    private AnimationRunner animationRunner;
    private String title;
    private String playKey;
    private String startHigh;
    private String exitKey;
    private boolean stop;
    private String highKey;
    private Menu<Task<Void>> myMenu;
    private String setLevelName;
    private TreeMap<String, String> dictOfMenu;
    private File file;

    /**
     * The Num of lives.
     */
    private static final int NUM_OF_LIVES = 7;
    /**
     * The Filename.
     */
    private static final String FILENAME = "highscores.txt";
    /**
     * The High key.
     */
    private static final String HIGH_KEY = KeyboardSensor.SPACE_KEY;
    /**
     * The Screen height.
     */
    private static final int SCREEN_HEIGHT = 600;
    /**
     * The Screen width.
     */
    private static final int SCREEN_WIDTH = 800;

    /**
     * Conctructor.
     *
     * @param ar           runner.
     * @param dictOfMenu   the dict of menu
     * @param dict         dictonary of keys.
     * @param levelSetName the level set name
     */
    public BeforeGameBegins(AnimationRunner ar, TreeMap<String, String> dictOfMenu,
                            TreeMap<String, String> dict, String levelSetName) {
        this.keyboardSensor = ar.getGui().getKeyboardSensor();
        this.dictOfMenu = dictOfMenu;
        this.animationRunner = ar;
        this.title = dictOfMenu.get("titleOfMenu");
        this.highKey = dict.get("highKey");
        this.playKey = dict.get("playKey");
        this.startHigh = dict.get("startHigh");
        this.exitKey = dict.get("exitKey");
        this.stop = false;
        this.setLevelName = levelSetName;
        this.myMenu = initializationMenu();
        this.file = new File(FILENAME);

    }


    /**
     * Initialize levels by txt list.
     *
     * @param nameOfFile the name of file
     * @return the list
     */
    private List<LevelInformation> initializeLevelsByTxt(String nameOfFile) {
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        return lsr.fromReader(new File(nameOfFile));
    }



    /**
     * inition the menu list.
     *
     * @return the orderes menu.
     */
    private Menu<Task<Void>> initializationMenu() {

        GameFlow gameFlow = new GameFlow(FILENAME, getAnimationRunner(), NUM_OF_LIVES, SCREEN_HEIGHT, SCREEN_WIDTH,
                HIGH_KEY, getKeyboardSensor());
        Menu<Task<Void>> menu = new MenuAnimation<>(getTitle(), getKeyboardSensor(), getAnimationRunner());
        menu.addSelection(getStartHigh(), getDictOfMenu().get("hstMsg"),
                new ShowHiScoresTask(getAnimationRunner(),
                        new KeyPressStoppableAnimation(getKeyboardSensor(), getHighKey(),
                                gameFlow.getHighScoresAnimation())));
        menu.addSelection(getExitKey(), getDictOfMenu().get("exitMsg"),
                new ShowHiScoresTask(getAnimationRunner(),
                        new KeyPressStoppableAnimation(getKeyboardSensor(), null, new ExitGame())));

        //subMenu--
        Menu<Task<Void>> subMenu = new MenuAnimation<>(getTitle(), getKeyboardSensor(), getAnimationRunner());
        File newFile = new File(getSetLevelName());
        ReadSubMenu rsm = new ReadSubMenu(newFile);
        if (rsm.getSub() == null) {
            System.out.println("Error: level_sets.txt is empty");
            System.exit(1);
        }
        TreeMap<String, SubMenuPath> path = rsm.getSub();
        for (String s : rsm.getKeys()) {
            subMenu.addSelection(s, path.get(s).getName(),
                    new ContinueToGame(gameFlow, initializeLevelsByTxt(path.get(s).getPath())));
        }
        menu.addSubMenu(getPlayKey(), getDictOfMenu().get("playMsg"), subMenu);
        //endOfSubmenu
        return menu;
    }

    /**
     * do one play in the animation.
     *
     * @param d  is the drawsurface to make the play on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        myMenu = initializationMenu();
        if (getMyMenu().getStatus() == null) {
            getAnimationRunner().run(getMyMenu());
            Task<Void> task = getMyMenu().getStatus();
            task.run();
            getMyMenu().reset();
        }

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
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
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
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
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
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets play key.
     *
     * @return the play key
     */
    public String getPlayKey() {
        return playKey;
    }

    /**
     * Gets start high.
     *
     * @return the start high
     */
    public String getStartHigh() {
        return startHigh;
    }

    /**
     * Gets exit key.
     *
     * @return the exit key
     */
    public String getExitKey() {
        return exitKey;
    }

    /**
     * Gets high key.
     *
     * @return the high key
     */
    public String getHighKey() {
        return highKey;
    }

    /**
     * Gets my menu.
     *
     * @return the my menu
     */
    public Menu<Task<Void>> getMyMenu() {
        return myMenu;
    }

    /**
     * Gets set level name.
     *
     * @return the set level name
     */
    public String getSetLevelName() {
        return setLevelName;
    }

    /**
     * Gets dict of menu.
     *
     * @return the dict of menu
     */
    public TreeMap<String, String> getDictOfMenu() {
        return dictOfMenu;
    }
}

