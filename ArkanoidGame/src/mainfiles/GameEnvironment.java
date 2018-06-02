package mainfiles;
import geometryprimitives.Line;
import geometryprimitives.Point;

import java.util.List;
import java.util.ArrayList;

import collision.Collidable;
import collision.CollisionInfo;
/**
 * Class implements gameLevel all that is related to the gameLevel environment.
 * @author idonata 305727802
 *
 */
public class GameEnvironment {
    private List<Collidable> collection;
    private List<Collidable> collidedWith;
    private List<Point> closestPoints;
/**
 * Constructor of gameLevel environment.
 */
    public GameEnvironment() {
        this.collection = new ArrayList<Collidable>();
    }
    /**
     * function adds a collidable to the collection.
     * @param c - collidable desired to be added
     */
    public void addCollidable(Collidable c) {
        this.collection.add(c);
    }
    /**
     * function removes a collidable to the collection.
     * @param c - collidable desired to be added
     */
    public void removeCollidable(Collidable c) {
        this.collection.remove(c);
    }
    /**
     * Function calculates closest collision.
     * @param trajectory - the direction vector of the ball
     * @return null or information about the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        this.closestPoints = new ArrayList<Point>();
        this.collidedWith = new ArrayList<Collidable>();
        for (int j = 0; j < this.collection.size(); j++) {
            Point colPoint = trajectory
                    .closestIntersectionToStartOfLine(this.collection.get(j)
                            .getCollisionRectangle());
            if (colPoint != null) {
                this.closestPoints.add(colPoint);
                this.collidedWith.add(this.collection.get(j));
            }
        }
        if (this.closestPoints.isEmpty()) {
            return null;
        }
        int index = 0;
        Point min = trajectory.end();
        Point start = trajectory.start();
        for (int i = 0; i < closestPoints.size(); i++) {
            if (start.distance(closestPoints.get(i)) <= start.distance(min)) {
                min = closestPoints.get(i);
                index = i;
            }
        }
        return new CollisionInfo(min, this.collidedWith.get(index));
    }
}
