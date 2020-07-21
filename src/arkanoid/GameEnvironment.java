package arkanoid;
import core.Collidable;
import core.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> rectanglesCanCollideWithBall = new ArrayList<Collidable>();

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.rectanglesCanCollideWithBall.add(c);
    }

    /**
     * Gets rectanglesCanCollideWithBall.
     *
     * @return the rectanglesCanCollideWithBall
     */
    public List<Collidable> getRectanglesCanCollideWithBall() {
        return rectanglesCanCollideWithBall;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.rectanglesCanCollideWithBall.remove(c);
    }


    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the rectanglesCanCollideWithBall
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;
        Point findDistancePoint, collisionPoint = null;
        Collidable collidableShape = null;
        double minDistance = trajectory.start().distance(trajectory.end());
        //playOneTurn on the list and find the closer collidable.
        for (Collidable c : this.rectanglesCanCollideWithBall) {
            r = c.getCollisionRectangle();
            //point of the intersection point of the current collidable.
            findDistancePoint = trajectory.closestIntersectionToStartOfLine(r);
            if (findDistancePoint != null) {
                double distance = trajectory.start().distance(findDistancePoint);
                //check if this the closer collidable.
                if (distance <= minDistance) {
                    //if yes, save the min distance, the index and collision point.
                    minDistance = trajectory.start().distance(findDistancePoint);
                    collidableShape = c;
                    collisionPoint = findDistancePoint;
                }
            }
        }
        //check if there is collision.
        if (collidableShape != null) {
            return new CollisionInfo(collidableShape, collisionPoint);
        } else {
            return null;
        }
    }
}
