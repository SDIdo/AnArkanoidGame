package geometryprimitives;

import java.awt.Color;


import java.util.List;
import java.util.ArrayList;

import biuoop.DrawSurface;
/**
 * width, height and corners of a rectangle, collision areas and more..
 * @author idonata 305727802
 *
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Color color;
    /**
     * Constructor of rectangle.
     * @param upperLeft - the upper left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
    }
    /**
     * Function estimate intersection points.
     * @param line - the line with which intersection point may appear.
     * @return - a (possibly empty) List of intersection points
    // with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> collisionPoints = new ArrayList<Point>();
        Point collisionPoint = new Point(0, 0);
        for (int k = 0; k < 4; k++) {
            collisionPoint = line.intersectionWith(lines()[k]);
            if (collisionPoint != null) {
                collisionPoints.add(collisionPoint);
            }
        }
        return collisionPoints;
    }
    /**
     * Getter of the width and height of the rectangle.
     * @return the width and height of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
/**
 * Getter of the rectangle height.
 * @return the rectangle height.
 */
    public double getHeight() {
        return this.height;
    }
    /**
     * Getter of the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Getter of the upper-right point of the rectangle.
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.upperLeft.getX() + this.getWidth(),
                this.upperLeft.getY());
        return upperRight;
    }
    /**
     * Getter of the bottom-left point of the rectangle.
     * @return the bottom-left point of the rectangle.
     */
    public Point getBottomLeft() {
        Point upperRight = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.getHeight());
        return upperRight;
    }
    /**
     * Getter of the bottom-right point of the rectangle.
     * @return the bottom-right point of the rectangle.
     */
    public Point getBottomRight() {
        Point bottomRight = new Point(this.upperLeft.getX() + this.getWidth(),
                this.upperLeft.getY() + this.getHeight());
        return bottomRight;
    }
    /**
     * Getter of the upperLine of the rectangle.
     * @return the upperLine of the rectangle
     */
    public Line getUpperHorizontal() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }
    /**
     * Returns the bottomLine of the rectangle.
     * @return the bottomLine of the rectangle
     */
    public Line getBottomHorizontal() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }
    /**
     * Getter of the vertical left line of the rectangle.
     * @return the vertical left Line of the rectangle
     */
    public Line getVerticalLeft() {
        return new Line(this.getUpperLeft(), this.getBottomLeft());
    }
/**
 * Getter of the vertical right Line of the rectangle.
 * @return the vertical right Line of the rectangle
 */
    public Line getVerticalRight() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }
/**
 * Function collects the lines of the rectangle.
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
/**\
 * Setter of color of rectangle.
 * @param newColor - the desired color.
 */
    public void setColor(Color newColor) {
        this.color = newColor;
    }
    /**
     * Function finds the point relative to a certain line type.
     * @param point - the tested point.
     * @return the line at which a collision took place.
     */
    public Line fromPointToLine(Point point) {
        double leftX = this.getUpperLeft().getX();
        double rightX = this.getUpperRight().getX();
        double upY = this.getUpperRight().getY();
        double bottomY = this.getBottomRight().getY();
        if (point.getX() >= rightX) {
            return this.getVerticalRight();
        }
        if (point.getX() <= leftX) {
            return this.getVerticalLeft();
        }

        if (point.getX() < rightX && point.getX() > leftX) { //between the X's
            if (Math.floor(point.getY()) <= upY) { //A hit from above
                return this.getUpperHorizontal();
            }
            if (Math.ceil(point.getY()) >= bottomY) { //A hit from below
                return this.getBottomHorizontal();
            }
            if (point.getY() < bottomY && point.getY() > upY) {
                if ((point.getY() - bottomY) > (point.getY() - upY)) {
                    return this.getBottomHorizontal();
                }
                if ((point.getY() - bottomY) < (point.getY() - upY)) {
                    return this.getUpperHorizontal();
                }
            } else {
                return null;
            }
        }
        if (point.getY() <= upY) {
            return this.getUpperHorizontal();
        }
        if (point.getY() >= bottomY) {
            return this.getBottomHorizontal();
        }
        if (point.getY() < bottomY && point.getY() > upY) { //between the Y's
            if (point.getX() >= rightX) { //A hit from the right (to the left)
                return this.getVerticalRight();
            }
            if (point.getX() <= leftX) { //A hit from the left (to the right)
                return this.getVerticalLeft();
            }
            if (point.getX() < rightX && point.getX() > leftX) {
                if ((point.getX() - rightX) > (point.getX() - leftX)) {
                    return this.getVerticalRight();
                }
                if ((point.getX() - rightX) < (point.getX() - leftX)) {
                    return this.getVerticalLeft();
                }
            } else {
                return null;
            }
        }
        return null;
    }
/**
 * Function draws the rectangle on the screen.
 * @param d - the DrawSurface desired to draw the rectangle on.
 */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
    }
}
