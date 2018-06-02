package levels;

import indicators.Counter;
import indicators.LevelNameIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import animacion.Animation;
import animacion.AnimationRunner;
import animacion.CountdownAnimation;
import animacion.PauseScreen;
import backgrounds.Sprite;
import backgrounds.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.Random;

import listeners.BallAdder;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import mainfiles.GameEnvironment;
import mainobjects.Ball;
import mainobjects.Block;
import mainobjects.Paddle;
import collision.Collidable;

/**
 * This class organizes the elements and building blocks of the gameLevel.
 *
 * @author idonata 305727802
 *
 */
public class GameLevel implements Animation {
    /*
     * MAGIC NUMBERS
     */
    public static final int MEDIUM = 30;
    public static final int HARD = 90;
    public static final int HARD_DUPS = 50; // of block adders grants
    public static final int PADDLE_PLACE = 400;
    public static final int BALL_X_PLACE = 400;
    public static final int BALL_Y_PLACE = 540;
    public static final int BORDER_SIZE = 25;
    public static final int UPPER_SPACE = 150;
    public static final int BALL_SIZE = 6;
    public static final int ORIGIN = 0;
    /*
     * MEMBERS
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Paddle player = new Paddle(ORIGIN, ORIGIN, ORIGIN, this.keyboard);
    private Color[] blockColors = {Color.GRAY, Color.RED, Color.YELLOW,
            Color.BLUE, Color.PINK, Color.GREEN };
    private Color[] ballColors = {Color.CYAN.brighter(), Color.RED.brighter(),
            Color.YELLOW.brighter(), Color.WHITE.brighter(),
            Color.MAGENTA.brighter(), Color.GREEN.brighter() };

    private LevelInformation levelInfo;

    private Counter score = new Counter();
    private ScoreTrackingListener stl = new ScoreTrackingListener(score);

    private List<Ball> ballCollection = new ArrayList<Ball>();
    private Counter ballsRemained = new Counter();
    private BallRemover ballRem = new BallRemover(this, ballsRemained);
    private BallAdder ballAdder = new BallAdder(this, ballsRemained);

    private List<Block> pBlocks = new ArrayList<Block>();
    private Counter blocksRemained = new Counter();

    private BlockRemover bloRem = new BlockRemover(this, blocksRemained);
    private Random rand = new Random();

    private Counter numOfLives = new Counter();
    private AnimationRunner runner = new AnimationRunner();
    private boolean running;
    private int height;

    /**
     * Constructor of GameLevel.
     *
     * @param newInfo
     *            - level's information.
     * @param gameGui
     *            - graphical interface utility.
     * @param screenHeight
     *            - screen height.
     * @param gameKeyboard
     *            - a keyboard utility.
     * @param ar
     *            - an instance from animation runners.
     * @param providedScore
     *            - register of score.
     * @param providedLives
     *            - register of lives.
     */
    public GameLevel(LevelInformation newInfo, GUI gameGui, KeyboardSensor gameKeyboard,
            int screenHeight, AnimationRunner ar,
            Counter providedScore, Counter providedLives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gameGui;
        this.keyboard = gameKeyboard;
        this.levelInfo = newInfo;
        this.runner = ar;
        this.score = providedScore;
        this.numOfLives = providedLives;
        this.height = screenHeight;
    }

    /**
     * adds a collidable.
     *
     * @param c
     *            - the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * removes a collidable.
     *
     * @param c
     *            - the collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * adds a Sprite.
     *
     * @param s
     *            - the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removes a Sprite.
     *
     * @param s
     *            - the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * An array of colors for the blocks.
     *
     * @param index
     *            - the desired color index.
     * @return the color of the desired index from the array
     */
    public Color getBlockColors(int index) {
        return this.blockColors[index];
    }

    /**
     * getter of game's environment.
     *
     * @return the environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Function is responsible to set the balls initial placement.
     */
    public void ballCreator() {
        int randomColor = 0;
        Ball ball;

        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            randomColor = rand.nextInt(this.ballColors.length);
            ball = new Ball(BALL_X_PLACE, BALL_Y_PLACE, BALL_SIZE,
                    this.ballColors[randomColor], environment);
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        ballsRemained.increase(this.getBallCollection().size());
        ballRem = new BallRemover(this, ballsRemained);
        ballAdder = new BallAdder(this, ballsRemained);
    }

    /**
     * Function is responsible to create the paddle.
     */
    public void paddleCreator() {
        this.player = new Paddle(PADDLE_PLACE
                - (this.levelInfo.paddleWidth() / 2), height - 2
                * BORDER_SIZE + (BORDER_SIZE / 2),
                this.levelInfo.paddleSpeed(), this.keyboard);
        this.player.setWidth(this.levelInfo.paddleWidth());
        this.player.addToGame(this);
    }

    /**
     * Getter of level's balls.
     *
     * @return a ball list.
     */
    public List<Ball> getBallCollection() {
        return this.ballCollection;
    }

    /**
     * Getter of level's remaining blocks.
     *
     * @return a block list.
     */
    public List<Block> getRemainingBlocks() {
        return this.pBlocks;
    }

    /**
     * Getter of level's remaining blocks.
     *
     * @return a counter.
     */
    public Counter getBlocksToRemove() {
        return this.blocksRemained;
    }

    /**
     * Getter of game's block Remover.
     *
     * @return game's blockRemover.
     */
    public BlockRemover getBloRem() {
        return this.bloRem;
    }

    /**
     * Getter of game's ball Remover.
     *
     * @return game's ballRemover.
     */
    public BallRemover getBallRem() {
        return this.ballRem;
    }

    /**
     * Getter of game's score tracking listener.
     *
     * @return game's score tracking listener.
     */
    public ScoreTrackingListener getStl() {
        return this.stl;
    }

    /**
     * Getter of game's number of player lives.
     *
     * @return game's counter of lives.
     */
    public Counter getLives() {
        return this.numOfLives;
    }

    /**
     * Getter of game's score.
     *
     * @return game's counter of score.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Gives new balls and sets the paddle in the middle.
     */
    public void ballsOnTopOfPaddle() {
        this.paddleCreator();
        this.ballCreator();
    }

    /**
     * Initialize a new gameLevel: create the Blocks and Ball (and Paddle) and
     * add them to the gameLevel.
     */
    public void initialize() {
        this.stl = new ScoreTrackingListener(this.score);
        this.sprites.addSprite(this.levelInfo.getBackground());
        int totalBlocks = this.levelInfo.blocks().size();
        int breakables = this.levelInfo.numberOfBlocksToRemove();
        for (int i = 0; i < totalBlocks; i++) {
            this.levelInfo.blocks().get(i).addToGame(this);
        }
        for (int j = 0; j < levelInfo.numberOfBlocksToRemove(); j++) {
            this.pBlocks.add(this.levelInfo.blocks().get(j));
        }
        this.blocksRemained.increase(levelInfo.numberOfBlocksToRemove());
        this.sprites.addSprite(new ScoreIndicator(this)); // Score panel
        this.sprites.addSprite(new LivesIndicator(this)); // Live panel
        this.sprites.addSprite(new LevelNameIndicator(levelInfo.levelName()));
        /*
         * BLOCK ADDERS & BLOCK REMOVERS
         */
        this.bloRem = new BlockRemover(this, blocksRemained);
        if (breakables >= MEDIUM) {
            int randomDeathBlock = rand.nextInt(breakables);
            int randomViverBlock = rand.nextInt(breakables);
            this.pBlocks.get(randomDeathBlock).addHitListener(ballRem);
            while (randomViverBlock == randomDeathBlock) {
                randomViverBlock = rand.nextInt(this.pBlocks.size());
                this.pBlocks.get(randomViverBlock).addHitListener(ballAdder);
            }
        }
        if (breakables >= HARD) { // Hard but fun ;)
            for (int i = 0; i < HARD_DUPS; i++) {
                int randomViverBlock = rand.nextInt(breakables);
                randomViverBlock = rand.nextInt(this.pBlocks.size());
                this.pBlocks.get(randomViverBlock).addHitListener(ballAdder);
            }
        }
        // ////////////////////////////////////////////////////////////////
        this.ballsOnTopOfPaddle();
    }

    /**
     * playOneTurn -- start the animation loop of one turn.
     */
    public void playOneTurn() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // 3..2..1..GO
        this.running = true;
        this.runner.run(this); // THIS GOES BACK TO RUN OF ANIMATION.
    }

    /**
     * Run -- start the animation loop.
     */
    public void run() {
        this.runner = new AnimationRunner(this.gui);
        this.initialize();
        this.playOneTurn();
        while (this.numOfLives.getValue() > 0
                && this.blocksRemained.getValue() > 0) {

            this.ballsOnTopOfPaddle();

            this.playOneTurn();
        }
    }

    /**
     * Function runs one frame of animation.
     *
     * @param d
     *            - the draw surface upon which animation is to be.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.blocksRemained.getValue() <= 0) { // back to run when no more
                                                   // blocks
            this.player.removeFromGame(this);
            this.running = false;
            return;
        }
        if (this.ballsRemained.getValue() <= 0) { // back to run when no more
                                                  // balls.
            this.player.removeFromGame(this);
            this.numOfLives.decrease(1);
            this.ballsOnTopOfPaddle();
            this.running = false;
            return;
        }
    }

    /**
     * Function checks whether running should halt.
     *
     * @return true or false accordingly.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
