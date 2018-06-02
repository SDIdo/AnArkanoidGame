package mainobjects;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;

import levels.GameLevel;
import collision.Collidable;
import collision.Velocity;
import backgrounds.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle interacts with the ball using the keyboard to move it around.
 *
 * @author idonata 305727802
 *
 */
public class Paddle implements Collidable, Sprite {
    /*
     * MAGIC NUMBERS
     */
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int BORDER_SIZE = 25;
    public static final int HEIGHT = 20;
    public static final int LEFT_MOST_DEGREE = 120;
    public static final int NUM_OF_FRAGMETNS = 5;
    public static final int DEG_DIFF = 15;
    public static final int RIGHT_MOST_DEG = 240;
    public static final int RIGHT_DEG = 195;
    public static final int LEFT_DEG = 165;
    public static final int RIGHT_CORN_HIT = 50;
    public static final int LEFT_CORN_HIT = -50;
    /*
     * MEMBERS
     */
    private int x;
    private int y;
    private int speed;
    private KeyboardSensor keyBoard;
    private int width;;
    private int fragWidth;

    /**
     * Constructor of paddle.
     *
     * @param x
     *            - x value of the paddle.
     * @param y
     *            - y value of the paddle.
     * @param speed
     *            - speed of the paddle.
     * @param keyBoard
     *            - keyboard to control the paddle.
     */
    public Paddle(int x, int y, int speed, KeyboardSensor keyBoard) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.keyBoard = keyBoard;
    }

    /**
     * Getter of the x value of the paddle.
     *
     * @return the x value of the paddle.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter of the y value of the paddle.
     *
     * @return the y value of the paddle.
     */
    public int getY() {
        return this.y;
    }

    // Movement
    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
            this.x -= speed;
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
            this.x += speed;
    }

    /**
     * Function draws the paddle on the screen.
     *
     * @param d
     *            - DrawSurface desired to draw upon.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle(this.x, this.y, this.width, HEIGHT);
        d.setColor(Color.BLACK.darker());
        d.drawRectangle(this.x, this.y, this.width, HEIGHT);
    }
    /**
     * function calls for movement each passing time.
     */
    public void timePassed() {
        if (this.keyBoard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.x - speed / 2 > BORDER_SIZE) {
            this.moveLeft();
        }
        if (this.keyBoard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.x + speed / 2 < SCREEN_WIDTH - BORDER_SIZE - this.width) {
        this.moveRight();
        }
    }
    /**
     * Getter of the width of the paddle.
     *
     * @return the width of the paddle
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * setter of the width of the paddle.
     * @param newWidth - the desired new width.
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Getter of the height and height of the paddle.
     *
     * @return the height and height of the paddle
     */
    public double getHeight() {
        return HEIGHT;
    }
    /**
     * Getter of the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.x, this.y);
    }
    /**
     * Getter of the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.getUpperLeft().getX()
                + this.getWidth(), this.getUpperLeft().getY());
        return upperRight;
    }
    /**
     * Getter of the bottom-left point of the rectangle.
     *
     * @return the bottom-left point of the rectangle.
     */
    public Point getBottomLeft() {
        Point upperRight = new Point(this.getUpperLeft().getX(), this
                .getUpperLeft().getY() + this.getHeight());
        return upperRight;
    }
    /**
     * Getter of the bottom right point of the rectangle.
     *
     * @return - the bottom-right point of the rectangle.
     */
    public Point getBottomRight() {
        Point bottomRight = new Point(this.getUpperLeft().getX()
                + this.getWidth(), this.getUpperLeft().getY()
                + this.getHeight());
        return bottomRight;
    }
    /**
     * Function returns the upperLine of the rectangle.
     *
     * @return - the upperLine of the rectangle
     */
    public Line getUpperHorizontal() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }
    /**
     * Function returns the bottomLine of the rectangle.
     *
     * @return - the bottomLine of the rectangle
     */
    public Line getBottomHorizontal() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }
    /**
     * Function returns the vertical left Line of the rectangle.
     *
     * @return - the vertical left Line of the rectangle
     */
    public Line getVerticalLeft() {
        return new Line(this.getUpperLeft(), this.getBottomLeft());
    }
    /**
     * Function returns the vertical right Line of the rectangle.
     *
     * @return - the vertical right Line of the rectangle
     */
    public Line getVerticalRight() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }

    /**
     * Function contains a collection of lines representing the paddle's shape.
     *
     * @return - the collection.
     */
    public Line[] lines() {
        Line[] lines = new Line[4];
        lines[0] = this.getUpperHorizontal();
        lines[1] = this.getVerticalRight();
        lines[2] = this.getBottomHorizontal();
        lines[3] = this.getVerticalLeft();
        return lines;
    }

    /**
     * Function separates the paddle to segments to bring different velocities
     * there.
     *
     * @param collisionPoint
     *            - the point of collision.
     * @param currentVelocity
     *            - velocity at hit.
     * @return - a new velocity for the collider
     */
    public Velocity getVelByPaddleFragment(Point collisionPoint,
            Velocity currentVelocity) {
        double movementDegree = 0;
        fragWidth = this.width / NUM_OF_FRAGMETNS;
        for (int i = 0; i < NUM_OF_FRAGMETNS; i++) {
            int segStart = this.x + this.fragWidth * i;
            int segEnd = this.x + this.fragWidth + this.fragWidth * i;
            if (collisionPoint.getX() <= segEnd
                    && collisionPoint.getX() >= segStart) {
                if (i == 0) {
                    movementDegree = LEFT_MOST_DEGREE;
                    break;
                }
                if (i == 1) {
                    movementDegree = LEFT_DEG;
                    break;
                }
                if (i == 2) {
                    return new Velocity(currentVelocity.getDx(), // change
                                                                 // direction
                            -currentVelocity.getDy());
                }
                if (i == 3) {
                    movementDegree = RIGHT_DEG;
                    break;
                }
                if (i == 4) {
                    movementDegree = RIGHT_MOST_DEG;
                    break;
                }
            }
        }
        double updatedSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));

        return Velocity.fromAngleAndSpeed(movementDegree, -updatedSpeed);
    }

    /**
     * Getter of paddle shape using Rectangle.
     *
     * @return - the shape.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(new Point(this.getX(), this.getY()),
                this.getWidth(), this.getHeight());
    }

    /**
     * Function adds the paddle to the gameLevel.
     *
     * @param g
     *            - the desired gameLevel for the paddle to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Function removes the paddle from the gameLevel.
     *
     * @param g
     *            - the desired gameLevel for the paddle to be removes from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * Function notifies the object that we collided with it at collisionPoints.
     * .
     * @param collisionPoint
     *            - the place which the collision occured.
     * @param currentVelocity
     *            - the velocity with which the collision occured.
     * @param hitter - the hitter ball.
     * @return - a new velocity for the collider.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        double ballSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        if (collisionPoint == null) {
            return currentVelocity;
        }
        if (collisionPoint
                .equals(this.getCollisionRectangle().getBottomRight())
                || collisionPoint.equals(this.getCollisionRectangle()
                        .getBottomLeft())) { // Bottom Corner hit
            return new Velocity(-currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (collisionPoint.equals(this.getCollisionRectangle().getUpperRight())) {
            return Velocity.fromAngleAndSpeed(RIGHT_CORN_HIT, ballSpeed); // UpperRight
                                                               // corner hit
        }
        if (collisionPoint.equals(this.getCollisionRectangle().getUpperLeft())) {
            return Velocity.fromAngleAndSpeed(LEFT_CORN_HIT, ballSpeed); // UpperLeft
                                                               // corner hit
        }
        Line lineOfCollision = this.getCollisionRectangle().fromPointToLine(
                collisionPoint);
        if (lineOfCollision.equals(this.getCollisionRectangle()
                .getUpperHorizontal())) { // Upper Horizontal line hit
            return getVelByPaddleFragment(collisionPoint, currentVelocity);
        }
        if (lineOfCollision.equals(this.getCollisionRectangle()
                .getVerticalRight())) { // Right Vertical line hit
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        if (lineOfCollision.equals(this.getCollisionRectangle()
                .getBottomHorizontal())) { // Bottom Horizontal line hit
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (lineOfCollision.equals(this.getCollisionRectangle()
                .getVerticalLeft())) { // Left Vertical line hit
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        } else {
            return currentVelocity; // No hit
        }
    }
}
