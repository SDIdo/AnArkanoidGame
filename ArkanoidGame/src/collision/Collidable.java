package collision;
import mainobjects.Ball;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
/**
 * This interface rules what a collidable must do.
 * @author idonata
 *
 */
public interface Collidable {
    /**
     * Getter of collisionRectangle.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
       a given velocity.
     * @param hitter - the ball that collided with this collidable.
     * @param collisionPoint - the point at which the collision occured.
     * @param currentVelocity - the velocity upon hit.
     * @return - a new velocity for the collider.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
 }
