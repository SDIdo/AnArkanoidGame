package geometryprimitives;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * This class contains all the attributes and functions of a Line.
 * @author Idonata 305727802
 */
public class Line {
    private Point start;
    private Point end;
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    /**
     * Constructor of the Line.
     * @param x1 - The Horizontal value of first point
     * @param y1 - The Vertical value of first point
     * @param x2 - The Horizontal value of second point
     * @param y2 - The Vertical value of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    /**
     * Constructor of the Line.
     * @param start - The first point of the line.
     * @param end - The second point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Finds the starting point of the line.
     * @return the first point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     * Finds the ending point of the line.
     * @return the second point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * calculates the length from start to end.
     * @return the length between the two
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * function calculates the middle of the line.
     * @return the middle of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        Point mid = new Point(midX, midY);
        return mid;
    }
    /**
     * Function Checks whether this line is the same as other.
     * @param other - the line that this line is being tested with
     * @return - true or false according to conclusion
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)) {
            if (this.end.equals(other.end)) {
                return true;
            }
            return false;
        }
        if (this.start.equals(other.end)) {
            if (this.end.equals(other.start)) {
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * Calculates the Slope of the function.
     * @return the slope of the function
     */
    public double getSlope() {
        if (this.end.getY() == this.start.getY()) { // horizontal line
            return 0;
        }
        if (this.end.getX() == this.start.getX()) { // vertical line
            return Integer.MAX_VALUE; // represents the vertical line
        }
        return (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
    }
    /**
     * calculates the constant of the linear function.
     * @return the constant
     */
    public double getConstant() {
        if (this.end.getX() == this.start.getX()) { // vertical line
            return 0; // value does not matter in this case.
        }
        return this.start.getY() - ((this.start.getX()) * this.getSlope());
    }
    /**
     * calculates f(x).
     * @param x - the horizontal value
     * @return f(x)
     */
    public double calcYByX(double x) {
        if (this.end.getX() == this.start.getX()) {
            return 0; // but wont be used
        }
        return this.getSlope() * x + this.getConstant();
    }
    /**
     * Determines if a certain value is in a segment.
     * @param x - a certain value
     * @param other - the other line which is being tested with
     * @return true or false regarding the conclusion
     */
    public boolean isInSegment(double x, Line other) {
        double biggerXOther = max(other.end.getX(), other.start.getX());
        double smallerXOther = min(other.end.getX(), other.start.getX());
        double biggerXThis = max(this.end.getX(), this.start.getX());
        double smallerXThis = min(this.end.getX(), this.start.getX());
        if (x <= min(biggerXOther, biggerXThis)) {
            if (x >= max(smallerXOther, smallerXThis)) {
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * Determines if a certain value is in a segment.
     * @param y - a certain value
     * @param other - the other line which is being tested with
     * @return true or false regarding the conclusion
     */
    public boolean isInSegmentY(double y, Line other) {
        double biggerYOther = max(other.end.getY(), other.start.getY());
        double smallerYOther = min(other.end.getY(), other.start.getY());
        double biggerYThis = max(this.end.getY(), this.start.getY());
        double smallerYThis = min(this.end.getY(), this.start.getY());
        if (y <= min(biggerYOther, biggerYThis)) {
            if (y >= max(smallerYOther, smallerYThis)) {
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * Finds the point that is in the intersection between this line and the other.
     * @param other - the tested with line
     * @return intersection point
     */
    public Point intersectionWith(Line other) {
        if (this.getSlope() == other.getSlope()) { // same line or parallels
            return null;
        }
        if (other.end.getX() == other.start.getX()) { // other is vertical line
            if (!isInSegment(other.start.getX(), this)) {
                return null;
            }
            if (!isInSegmentY(this.calcYByX(this.start.getX()), other)) {
                return null;
            }
            return new Point(other.start.getX(), this.calcYByX(other.start.getX()));
        }
        if (this.end.getX() == this.start.getX()) { // is vertical check
            if (!isInSegment(this.start.getX(), other)) {
                return null;
            }
            if (!isInSegmentY(other.calcYByX(this.start.getX()), other)) {
                return null;
            }
            return new Point(this.start.getX(), other.calcYByX(this.start
                    .getX()));
        }
        double x = (this.getConstant() - other.getConstant())
                / (other.getSlope() - this.getSlope());
        if (!isInSegment(x, other)) {
            return null;
        }
        return new Point(x, this.calcYByX(x));
    }
    /**
     * concludes whether there was an intersection with another line.
     * @param other - the line that this line is being checked for intersection with
     * @return true or false according to the conclusion
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
    /**
     * determines the minimum between two values.
     * @param valueA - first value
     * @param valueB - second value
     * @return the smaller value, or if they are equals - one of them
     */
    public static double min(double valueA, double valueB) {
        if (valueA <= valueB) {
            return valueA;
        }
        return valueB;
    }
    /**
     * determines the maximum between two values.
     * @param valueA - first value
     * @param valueB - second value
     * @return the higher value, or if they are equals - one of them
     */
    public static double max(double valueA, double valueB) {
        if (valueA >= valueB) {
            return valueA;
        }
        return valueB;
    }
    /**
     * Draws this line in black.
     * @param d - the surface to draw on
     */
    public void drawLine(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) this.x1, (int) this.y1, (int) this.x2, (int) this.y2);
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * Function calculates closest intersection to start of line.
     * @param rect - the rectangle that line might collide with.
     * @return - a collision point or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> collisionPoints = rect.intersectionPoints(this);
        if (collisionPoints.size() == 0) {
            return null;
        } else {
            Point newMin = this.end;
            Point newStart = this.start;
            for (int i = 0; i < collisionPoints.size(); i++) {
                if (newStart.distance(collisionPoints.get(i)) <= newStart.distance(newMin)) {
                    newMin = collisionPoints.get(i);
                }
            }
            return newMin;
        }
    }
 }
