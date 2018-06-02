package geometryprimitives;
/**
 * This class contains the attributes and functions of the point.
 * @author Idonata 305727802.
 *
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor of the point.
     * @param x - horizontal value of the center of the point.
     * @param y - vertical value of the center of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates the distance between two points.
     * @param other - the other point.
     * @return the distance between this point and the given point.
     */
    public double distance(Point other) { // The distance formula
        return Math
                .sqrt(((this.getX() - other.getX()) * (this.getX() - other
                        .getX()))
                        + ((this.getY() - other.getY()) * (this.getY() - other
                                .getY())));
    }

    /**
     * Concludes whether two pointers are the same.
     * @param other - the point of which this point is being tested with.
     * @return true or false regarding conclusion.
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }
        return false;
    }
    /**
     * A horizontal value getter.
     * @return the horizontal value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * A vertical value getter.
     * @return the vertical value.
     */
    public double getY() {
        return this.y;
    }
    /**
     * A Horizontal value setter.
     * @param newX - the desired horizontal value
     */
    public void setX(double newX) {
        this.x = newX;
    }
    /**
     * A Vertical value setter.
     * @param newY - the desired vertical value
     */
    public void setY(double newY) {
        this.y = newY;
    }
    /**
     * Function checks whether a certain point is on a certain line.
     * @param line - the tested for line.
     * @return true or false accordingly.
     */
    public boolean isOnLine(Line line) {
        boolean firstXCase = (this.getX() >= line.start().getX() && this.getX() <= line.end().getX());
        boolean secondXCase = (this.getX() <= line.start().getX() && this.getX() >= line.end().getX());
        boolean firstYCase = (this.getY() >= line.start().getY() &&  this.getY() <= line.end().getY());
        boolean secondYCase = (this.getY() <= line.start().getY() && this.getY() >= line.end().getY());
        return ((firstXCase && firstYCase) || (secondXCase && secondYCase));
    }
}

