package listeners;
import levels.GameLevel;
import mainobjects.Ball;
import mainobjects.Block;
import indicators.Counter;

/**
 * An implementation of a HitListener - removes a ball upon hit.
 *
 * @author idonata 305727802
 *
 */
public class BallRemover implements HitListener {
    /*
     * MEMBERS
     */
    private GameLevel gameLevel;
    private Counter remainedBalls;
    /**
     * Constructor of BallRemover.
     * @param g - the game.
     * @param remainingBalls - num of still hanging blocks.
     */
    public BallRemover(GameLevel g, Counter remainingBalls) {
        this.gameLevel = g;
        this.remainedBalls = remainingBalls;
    }
    /**
     * Function occurs whenever a hit took place with this HitListener block.
     * @param beingHit - the block that took the hit.
     * @param hitter - the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainedBalls.decrease(1);
    }
}
