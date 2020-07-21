package core;

import animation.Animation;

/**
 * Menu in this game.
 *
 * @param <T> the type of menu.
 */
public interface Menu<T> extends Animation {
    /**
     * add selection to the menu.
     *
     * @param key       to press.
     * @param message   to show.
     * @param returnVal after press.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return what is the cur pressing mission.
     */
    T getStatus();

    /**
     * reset the choosen.
     */
    void reset();

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
