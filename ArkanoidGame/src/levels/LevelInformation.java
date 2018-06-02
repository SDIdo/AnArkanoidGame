package levels;
import java.util.List;

import mainobjects.Block;
import collision.Velocity;
import backgrounds.Sprite;
/**
 * Rules what every level information object must do.
 * @author idonata 30572780
 *
 */
public interface LevelInformation {
    /**
     * answers how many balls in this level.
     * @return - the number of balls.
     */
   int numberOfBalls();
   /**
    * The initial velocity of each ball.
    * @return - a list of velocities.
    */
   List<Velocity> initialBallVelocities();
   /**
    * answers what is the speed of the paddle.
    * @return - the speed.
    */
   int paddleSpeed();
   /**
    * answers what is the width of the paddle.
    * @return - the width.
    */
   int paddleWidth();
   /**
    * answers what is the name of the level.
    * @return - the name.
    */
   String levelName();
   /**
    * getter of level's background.
    * @return sprite with the background of the level
    */
   Sprite getBackground();
   /**
    * The Blocks that make up this level, each block contains.
    * its size, color and location.
    * @return - the list of blocks.
    */
   List<Block> blocks();
   /**
    * Number of blocks that should be removed.
    * before the level is considered to be "cleared".
    * @return the number of breakables.
    */
   int numberOfBlocksToRemove();
}
