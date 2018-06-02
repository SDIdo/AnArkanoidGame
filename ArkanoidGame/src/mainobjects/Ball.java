package mainobjects;
import backgrounds.Sprite;
import biuoop.DrawSurface;
import geometryprimitives.Line;
import geometryprimitives.Point;

import java.awt.Color;

import levels.GameLevel;
import mainfiles.GameEnvironment;
import collision.Collidable;
import collision.CollisionInfo;
import collision.Velocity;

/**
 * This class contains all the attributes and functions of a ball.
 *
 * @author Idonata 305727802
 */
public class Ball implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    public static final int INIT_X_VEL = 4;
    public static final int INIT_Y_VEL = 4;
    /*
     * MEMBERS
     */
    private Point center;
    private int radius;
    private Velocity velocity;
    private Color color;
    private GameEnvironment game;
    private Collidable lastHit = null;

    // constructors
    /**
     * 1st constructor of the ball.
     *
     * @param center
     *            - the starting point of the ball
     * @param r
     *            - the radius of the ball
     * @param color
     *            - the color of the ball
     * @param myGame
     *            - the gameLevel desired to get ball in
     */
    public Ball(Point center, int r, Color color, GameEnvironment myGame) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.game = myGame;
        this.setVelocity(INIT_X_VEL, INIT_Y_VEL);
    }

    /**
     * 2nd constructor of the ball.
     *
     * @param x
     *            - the horizontal value of the starting point of ball
     * @param y
     *            - the vertical value of the starting point of ball
     * @param r
     *            - the radius of the ball
     * @param color
     *            - the color of the ball
     * @param myGame
     *            - the gameLevel desired to get ball in
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment myGame) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.game = myGame;
        this.setVelocity(INIT_X_VEL, INIT_Y_VEL);
    }

    // accessories

    /**
     * Getter of the horizontal value of ball's location.
     *
     * @return the horizontal value
     */
    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * Getter of the vertical value of ball's current location.
     *
     * @return the vertical value
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * Setter of the horizontal value of ball's current location.
     *
     * @param x
     *            - the desired horizontal value
     */
    public void setX(int x) {
        this.center.setX(x);
    }

    /**
     * Setter of the vertical value of ball's current location.
     *
     * @param y
     *            - the desired vertical value
     */
    public void setY(int y) {
        this.center.setY(y);
    }

    /**
     * Getter of the size of ball.
     *
     * @return the size of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Getter of ball's color.
     *
     * @return the color of ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Setter of ball's color.
     *
     * @param newColor
     *            - the desired color to dye ball with
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * 1st Setter of ball's velocity.
     *
     * @param v
     *            - input an instance of velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * 2nd Setter of ball's velocity.
     *
     * @param dx
     *            - desired progression in the horizontal axis
     * @param dy
     *            - desired progression in the vertical axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Function tells ball the required velocity.
     *
     * @return velocity of ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Function asks ball where it is.
     *
     * @return the current location of ball by it's center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Function tells ball where it should be.
     *
     * @param newCenter
     *            - desired new center or location for ball
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Function sets the gameLevel environment.
     *
     * @param newGame
     *            - the gameLevel environment desired.
     */
    public void setGameEnvironment(GameEnvironment newGame) {
        this.game = newGame;
    }

    /**
     * getter of gameLevel environment.
     *
     * @return the gameLevel environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * Adds a ball to the gameLevel.
     *
     * @param g
     *            - the gameLevel required to add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.getBallCollection().add(this);
    }
    /**
     * Removes a ball from the gameLevel.
     *
     * @param g
     *            - the gameLevel required to remove from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.getBallCollection().remove(this); //TODO same as ^above^
    }

    /**
     * Function creates a vector from given start point and velocity.
     *
     * @return a vector line
     */
    public Line getTrajectory() {
        Point afterVel = new Point(Math.round(this.getX()
                + this.velocity.getDx()), Math.round(this.getY()
                + this.velocity.getDy()));
        return new Line(this.getX(), this.getY(), afterVel.getX(),
                afterVel.getY());
    }

    /**
     * Function represents the ability of ball to move.
     */
    public void moveOneStep() {
        Line ballTrajectory = this.getTrajectory();
        CollisionInfo posCol = this.getGameEnvironment().getClosestCollision(
                ballTrajectory);
        if (posCol != null && posCol.collisionObject() != lastHit) {
            lastHit = posCol.collisionObject();
            Point colPoint = new Point(
                    Math.round(posCol.collisionPoint().getX()),
                    Math.round(posCol.collisionPoint().getY()));

            this.setVelocity(posCol.collisionObject().hit(this, colPoint, this.getVelocity()));

        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * A function that draws the ball with regards to its color and size.
     *
     * @param surface
     *            - a desired drawing utility instance
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * timePassed calls for the ball to move.
     */
    public void timePassed() {
        this.moveOneStep();
    }
}
