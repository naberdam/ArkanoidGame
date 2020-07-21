
package core;

/**
 * Interface to do tasks from menu.
 *
 * @param <T> is the type of the mission.
 */
public interface Task<T> {
    /**
     * run this task.
     *
     * @return void.
     */
    T run();
}