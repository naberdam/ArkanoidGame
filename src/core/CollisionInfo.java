package core;

import geometry.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPointWithRectangle;
    private Collidable shapeOfeCollision;

    /**
     * Instantiates a new Collision info.
     *
     * @param collidable     the collidable
     * @param pointCollision the point collision
     */
    public CollisionInfo(Collidable collidable, Point pointCollision) {
        this.collisionPointWithRectangle = pointCollision;
        this.shapeOfeCollision = collidable;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPointWithRectangle;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.shapeOfeCollision;
    }

    /**
     * Check side of collision char.
     *
     * @return the char
     */
    public String checkSideOfCollision() {
        if ((collisionPoint().getX() == collisionObject().getCollisionRectangle().getUpperRight().getX())
                && collisionPoint().getY() == collisionObject().getCollisionRectangle().getUpperRight().getY()) {
            return "upperRight";
        }
        if ((collisionPoint().getX() == collisionObject().getCollisionRectangle().getUpperLeft().getX())
                && collisionPoint().getY() == collisionObject().getCollisionRectangle().getUpperLeft().getY()) {
            return "UpperLeft";
        }
        if ((collisionPoint().getX() == collisionObject().getCollisionRectangle().getDownRight().getX())
                && collisionPoint().getY() == collisionObject().getCollisionRectangle().getDownRight().getY()) {
            return "DownRight";
        }
        if ((collisionPoint().getX() == collisionObject().getCollisionRectangle().getDownLeft().getX())
                && collisionPoint().getY() == collisionObject().getCollisionRectangle().getDownLeft().getY()) {
            return "DownLeft";
        }
        if (collisionPoint().getX() == collisionObject().getCollisionRectangle().getUpperRight().getX()) {
            return "right";
        }
        if (collisionPoint().getX() == collisionObject().getCollisionRectangle().getUpperLeft().getX()) {
            return "left";
        }
        if (collisionPoint().getY() == collisionObject().getCollisionRectangle().getUpperRight().getY()) {
            return "up";
        }
        return "down";
    }
}
