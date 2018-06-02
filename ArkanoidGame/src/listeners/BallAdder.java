package listeners;
import indicators.Counter;

import java.awt.Color;

import levels.GameLevel;
import mainobjects.Ball;
import mainobjects.Block;

/**
 * An implementation of a HitListener - adds a ball upon destruction.
 *
 * @author idonata 305727802
 *
 */
public class BallAdder implements HitListener {
    /*
     * MEMBERS
     */
    private GameLevel gameLevel;
    private Counter remainedBalls;
    /**
     * Constuctor of BallAdder.
     * @param g - the game.
     * @param remainingBalls - num of still bouncing balls.
     */
    public BallAdder(GameLevel g, Counter remainingBalls) {
        this.gameLevel = g;
        this.remainedBalls = remainingBalls;
    }
    /**
     * Function occurs whenever a hit took place with this HitListener block.
     * @param beingHit - the block that took the hit.
     * @param hitter - the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            new Ball(beingHit.getXCenter(), beingHit.getYCenter(),
                    GameLevel.BALL_SIZE, new Color(142, 52, 175),
                    gameLevel.getGameEnvironment()).addToGame(gameLevel);
            remainedBalls.increase(1);
        }
    }
}
