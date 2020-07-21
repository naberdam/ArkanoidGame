package levels;

import java.awt.Color;

/**
 * The type All levels.
 */
abstract class AllLevels implements LevelInformation {
    private int widthOfScreen;
    private int heightOfScreen;
    private Color[] allColors = {Color.RED, Color.BLUE, Color.GREEN, Color.pink, Color.YELLOW,
            Color.DARK_GRAY, Color.LIGHT_GRAY};

    /**
     * Instantiates a new All levels.
     *
     * @param widthOfScreen  the width of screen
     * @param heightOfScreen the height of screen
     */
    AllLevels(int widthOfScreen, int heightOfScreen) {
        this.widthOfScreen = widthOfScreen;
        this.heightOfScreen = heightOfScreen;
    }

    /**
     * Gets width of screen.
     *
     * @return the width of screen
     */
    int getWidthOfScreen() {
        return this.widthOfScreen;
    }

    /**
     * Gets height of screen.
     *
     * @return the height of screen
     */
    int getHeightOfScreen() {
        return this.heightOfScreen;
    }

    /**
     * Get all colors color [ ].
     *
     * @return the color [ ]
     */
    Color[] getAllColors() {
        return allColors;
    }

}
