package animation;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    private String key;

    /**
     * Constructor.
     *
     * @param highScoresTable the highScoresTable
     * @param key             is the key to pause the screen.
     */
    public HighScoresAnimation(HighScoresTable highScoresTable, String key) {
        this.highScoresTable = highScoresTable;
        this.key = key;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.drawOn(d);
    }

    /**
     * check if the play should pause.
     *
     * @return true is has to stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * draw BG or something.
     *
     * @param d is the drawsurface.
     */
    private void drawOn(DrawSurface d) {
        Image image = null;
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "images_of_menu/highScorePicture.jpg");
            if (inputStream == null) {
                System.out.println("input stream is null:" + "+*+" + ClassLoader.getSystemClassLoader());
                return;
            }
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("images_of_menu/highScorePicture.jpg");
            System.out.println("Error8!!:" + e.getMessage() + "  " + "images_of_menu/highScorePicture.jpg" + " "
                    + e.getLocalizedMessage());
        }
        d.drawImage(0, 0, image);
        d.setColor(Color.BLUE);
        d.drawText(250, 170, "High Score Table!", 40);
        d.drawText(250, 480, "press " + getKey() + " to continue", 30);
        if (getHighScoresTable().sizeOfList() == 0) {
            d.setColor(Color.RED);
            d.drawText(250, 300, "The table is empty for now.", 30);
        } else {
            for (int i = 0; i < getHighScoresTable().sizeOfList(); i++) {
                if (getHighScoresTable().getHighScores().get(i).getName().equals("")) {
                    continue;
                }
                d.setColor(Color.white);
                d.drawText(145, 205 + (40 * i), (i + 1) + ") "
                        + getHighScoresTable().getHighScores().get(i).toString(), 20);
            }
        }
        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    /**
     * Gets highScoresTable.
     *
     * @return the high score table.
     */
    public HighScoresTable getHighScoresTable() {
        return highScoresTable;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }
}
