package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mainobjects.Block;
import collision.Velocity;
import backgrounds.DirectHitBackground;
import backgrounds.Sprite;

/**
 * Class holds information regarding this level.
 *
 * @author idonata 305727802
 *
 */
public class DirectHit implements LevelInformation {
    /*
     * MAGIC NUMBERS
     */
    public static final String LEVEL_NAME = "Direct Hit";
    public static final int TOTAL_BALLS = 1;
    public static final int PADDLE_SPEED = 15;
    public static final int PADDLE_WIDTH = 120;
    public static final int BALL_Y_MOVE = -10;
    public static final int UPPER_SPACE = 150;
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int BALL_SIZE = 5;

    /*
     * MEMBERS
     */
    private Color[] colors = {Color.CYAN.brighter(), Color.RED.brighter(),
            Color.YELLOW.brighter(), Color.WHITE.brighter(),
            Color.BLUE.brighter(), Color.GREEN.brighter() };

    private List<Block> pBlocks = new ArrayList<Block>();
    private boolean shouldCreate = true;
    private int fcnt = 0; //border and layout blocks counter
    private int width;
    private int height;
    /**
     * Constructor of DirectHit.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public DirectHit(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
    }
    /**
     * getter of this level's balls.
     * @return the number of balls for this level.
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
                vel.add(new Velocity(-i * 2, BALL_Y_MOVE)); // Left
            } else {
                vel.add(new Velocity(i * 2, BALL_Y_MOVE)); // Right
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
        return new DirectHitBackground(width, height);
    }
    /**
     *
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
        Random rand = new Random();
        this.pBlocks.add(new Block((width / 2 - BLOCK_WIDTH / 2),
                (height * 2 / 7), BLOCK_WIDTH, BLOCK_HEIGHT, this
                        .getBlockColors(rand.nextInt(this.colors.length))));
    }
    /**
     * getter of blocks colors.
     * @param line - the line of blocks desired.
     * @return - a color.
     */
    private Color getBlockColors(int line) {
        return this.colors[line];
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
