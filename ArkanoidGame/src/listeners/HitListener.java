package listeners;
import mainobjects.Ball;
import mainobjects.Block;
/**
 * Rules what every hit listener must do.
 * @author idonata 305727802
 *
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that was hit.
     * @param hitter - the colliding ball.
     */
   void hitEvent(Block beingHit, Ball hitter);
}
