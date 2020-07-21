package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean alreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        stop = false;
        alreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getAnimation().doOneFrame(d);
        if (getSensor().isPressed(getKey())) {
            if (!isAlreadyPressed()) {
                this.stop = true;
            }
        } else {
            setAlreadyPressed(false);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Gets sensor.
     *
     * @return the sensor
     */
    public KeyboardSensor getSensor() {
        return sensor;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets animation.
     *
     * @return the animation
     */
    public Animation getAnimation() {
        return animation;
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
     * Is already pressed boolean.
     *
     * @return the boolean
     */
    public boolean isAlreadyPressed() {
        return alreadyPressed;
    }

    /**
     * Sets already pressed.
     *
     * @param alreadyPressedd the already pressed
     */
    public void setAlreadyPressed(boolean alreadyPressedd) {
        this.alreadyPressed = alreadyPressedd;
    }
}