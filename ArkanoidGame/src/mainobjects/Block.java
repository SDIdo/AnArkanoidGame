package mainobjects;
import geometryprimitives.Point;

import geometryprimitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import levels.GameLevel;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.HitNotifier;
import listeners.ScoreTrackingListener;
import collision.Collidable;
import collision.Velocity;
import backgrounds.Sprite;
import biuoop.DrawSurface;

/**
 * Block - This class implements a block in the arkanoid gameLevel.
 *
 * @author idonata
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /*
     * MAGIC NUMBERS
     */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int BORDER_SIZE = 25;
    /*
     * MEMBERS
     */
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int score;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * Constructor of block.
     *
     * @param startX
     *            - the initial x value of the block
     * @param startY
     *            - the initial y value of the block
     * @param width
     *            - the width of the block
     * @param height
     *            - the height of the block
     * @param color
     *            - the color of the block
     */
    public Block(int startX, int startY, int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        this.score = 0;
    }

    /**
     * 2nd Constructor of block.
     *
     * @param startX
     *            - the initial x value of the block
     * @param startY
     *            - the initial y value of the block
     * @param width
     *            - the width of the block
     * @param height
     *            - the height of the block
     */
    public Block(int startX, int startY, int width, int height) {
        this.width = width;
        this.height = height;
        this.startX = startX;
        this.startY = startY;
        this.score = 0;
    }

    /**
     * Setter of block's color.
     *
     * @param newColor
     *            - the desired color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Setter of block's score points.
     *
     * @param newScore
     *            - the updated score.
     */
    public void setHitPoints(int newScore) {
        this.score = newScore;
    }

    /**
     * Getter of blocks score points.
     *
     * @return - block's current score
     */
    public int getHitPoints() {
        return this.score;
    }

    /**
     * Getter of X place of the block.
     *
     * @return the x place of the block.
     */
    public int getStartX() {
        return this.startX;
    }

    /**
     * Getter of Y place of the block.
     *
     * @return the y place of the block.
     */
    public int getStartY() {
        return this.startY;
    }

    /**
     * Getter of width of the block.
     *
     * @return the width of the block.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Getter of height of the block.
     *
     * @return the height of the block.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Function gets the center of the block x wise.
     *
     * @return the center of the block on the x axis.
     */
    public int getXCenter() {
        return Math.round(this.startX + (this.width / 2));
    }

    /**
     * Function gets the center of the block y wise.
     *
     * @return the center of the block on the y axis.
     */
    public int getYCenter() {
        return Math.round(this.startY + (this.height / 2));
    }

    /**
     * Function adds the block to the gameLevel.
     *
     * @param g
     *            - the desired gameLevel to be added to.
     */
    public void addToGame(GameLevel g) {
        BlockRemover blr = g.getBloRem();
        BallRemover balr = g.getBallRem();
        ScoreTrackingListener stl = g.getStl();
        if (this.color == null) {
            g.addCollidable(this);
            this.addHitListener(balr);
            return;
        }
        g.addSprite(this);
        if (this.color == Color.WHITE) {
            this.addHitListener(stl);
        }
        if (this.isBackground()) {
            return;
        }
        if (this.isCornerChecker() || this.isBorderControl()) {
            g.addCollidable(this);
            return;
        } else {
            g.addCollidable(this);
            this.addHitListener(blr);
            this.addHitListener(stl);
            return;
        }
    }
    /**
     * Function removes the block from the gameLevel.
     *
     * @param gameLevel
     *            - the desired gameLevel to be removed from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        if (!this.isBackground()) {
            gameLevel.removeCollidable(this);
        }
    }
    /**
     * Function checks whether the block is the background.
     *
     * @return true or false according to the answer.
     */
    public boolean isBackground() {
        boolean answer = (this.width == SCREEN_WIDTH && this.height == SCREEN_HEIGHT);
        return answer;
    }
    /**
     * Function checks whether the block is one of the borders.
     *
     * @return true or false according to the answer.
     */
    public boolean isBorderControl() {
        boolean answerA = (this.width == SCREEN_WIDTH && this.height == BORDER_SIZE);
        boolean answerB = (this.width == BORDER_SIZE && this.height == SCREEN_HEIGHT);
        if (answerA || answerB) {
            return true;
        }
        return false;
    }
    /**
     * Function checks whether the block is the corner checker.
     *
     * @return true or false according to the answer.
     */
    public boolean isCornerChecker() {
        boolean answer = (this.width == BORDER_SIZE && this.height == BORDER_SIZE);
        return answer;
    }
    /**
     * Function draws the block on the screen.
     *
     * @param d
     *            - a desired DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        if (!this.isCornerChecker()) {
            d.setColor(this.color);
            d.fillRectangle(this.startX, this.startY, this.width, this.height);
            d.setColor(Color.BLACK.darker());
            d.drawRectangle(this.startX, this.startY, this.width, this.height);
            if (!this.isBackground()) {
                d.setColor(Color.WHITE);
            }
        }
    }
    /**
     * Function lowers block's score.
     */
    public void scoreMinus() {
        if (this.getHitPoints() <= 0) {
            this.setHitPoints(0);
        } else {
            this.setHitPoints(this.getHitPoints() - 1);
        }
    }
    /**
     * Function notifies the object that we collided with it at collisionPoints.
     * @param hitter - the ball that collidede with this block.
     * @param collisionPoint
     *            - the place which the collision occured.
     * @param currentVelocity
     *            - the velocity with which the collision occured.
     * @return - a new velocity for the collider.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }

        if (collisionPoint.equals(this.getCollisionRectangle().getUpperLeft()) // Corner
                                                                               // hit
                || collisionPoint.equals(this.getCollisionRectangle()
                        .getUpperRight())
                || collisionPoint.equals(this.getCollisionRectangle()
                        .getBottomRight())
                || collisionPoint.equals(this.getCollisionRectangle()
                        .getBottomLeft())) {
            this.scoreMinus();
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(this.getCollisionRectangle()
                .getUpperHorizontal())) {
            this.scoreMinus();
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), // Upper line hit
                    -currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(this.getCollisionRectangle()
                .getBottomHorizontal())) {
            this.scoreMinus();
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), // Bottom line hit
                    -currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(this.getCollisionRectangle()
                .getVerticalRight())) {
            this.scoreMinus();
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), // Right Vertical line
                                                          // hit
                    currentVelocity.getDy());
        }
        if (collisionPoint.isOnLine(this.getCollisionRectangle()
                .getVerticalLeft())) {
            this.scoreMinus();
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), // Left Vertical line
                                                          // hit
                    currentVelocity.getDy());
        } else {
            return currentVelocity; // no hit
        }
    }

    /**
     * Getter of the shape of the block.
     *
     * @return - a rectangle implementing the shape.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(new Point(this.startX, this.startY), this.width,
                this.height);
    }

    /**
     * This function tells block time passed.
     */
    public void timePassed() {
    }

    /**
     * Function notifies there was a hit.
     * @param hitter - the ball that collided with this block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds a listener to the list.
     * @param hl - the HitListener desired to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes a listener from a list.
     * @param hl - the HitListener desired to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
