package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Menu;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private List<String> key;
    private AnimationRunner ar;
    private List<String> lineToPrint;
    private List<T> status;
    private T curStatus;
    private KeyboardSensor keyboardSensor;
    private List<Menu<T>> subMenu;
    private List<Boolean> isSubMenu;

    /**
     * Constructor.
     *
     * @param titlein         the title of dialog.
     * @param ks              the sensor.
     * @param animationRunner the animation runner
     */
    public MenuAnimation(String titlein, KeyboardSensor ks, AnimationRunner animationRunner) {
        this.title = titlein;
        this.ar = animationRunner;
        this.key = new ArrayList<>();
        this.lineToPrint = new ArrayList<>();
        this.status = new ArrayList<>();
        this.keyboardSensor = ks;
        this.curStatus = null;
        this.subMenu = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
    }

    /**
     * Add selection.
     *
     * @param keyin     the keyin
     * @param message   the message
     * @param returnVal the return val
     */
    @Override
    public void addSelection(String keyin, String message, T returnVal) {
        this.key.add(keyin);
        this.lineToPrint.add(message);
        this.status.add(returnVal);
        this.isSubMenu.add(false);
        this.subMenu.add(null);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    @Override
    public T getStatus() {
        return curStatus;
    }

    /**
     * do one play in the animation.
     *
     * @param d is the drawsurface to make the play on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        int i;
        boolean isGame = false;
        for (i = 0; i < getKey().size(); i++) {
            if (getKeyboardSensor().isPressed(getKey().get(i))) {
                if (!getIsSubMenu().get(i)) {
                    this.curStatus = status.get(i);
                } else {
                    getAr().run(getSubMenu().get(i));
                    this.curStatus = getSubMenu().get(i).getStatus();
                    isGame = true;
                }
                break;
            }
        }
        if (isGame) {
            return;
        }
        drawOn(d);
    }

    /**
     * draw this level.
     *
     * @param d the drawsurface.
     */
    private void drawOn(DrawSurface d) {
        Image image = null;
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "images_of_menu/menuPicture.jpg");
            if (inputStream == null) {
                System.out.println("input stream is null:" + "+*+" + ClassLoader.getSystemClassLoader());
                return;
            }
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("images_of_menu/menuPicture.jpg");
            System.out.println("Error8!!:" + e.getMessage() + "  " + "images_of_menu/menuPicture.jpg" + " "
                    + e.getLocalizedMessage());
        }
        d.drawImage(0, 0, image);
        d.setColor(Color.white);
        d.drawText(d.getWidth() / 2 - 80, 220, getTitle(), 40);
        for (int i = 0; i < getKey().size(); i++) {
            d.drawText(230, 270 + (i * 50),
                    "press \"" + getKey().get(i) + "\" for " + getLineToPrint().get(i), 25);
        }
        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    /**
     * check if the play should pause.
     *
     * @return true is has to stop.
     */
    @Override
    public boolean shouldStop() {
        return this.curStatus != null;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public List<String> getKey() {
        return key;
    }

    /**
     * reset this curStatus in order to rechoose menu.
     */
    public void reset() {
        this.curStatus = null;
    }

    /**
     * Add sub menu.
     *
     * @param keyin     the key
     * @param message   the message
     * @param subMenuin the sub menu
     */
    @Override
    public void addSubMenu(String keyin, String message, Menu<T> subMenuin) {
        this.isSubMenu.add(true);
        this.subMenu.add(subMenuin);
        this.key.add(keyin);
        this.lineToPrint.add(message);
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
     * Gets ar.
     *
     * @return the ar
     */
    public AnimationRunner getAr() {
        return ar;
    }

    /**
     * Gets line to print.
     *
     * @return the line to print
     */
    public List<String> getLineToPrint() {
        return lineToPrint;
    }

    /**
     * Gets cur status.
     *
     * @return the cur status
     */
    public T getCurStatus() {
        return curStatus;
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
     * Gets sub menu.
     *
     * @return the sub menu
     */
    public List<Menu<T>> getSubMenu() {
        return subMenu;
    }

    /**
     * Gets is sub menu.
     *
     * @return the is sub menu
     */
    public List<Boolean> getIsSubMenu() {
        return isSubMenu;
    }
}

