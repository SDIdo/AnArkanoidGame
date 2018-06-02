package collision;
import geometryprimitives.Point;
/**
 * This class is responsible for the implementation of holding collision info.
 * @author idonata
 *
 */
public class CollisionInfo {
    /*
     * MEMBERS
     */
    private Point collisionPoint;
    private Collidable object;
/**
 * Constructor of collisionInfo.
 * @param collisionPoint - the collision point
 * @param object - the colliding object
 */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = collisionPoint;
        this.object = object;
    }
    /**
     * getter of collision point.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Getter of colliding object.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
 }