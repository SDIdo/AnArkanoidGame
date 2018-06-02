package backgrounds;
import java.awt.Color;
import levels.DirectHit;



import levels.GameLevel;
import biuoop.DrawSurface;
/**
 * Background for DirectHit level.
 *
 * @author idonata 305727802
 *
 */
public class DirectHitBackground implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    private static final int HALF_LINE = 150;
    /*
     * MEMBERS
     */
    private int width;
    private int height;
    /**
     * Constructor of DirectHit.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public DirectHitBackground(int screenWidth, int screenHeight) {
        this.width = screenWidth;
        this.height = screenHeight;
    }
    /**
     * Creates the level's layout.
     *
     * @param d
     *            - the surface upon which to draw.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(GameLevel.ORIGIN, GameLevel.ORIGIN,
                width, height);
        d.setColor(Color.BLUE.darker());
        // /The rounds of target//
        d.drawCircle(width / 2, height * 2
                / 7 + DirectHit.BLOCK_HEIGHT / 2, 120);
        d.drawCircle(width / 2, height * 2
                / 7 + DirectHit.BLOCK_HEIGHT / 2, 90);
        d.drawCircle(width / 2, height * 2
                / 7 + DirectHit.BLOCK_HEIGHT / 2, 60);
        // /The lines of target//
        d.drawLine(width / 2 - HALF_LINE, height
                * 2 / 7 + DirectHit.BLOCK_HEIGHT / 2,
                width / 2 + HALF_LINE, height * 2
                        / 7 + DirectHit.BLOCK_HEIGHT / 2);
        d.drawLine(width / 2,
                height * 2 / 7 - HALF_LINE,
                width / 2,
                height * 2 / 7 + HALF_LINE);
    }
    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }
}
