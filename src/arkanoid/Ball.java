package arkanoid;

import java.awt.Color;

import biuoop.DrawSurface;
import core.Collidable;
import core.CollisionInfo;
import core.Sprite;
import core.Velocity;
import geometry.Line;
import geometry.Point;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private int radius;
    private Color colorOfBall;
    private Point centerBall;
    private Velocity velocity;
    private GameEnvironment gameEnvironmentOfBall;
    //the limits of the frame
    private double startPointFrameX;
    private double startPointFrameY;
    private double endPointFrameX;
    private double endPointFrameY;

    /**
     * The Default end of frame.
     */
    static final int DEFAULT_END_OF_FRAME = 400;
    /**
     * The Default start of frame.
     */
    static final int DEFAULT_START_OF_FRAME = 0;
    /**
     * The Color of frame ball.
     */
    static final Color COLOR_OF_FRAME_BALL = Color.BLACK;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center point of the ball
     * @param r      the radius
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.centerBall = center;
        this.radius = r;
        this.colorOfBall = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x from (x,y) of center
     * @param y     the y from (x,y) of center
     * @param r     the radius
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.centerBall.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.centerBall.getY();
    }


    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return 2 * (this.radius);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.colorOfBall;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), this.radius);
        surface.setColor(COLOR_OF_FRAME_BALL);
        surface.drawCircle(getX(), getY(), this.radius);
    }


    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        if (this.velocity != null) {
            Line trajectory = new Line(this.getCenterBall(), this.getVelocity().applyToPoint(this.getCenterBall()));
            if (this.gameEnvironmentOfBall.getClosestCollision(trajectory) != null) {
                CollisionInfo c = this.gameEnvironmentOfBall.getClosestCollision(trajectory);
                changeCentralBall(this.gameEnvironmentOfBall.getClosestCollision(trajectory));
                Collidable rect = this.gameEnvironmentOfBall.getClosestCollision(trajectory).collisionObject();
                this.setVelocity(rect.hit(this, c.collisionPoint(), this.getVelocity()));
            } else {
                this.changeVelocityAndCenterBallValues(this.checkIfBallStuckAxisX(), this.checkIfBallStuckAxisY());
            }
        }
    }

    /**
     * Change central ball.
     *
     * @param c the c
     */
    public void changeCentralBall(CollisionInfo c) {
        if (c.checkSideOfCollision() == "upperLeft") {
            this.centerBall = new Point(c.collisionPoint().getX() - getRadius(),
                    c.collisionPoint().getY() - getRadius());
        } else if (c.checkSideOfCollision() == "upperRight") {
            this.centerBall = new Point(c.collisionPoint().getX() + getRadius(),
                    c.collisionPoint().getY() - getRadius());
        } else if (c.checkSideOfCollision() == "DownLeft") {
            this.centerBall = new Point(c.collisionPoint().getX() - getRadius(),
                    c.collisionPoint().getY() + getRadius());
        } else if (c.checkSideOfCollision() == "downRight") {
            this.centerBall = new Point(c.collisionPoint().getX() + getRadius(),
                    c.collisionPoint().getY() + getRadius());
        } else if (c.checkSideOfCollision() == "right") {
            this.centerBall = new Point(c.collisionPoint().getX() + getRadius(),
                    c.collisionPoint().getY());
        } else if (c.checkSideOfCollision() == "left") {
            this.centerBall = new Point(c.collisionPoint().getX() - getRadius(),
                    c.collisionPoint().getY());
        } else if (c.checkSideOfCollision() == "down") {
            this.centerBall = new Point(c.collisionPoint().getX(),
                    c.collisionPoint().getY() + getRadius());
        } else if (c.checkSideOfCollision() == "up") {
            this.centerBall = new Point(c.collisionPoint().getX(),
                    c.collisionPoint().getY() - getRadius());
        }
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Check if ball stuck axis y boolean.
     *
     * @return the boolean
     */
    public boolean checkIfBallStuckAxisY() {
        boolean getStuckInAxisY = false;
        if (this.centerBall.getY() - this.radius + this.velocity.getHeadWayInAxisY() < this.startPointFrameY
            /*|| this.centerBall.getY() + this.radius + this.velocity.getHeadWayInAxisY() > this.endPointFrameY*/) {
            getStuckInAxisY = true;
            if (this.velocity.getHeadWayInAxisY() < 0) {
                // find the distance between the ball and the roof.
                double distance = this.centerBall.getY() - this.startPointFrameY - this.radius;

                // according the distance to the roof move the ball. and change direction of the speed
                this.centerBall = new Point(this.centerBall.getX(),
                        this.centerBall.getY() - this.velocity.getHeadWayInAxisY() - 2 * distance);
            } else {
                /// find the distance between the ball and the floor.
                double distance = this.endPointFrameY - this.centerBall.getY() - this.radius;
                // according the distance to the floor move the ball. and change direction of the speed
                this.centerBall = new Point(this.centerBall.getX(), this.centerBall.getY()
                        - this.velocity.getHeadWayInAxisY() + 2 * distance);
            }
        }
        return getStuckInAxisY;
    }

    /**
     * Check if ball stuck axis x boolean.
     *
     * @return the boolean
     */
    public boolean checkIfBallStuckAxisX() {
        boolean getStuckInAxisX = false;
        if (this.centerBall.getX() - this.radius + this.velocity.getHeadWayInAxisX() < this.startPointFrameX
                || this.centerBall.getX() + this.radius + this.velocity.getHeadWayInAxisX() > this.endPointFrameX) {
            getStuckInAxisX = true;
            if (this.velocity.getHeadWayInAxisX() < 0) {
                // find the distance between the ball and the left wall.
                double distance = this.centerBall.getX() - this.startPointFrameX - this.radius;
                // according the distance to the left wall move the ball. and change direction of the speed
                this.centerBall = new Point(this.centerBall.getX() - this.velocity.getHeadWayInAxisX()
                        - 2 * distance, this.centerBall.getY());
            } else {
                // find the distance between the ball and the right wall.
                double distance = this.endPointFrameX - this.centerBall.getX() - this.radius;
                // according the distance to the right wall move the ball. and change direction of the speed
                this.centerBall = new Point(this.centerBall.getX() - this.velocity.getHeadWayInAxisX()
                        + 2 * distance, this.centerBall.getY());
            }
        }
        return getStuckInAxisX;
    }

    /**
     * Change velocity and center ball values.
     *
     * @param axisX the axis x
     * @param axisY the axis y
     */
    public void changeVelocityAndCenterBallValues(boolean axisX, boolean axisY) {
        //if the ball doesn't stuck in axis x or y - We will change this position by velocity.
        if (!axisY && !axisX) {
            this.centerBall = this.velocity.applyToPoint(this.centerBall);
        } else if (!axisY) { //if the ball doesn't stuck in axis y -  change this position by getHeadWayInAxisX
            this.setVelocity(new Velocity(-this.velocity.getHeadWayInAxisX(), this.velocity.getHeadWayInAxisY()));
            this.centerBall = new Point(this.centerBall.getX(), this.centerBall.getY()
                    + this.velocity.getHeadWayInAxisY());
        } else if (!axisX) { //if the ball doesn't stuck in axis x -  change this position by getHeadWayInAxisY
            this.setVelocity(new Velocity(this.velocity.getHeadWayInAxisX(), -this.velocity.getHeadWayInAxisY()));
            this.centerBall = new Point(this.centerBall.getX() + this.velocity.getHeadWayInAxisX(),
                    this.centerBall.getY());
        } else { //if the ball stuck in both axis - change velocity in getHeadWayInAxisY and getHeadWayInAxisX
            this.setVelocity(new Velocity(-this.velocity.getHeadWayInAxisX(), -this.velocity.getHeadWayInAxisY()));
        }
    }

    /**
     * Sets frame gui.
     *
     * @param start the start
     * @param end   the end
     */
    public void setFrameGui(Point start, Point end) {
        if (end != null && start != null) {
            if (start.getX() < 0) {
                System.out.println("Error: x1 is smaller than 0");
                this.startPointFrameX = DEFAULT_START_OF_FRAME;
            }
            if (end.getX() < 0) {
                System.out.println("Error: y1 is smaller than 0");
                this.endPointFrameX = DEFAULT_END_OF_FRAME;
            }
            if (start.getY() < 0) {
                System.out.println("Error: x2 is smaller than 0");
                this.startPointFrameY = DEFAULT_START_OF_FRAME;
            }
            if (end.getY() < 0) {
                System.out.println("Error: y2 is smaller than 0");
                this.endPointFrameY = DEFAULT_END_OF_FRAME;
            }
            this.startPointFrameX = Math.min(start.getX(), end.getX());
            this.startPointFrameY = Math.min(start.getY(), end.getY());
            this.endPointFrameX = Math.max(start.getX(), end.getX());
            this.endPointFrameY = Math.max(start.getY(), end.getY());
        }
        if (start == null) {
            this.startPointFrameX = DEFAULT_START_OF_FRAME;
            this.startPointFrameY = DEFAULT_START_OF_FRAME;
        }
        if (end == null) {
            this.endPointFrameX = DEFAULT_END_OF_FRAME;
            this.endPointFrameY = DEFAULT_END_OF_FRAME;
        }
    }

    /**
     * Sets frame gui.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public void setFrameGui(double x1, double y1, double x2, double y2) {
        if (x1 < 0) {
            System.out.println("Error: x1 is smaller than 0");
            x1 = DEFAULT_START_OF_FRAME;
        }
        if (y1 < 0) {
            System.out.println("Error: y1 is smaller than 0");
            y1 = DEFAULT_START_OF_FRAME;
        }
        if (x2 < 0) {
            System.out.println("Error: x2 is smaller than 0");
            x2 = DEFAULT_END_OF_FRAME;
        }
        if (y2 < 0) {
            System.out.println("Error: y2 is smaller than 0");
            y2 = DEFAULT_END_OF_FRAME;
        }
        setFrameGui(new Point(Math.min(x1, x2), Math.min(y1, y2)), new Point(Math.max(x1, x2), Math.max(y1, y2)));
    }

    /**
     * Gets start point frame x.
     *
     * @return the start point frame x
     */
    public double getStartPointFrameX() {
        return this.startPointFrameX;
    }

    /**
     * Gets start point frame y.
     *
     * @return the start point frame y
     */
    public double getStartPointFrameY() {
        return this.startPointFrameY;
    }

    /**
     * Gets end point frame x.
     *
     * @return the end point frame x
     */
    public double getEndPointFrameX() {
        return this.endPointFrameX;
    }

    /**
     * Gets end point frame y.
     *
     * @return the end point frame y
     */
    public double getEndPointFrameY() {
        return this.endPointFrameY;
    }

    /**
     * Gets game environment of ball.
     *
     * @return the game environment of ball
     */
    public GameEnvironment getGameEnvironmentOfBall() {
        return gameEnvironmentOfBall;
    }

    /**
     * Sets game environment of ball.
     *
     * @param c the c
     */
    public void setGameEnvironmentOfBall(Collidable c) {
        this.gameEnvironmentOfBall.addCollidable(c);
    }

    /**
     * Gets center ball.
     *
     * @return the center ball
     */
    public Point getCenterBall() {
        return centerBall;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Gets color of ball.
     *
     * @return the color of ball
     */
    public Color getColorOfBall() {
        return colorOfBall;
    }

    /**
     * Add to the gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
//        Random rand = new Random();
//        double angle = (double) rand.nextInt(MAX_ANGLE_OF_INITIALIZATION_BALL) + 1;
//        Velocity v = Velocity.fromAngleAndSpeed(angle, SPEED_OF_BALL);
//        setVelocity(v);
        this.gameEnvironmentOfBall = gameLevel.getEnvironment();
        gameLevel.getSprites().addSprite(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        if (gameLevel == null) {
            return;
        }
        gameLevel.removeSprite(this);
    }
}


