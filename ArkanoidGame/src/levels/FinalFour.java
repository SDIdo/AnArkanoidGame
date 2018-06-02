package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mainobjects.Block;
import collision.Velocity;
import backgrounds.FinalFourBackground;
import backgrounds.Sprite;
/**
 * FinalFour level information: balls, blocks and more..
 * @author idonata 305727802
 *
 */
public class FinalFour implements LevelInformation {
    /*
     * MAGIC NUMBERS
     */
    public static final String LEVEL_NAME = "Final Four";
    public static final int SUPER_BREAKABLES = 2;
    public static final int TOTAL_BALLS = 3;
    public static final int PADDLE_SPEED = 15;
    public static final int PADDLE_WIDTH = 120;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BALL_SIZE = 5;
    public static final int BALL_Y_MOVE = -10;
    public static final int MOST_LINE_BLOCKS = 15;
    public static final int TOTAL_LINES = 7;
    public static final int CONST = 5;

    /*
     * MEMBERS
     */
    private Color[] colors = {Color.GRAY.brighter(), Color.RED, Color.YELLOW, Color.GREEN,
            Color.WHITE, Color.pink, Color.CYAN};
    private List<Block> pBlocks = new ArrayList<Block>();
    private boolean shouldCreate = true;
    private int fcnt = 0; // border and layout blocks counter
    private int width;
    private int height;
    /**
     * Constructor of FinalFourBackground.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public FinalFour(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
    }
    /**
     * Getter of level's balls.
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return TOTAL_BALLS;
    }

    /**
     * setter of balls velocities for this level.
     * @return velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vel = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            if (i % 2 == 0) {
            vel.add(new Velocity(-i * CONST, BALL_Y_MOVE)); // Left
            } else {
                vel.add(new Velocity((i + 1) * CONST, BALL_Y_MOVE)); // Right
            }
        }
        return vel;
    }
    /**
     * getter of this level's paddle speed.
     * @return the speed.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * getter of this level's paddle width.
     * @return the width.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * getter of this level's name.
     * @return the name.
     */
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * getter of this level's background.
     * @return the background.
     */
    public Sprite getBackground() {
        return new FinalFourBackground(width, height);
    }
    /**
     * getter of this level's block ordering.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        if (this.shouldCreate) {
            this.stageDesign();
            this.frameCreation();
            this.shouldCreate = false;
        }
        return this.pBlocks;
    }

    /**
     * getter of number of blocks to remove.
     * @return the number of breakable blocks.
     */
    public int numberOfBlocksToRemove() {
        return this.pBlocks.size() - this.fcnt;
    }
    /**
     * function sets the pattern of the breakables.
     */
    public void stageDesign() {
      int space = 0;
      int line = 0;
      while (line < TOTAL_LINES) {
          for (int i = 0; i < MOST_LINE_BLOCKS; i++) {
              Block block = new Block(
                      (width - GameLevel.BORDER_SIZE - BLOCK_WIDTH) - space,
                      GameLevel.UPPER_SPACE + BLOCK_HEIGHT * line, BLOCK_WIDTH,
                      BLOCK_HEIGHT, this.getBlockColors(line));
              block.setHitPoints(2);
            if (line >= SUPER_BREAKABLES) {
                block.setHitPoints(1);
        }
              this.pBlocks.add(block);
              space += BLOCK_WIDTH;
          }
          space = 0;
          line += 1;
      }
  }
    /**
     * getter of colors collection.
     * @param index - choose a color from the list.
     * @return a color.
     */
    private Color getBlockColors(int index) {
        return this.colors[index];
    }
    /**
     * function sets the borders and layout.
     */
    public void frameCreation() {
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN + GameLevel.BORDER_SIZE,
                width, GameLevel.BORDER_SIZE, // upperBorder
                Color.GRAY.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE, GameLevel.ORIGIN,
                GameLevel.BORDER_SIZE, height, Color.GRAY.darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN, GameLevel.BORDER_SIZE,
                height, Color.GRAY.darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, height, width,
                GameLevel.BORDER_SIZE)); // DeathBlock
        this.fcnt++;
        // Corner Checkers.
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN, GameLevel.BORDER_SIZE,
                GameLevel.BORDER_SIZE, Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, height - GameLevel.BORDER_SIZE,
                GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE, GameLevel.ORIGIN,
                GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE, height
                - GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN, width,
                GameLevel.BORDER_SIZE, // Information
                Color.WHITE));
        this.fcnt++;
    }

}

