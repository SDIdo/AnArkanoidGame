package collision;
import geometryprimitives.Point;
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author idonata 305727802.
 */
public class Velocity {
    private double dx;
    private double dy;

    /** Constructor of velocity.
     * @param dx - the change in horizontal values
     * @param dy - the change in vertical values
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Getter of the horizontal change.
     * @return the horizontal change value
     */
    public double getDx() {
        return this.dx;
    }
    /** Getter of the vertical change.
     * @return the vertical change value
     */
    public double getDy() {
        return this.dy;
    }
    /** Setter of horizontal change.
     *
     * @param newDx - the desired change in horizontal change
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /** Setter of vertical change.
     *
     * @param newDy - the desired change in vertical change
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - a desired point to be changed
     * @return the point with changed values
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }
    /**
     * function calculates and returns velocity from a given angle and speed.
     * @param angle - a desired angle
     * @param speed - a desired speed
     * @return the calculation of velocity that is created by them
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle)); // frame is rotated
                                                             // and flipped in comparison
                                                             // with the unit circle
                                                             // hence the
                                                             // usage of the
                                                             // trigonometrical
                                                             // functions as
                                                             // such
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}

