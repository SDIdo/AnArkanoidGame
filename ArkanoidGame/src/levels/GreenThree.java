package levels;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import mainobjects.Block;
import collision.Velocity;
import backgrounds.GreenThreeBackground;
import backgrounds.Sprite;



/**
 * Green 3 level information: blocks layout, balls and more..
 *
 * @author idonata 305727802
 *
 */
public class GreenThree implements LevelInformation {
    /*
     * MAGIC NUMBERS
     */
    public static final String LEVEL_NAME = "Green 3";
    public static final int TOTAL_BALLS = 2;
    public static final int PADDLE_SPEED = 15;
    public static final int PADDLE_WIDTH = 120;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BALL_Y_MOVE = -7;
    public static final int MOST_LINE_BLOCKS = 10;
    public static final int TOTAL_LINES = 5;
    public static final int CONST = 5;

    /*
     * MEMBERS
     */
    private Color[] colors = {Color.GRAY.brighter(), Color.RED.brighter(),
            Color.YELLOW.brighter(), Color.BLUE.brighter(),
            Color.WHITE.brighter()};
    private List<Block> pBlocks = new ArrayList<Block>();
    private boolean shouldCreate = true;
    private int fcnt = 0; // border and layout blocks counter
    private int prevInDeltaSeries;
    private int width;
    private int height;
    /**
     * Constructor of Green 3 level.
     * @param screenWidth - screen's width.
     * @param screenHeight - screen's height.
     */
    public GreenThree(int screenWidth, int screenHeight) {
        this.prevInDeltaSeries = MOST_LINE_BLOCKS + 1;
        this.width = screenWidth;
        this.height = screenHeight;
    }
    /**
     * Getter of level's balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return TOTAL_BALLS;
    }
    /**
     * setter of balls velocities for this level.
     *
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
     *
     * @return the speed.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }
    /**
     * getter of this level's paddle width.
     *
     * @return the width.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    /**
     * getter of this level's name.
     *
     * @return the name.
     */
    public String levelName() {
        return LEVEL_NAME;
    }
    /**
     * getter of this level's background.
     *
     * @return the background.
     */
    public Sprite getBackground() {
        return new GreenThreeBackground(width, height);
    }
    /**
     * getter of this level's block ordering.
     *
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
     *
     * @return the number of breakable blocks.
     */
    public int numberOfBlocksToRemove() {
        return this.pBlocks.size() - this.fcnt;
    }
    /**
     * function sets the pattern of the breakables.
     */
    public void stageDesign() {
        int curStage = MOST_LINE_BLOCKS;
        int k = 0;
        int space = 0;
        int line = 0;
        while (line < TOTAL_LINES) {
            for (int i = 0; i < curStage; i++) {
                this.pBlocks.add(new Block((width
                        - GameLevel.BORDER_SIZE - BLOCK_WIDTH)
                        - space, GameLevel.UPPER_SPACE + BLOCK_HEIGHT * line,
                        BLOCK_WIDTH, BLOCK_HEIGHT, this.getBlockColors(line)));
                this.pBlocks.get(i + k).setHitPoints(2);

                if (curStage < MOST_LINE_BLOCKS) {
                    this.pBlocks.get(i + k).setHitPoints(1);
                }
                space += BLOCK_WIDTH;
            }
            curStage--;
            k = this.differentiateMinusOne(k);
            space = 0;
            line += 1;
        }
    }

    /**
     * A Changing Arithmetic progression creation function for the blocks.
     *
     * @param currentDelta
     *            - the changing difference of the series
     * @return a new delta
     */
    public int differentiateMinusOne(int currentDelta) {
        this.prevInDeltaSeries = this.prevInDeltaSeries - 1;
        return (currentDelta + this.prevInDeltaSeries);
    }

    /**
     * getter of colors collection.
     *
     * @param index
     *            - choose a color from the list.
     * @return a color.
     */
    private Color getBlockColors(int index) {
        return this.colors[index];
    }

    /**
     * function sets the borders and layout.
     */
    public void frameCreation() {
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN
                + GameLevel.BORDER_SIZE, width,
                GameLevel.BORDER_SIZE, // upperBorder
                Color.GRAY.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE,
                GameLevel.ORIGIN, GameLevel.BORDER_SIZE,
                height, Color.GRAY.darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN,
                GameLevel.BORDER_SIZE, height, Color.GRAY
                        .darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, height,
                width, GameLevel.BORDER_SIZE)); // DeathBlock
        this.fcnt++;
        // Corner Checkers.
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN,
                GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, Color.RED
                        .darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, height
                - GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE,
                GameLevel.BORDER_SIZE, Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE,
                GameLevel.ORIGIN, GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE,
                Color.RED.darker()));
        this.fcnt++;
        pBlocks.add(new Block(width - GameLevel.BORDER_SIZE,
                height - GameLevel.BORDER_SIZE,
                GameLevel.BORDER_SIZE, GameLevel.BORDER_SIZE, Color.RED
                        .darker()));
        this.fcnt++;
        pBlocks.add(new Block(GameLevel.ORIGIN, GameLevel.ORIGIN,
                width, GameLevel.BORDER_SIZE, // Information
                Color.WHITE));
        this.fcnt++;
    }

}
