import animation.AnimationRunner;
import animation.BeforeGameBegins;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.TreeMap;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {

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
     * The Fps.
     */
    private static final int FPS = 60;
    /**
     * The Title of menu.
     */
    private static final String TITLE_OF_MENU = "My Menu";
    /**
     * The Title of gui.
     */
    private static final String TITLE_OF_GUI = "Arkanoid Nerya Aberdam";
    /**
     * The File level name.
     */
    private static final String FILE_LEVEL_NAME = "resources/level_sets.txt";
    /**
     * The Play key.
     */
    private static final String PLAY_KEY = "s";
    /**
     * The Start high.
     */
    private static final String START_HIGH = "h";
    /**
     * The Exit key.
     */
    private static final String EXIT_KEY = "q";
    /**
     * The Game over key.
     */
    private static final String GAME_OVER_KEY = KeyboardSensor.SPACE_KEY;
    /**
     * The Play msg.
     */
    private static final String PLAY_MSG = "play Arkanoid!";
    /**
     * The Exit msg.
     */
    private static final String EXIT_MSG = "Exit from the game";
    /**
     * The Hst msg.
     */
    private static final String HST_MSG = "High Score Table";


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        TreeMap<String, String> dictOfMenu = new TreeMap<>();
        dictOfMenu.put("playMsg", PLAY_MSG);
        dictOfMenu.put("exitMsg", EXIT_MSG);
        dictOfMenu.put("hstMsg", HST_MSG);
        dictOfMenu.put("titleOfMenu", TITLE_OF_MENU);
        TreeMap<String, String> dictOfKey = new TreeMap<>();
        dictOfKey.put("highKey", HIGH_KEY);
        dictOfKey.put("playKey", PLAY_KEY);
        dictOfKey.put("startHigh", START_HIGH);
        dictOfKey.put("exitKey", EXIT_KEY);
        dictOfKey.put("gameOverKey", GAME_OVER_KEY);
        GUI gui = new GUI(TITLE_OF_GUI, SCREEN_WIDTH, SCREEN_HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, FPS);

        String fileLevelSet;
        if (args.length != 0) {
            fileLevelSet = args[0];
        } else {
            fileLevelSet = FILE_LEVEL_NAME;
        }
        BeforeGameBegins beforeGameBegins = new BeforeGameBegins(ar, dictOfMenu, dictOfKey, fileLevelSet);
        ar.run(beforeGameBegins);

        //close the window after gaming.
        ar.getGui().close();
    }
}
