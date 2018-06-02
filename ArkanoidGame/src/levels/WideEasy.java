package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import mainfiles.Ass5Game;
import mainobjects.Block;
import collision.Velocity;
import backgrounds.Sprite;
import backgrounds.WideEasyBackground;
/**
 * Wide Easy, level 2 information: blocks setting, paddle properties and more..
 * @author idonata 305727802
 *
 */
public class WideEasy implements LevelInformation {
    /*
     * MAGIC NUMBERS
     */
    public static final String LEVEL_NAME = "Wide Easy";
    public static final int TOTAL_BALLS = 10;
    public static final int PADDLE_SPEED = 1;
    public static final int PADDLE_WIDTH = 675;
    public static final int BORDER_SIZE = 25;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BALL_SIZE = 5;
    public static final int BALL_Y_MOVE = -10;
    public static final int CONST = 3;
    public static final int MOST_LINE_BLOCKS = 15;
    public static final int TOTAL_LINES = 1;
    /*
     * MEMBERS
     */
    private Color[] colors = {Color.RED.brighter(), Color.RED.brighter(),
            Color.ORANGE, Color.ORANGE,
            Color.YELLOW, Color.YELLOW,
            Color.GREEN.brighter(), Color.GREEN.brighter(),
            Color.GREEN.brighter(), Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};

    private List<Block> pBlocks = new ArrayList<Block>();
    private boolean shouldCreate = true;
    private int fcnt = 0; //counter of frames and layout blocks
    private int width;
    private int height;
    /**
     * Constructor of WideEasy level.
     * @param screenWidth - screen's width.
     * @param screenHeight - screen's height.
     */
    public WideEasy(int screenWidth, int screenHeight) {
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
            vel.add(new Velocity(-i - CONST, BALL_Y_MOVE)); // Left
            } else {
                vel.add(new Velocity(i + CONST, BALL_Y_MOVE)); // Right
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
        return new WideEasyBackground(width, height);
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
        for (int i = 0; i < MOST_LINE_BLOCKS; i++) {
            Block block = new Block(BORDER_SIZE + BLOCK_WIDTH * i,
                    (Ass5Game.SCREEN_HEIGHT * 3 / 8), BLOCK_WIDTH, BLOCK_HEIGHT, this
                    .getBlockColors(i));
            block.setHitPoints(2);
            this.pBlocks.add(block);
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
