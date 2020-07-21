package arkanoid;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Velocity velocity;
    private double leftLimit;
    private double rightLimit;
    private Point upperLeftInBeginning;
    /**
     * The Color of paddle.
     */
    static final Color COLOR_OF_PADDLE = Color.BLUE;
    /**
     * The Speed of paddle.
     */
    static final double SPEED_OF_PADDLE = 10;
    /**
     * The Angle of paddle.
     */
    static final double ANGLE_OF_PADDLE = 90;
    /**
     * The Angle of region one.
     */
    static final double ANGLE_OF_REGION_ONE = 300;
    /**
     * The Angle of region two.
     */
    static final double ANGLE_OF_REGION_TWO = 330;
    /**
     * The Angle of region four.
     */
    static final double ANGLE_OF_REGION_FOUR = 30;
    /**
     * The Angle of region five.
     */
    static final double ANGLE_OF_REGION_FIVE = 60;

    /**
     * Instantiates a new Paddle.
     *
     * @param rect  the rect
     * @param gui   the gui
     * @param left  the left
     * @param right the right
     */
    public Paddle(Rectangle rect, GUI gui, double left, double right) {
        this.paddle = rect;
        this.keyboard = gui.getKeyboardSensor();
        this.velocity = Velocity.fromAngleAndSpeed(ANGLE_OF_PADDLE, SPEED_OF_PADDLE);
        this.leftLimit = left;
        this.rightLimit = right;
        this.upperLeftInBeginning = rect.getUpperLeft();
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param rect  the rect
     * @param gui   the gui
     * @param left  the left
     * @param right the right
     * @param speed the speed
     */
    public Paddle(Rectangle rect, GUI gui, double left, double right, double speed) {
        this.paddle = rect;
        this.keyboard = gui.getKeyboardSensor();
        this.velocity = Velocity.fromAngleAndSpeed(ANGLE_OF_PADDLE, SPEED_OF_PADDLE);
        this.leftLimit = left;
        this.rightLimit = right;
        this.upperLeftInBeginning = rect.getUpperLeft();
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (getCollisionRectangle().getUpperLeft().getX() - getVelocity().getHeadWayInAxisX() < this.leftLimit) {
            Point newUpperLeft = new Point(this.leftLimit, getCollisionRectangle().getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, getCollisionRectangle().getWidthOfRec(),
                    getCollisionRectangle().getHeightOfRec());
        } else {
            Point newUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX()
                    - getVelocity().getHeadWayInAxisX(), getCollisionRectangle().getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, getCollisionRectangle().getWidthOfRec(),
                    getCollisionRectangle().getHeightOfRec());
        }

    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperRight().getX() + getVelocity().getHeadWayInAxisX() > this.rightLimit) {
            Point newUpperLeft = new Point(this.rightLimit - getCollisionRectangle().getWidthOfRec(),
                    getCollisionRectangle().getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, getCollisionRectangle().getWidthOfRec(),
                    getCollisionRectangle().getHeightOfRec());
        } else {
            Point newUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX()
                    + getVelocity().getHeadWayInAxisX(), getCollisionRectangle().getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, getCollisionRectangle().getWidthOfRec(),
                    getCollisionRectangle().getHeightOfRec());
        }
    }

    /**
     * Time passed.
     */
// Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(COLOR_OF_PADDLE);
        surface.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidthOfRec(),
                (int) getCollisionRectangle().getHeightOfRec());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidthOfRec(),
                (int) getCollisionRectangle().getHeightOfRec());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the collision point is on the right or left of the paddle
        if ((collisionPoint.getX() <= getCollisionRectangle().getUpperLeft().getX())
                || (collisionPoint.getX() >= getCollisionRectangle().getUpperRight().getX())) {
            if (collisionPoint.getY() <= getCollisionRectangle().getUpperLeft().getY()) {
                return new Velocity(-currentVelocity.getHeadWayInAxisX(), -currentVelocity.getHeadWayInAxisY());
            } else {
                return new Velocity(-currentVelocity.getHeadWayInAxisX(), currentVelocity.getHeadWayInAxisY());
            }
        }
        if (collisionPoint.getY() <= getCollisionRectangle().getUpperLeft().getY()) {
            double sizeOfRegion = getCollisionRectangle().getWidthOfRec() / 5;
            if ((getCollisionRectangle().getUpperLeft().getX() + sizeOfRegion) - collisionPoint.getX() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(ANGLE_OF_REGION_ONE, currentVelocity.getSpeed());
                return currentVelocity;
            }
            if ((getCollisionRectangle().getUpperLeft().getX() + (2 * sizeOfRegion)) - collisionPoint.getX() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(ANGLE_OF_REGION_TWO, currentVelocity.getSpeed());
                return currentVelocity;
            }
            if ((getCollisionRectangle().getUpperLeft().getX() + (3 * sizeOfRegion)) - collisionPoint.getX() > 0) {
                currentVelocity = new Velocity(currentVelocity.getHeadWayInAxisX(),
                        -currentVelocity.getHeadWayInAxisY());
                return currentVelocity;
            }
            if ((getCollisionRectangle().getUpperLeft().getX() + (4 * sizeOfRegion)) - collisionPoint.getX() > 0) {
                currentVelocity = Velocity.fromAngleAndSpeed(ANGLE_OF_REGION_FOUR, currentVelocity.getSpeed());
                return currentVelocity;
            }
            return Velocity.fromAngleAndSpeed(ANGLE_OF_REGION_FIVE, currentVelocity.getSpeed());
        } else {
            return new Velocity(currentVelocity.getHeadWayInAxisX(), -currentVelocity.getHeadWayInAxisY());
        }

    }

    /**
     * Add to gameLevel.
     *
     * @param gameLevel the gameLevel
     */
// Add this paddle to the gameLevel.
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Sets paddles.
     *
     * @param paddles the paddles
     */
    public void setPaddle(Rectangle paddles) {
        this.paddle = paddles;
    }

    /**
     * Take paddle to center.
     */
    public void takePaddleToCenter() {
        double widthOfPaddle = getCollisionRectangle().getWidthOfRec();
        double heightOfPaddle = getCollisionRectangle().getHeightOfRec();
        getCollisionRectangle().getUpperLeft().setX(getUpperLeftInBeginning().getX());
        getCollisionRectangle().updateRectangle(widthOfPaddle, heightOfPaddle);
    }

    /**
     * Gets left limit.
     *
     * @return the left limit
     */
    public double getLeftLimit() {
        return leftLimit;
    }

    /**
     * Gets right limit.
     *
     * @return the right limit
     */
    public double getRightLimit() {
        return rightLimit;
    }

    /**
     * Gets upper left in beginnig.
     *
     * @return the upper left in beginnig
     */
    private Point getUpperLeftInBeginning() {
        return upperLeftInBeginning;
    }
}
