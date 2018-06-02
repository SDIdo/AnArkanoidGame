package listeners;
import levels.GameLevel;
import mainobjects.Ball;
import mainobjects.Block;
import indicators.Counter;
/**
 * a BlockRemover is in charge of removing blocks from the gameLevel,
 * as well as keeping count of the number of blocks that remain.
 *
 * @author idonata 305727802
 *
 */
public class BlockRemover implements HitListener {
    /*
     * MAGIC NUMBERS
     */
    public static final int LEVEL_CLEARED = 100;
    /*
     * MEMBERS
     */
   private GameLevel gameLevel;
   private Counter remainingBlocks;
   /**
    * BlockRemover Constructor.
    * @param gameLevel - a desired game level properties.
    * @param removedBlocks - blocks left to be removed.
    */
   public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
       this.gameLevel = gameLevel;
       this.remainingBlocks = removedBlocks;
   }

   /**
    * Blocks that are hit and reach 0 hit-points are removed
    * from the gameLevel. Removing all the blocks grants an addition of 100 points.
    * @param beingHit - the block that was hit.
    * @param hitter - the ball which collided with this block.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() <= 0 && this.remainingBlocks.getValue() <= 1) {
           gameLevel.getScore().increase(LEVEL_CLEARED);
       }
       if (beingHit.getHitPoints() <= 0) {
           this.remainingBlocks.decrease(1);
           beingHit.removeHitListener(this);
           beingHit.removeFromGame(gameLevel);
       }
   }
}
