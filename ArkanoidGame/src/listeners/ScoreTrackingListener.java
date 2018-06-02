package listeners;
import mainobjects.Ball;
import mainobjects.Block;
import indicators.Counter;
/**
 * Each block that is breakable has this listener.
 * Upon hit player's score goes up.
 * @author idonata 305727802
 *
 */
public class ScoreTrackingListener implements HitListener {
    /*
     * MAGIC NUMBERS
     */
    public static final int WAS_HIT = 5;
    public static final int WAS_BROKEN = 10;
    /*
     * MEMBERS
     */
   private Counter currentScore;
   /**
    * ScoreTrackingListener constructor.
    * @param scoreCounter - a simple counter to accumulate the score in.
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }
   /**
    * upon regular hit +5 points, upon destruction +10.
    * @param beingHit - the block that was hit.
    * @param hitter - the hitting ball.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() >= 1) {
           currentScore.increase(WAS_HIT);
       }
       if (beingHit.getHitPoints() == 0) {
           currentScore.increase(WAS_BROKEN);
           beingHit.removeHitListener(this);
       }
   }
}
