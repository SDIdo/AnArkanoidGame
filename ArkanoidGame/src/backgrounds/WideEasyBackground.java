package backgrounds;
import java.awt.Color;


import levels.GameLevel;
import levels.WideEasy;
import biuoop.DrawSurface;

/**
 * abstract art appearance of WideEasy level.
 *
 * @author idonata 305727802
 *
 */
public class WideEasyBackground implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    public static final int OUTER_SUN_RADIOUS = 60;
    public static final int SUN_RADIOUS = 50;
    public static final int INNER_SUN_RADIOUS = 40;
    public static final int RAYS_PER_BLOCK = 10;
    /*
     * MEMBERS
     */
    private int width;
    private int height;
    /**
     * Constructor of WideEasyBackground.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public WideEasyBackground(int screenWidth, int screenHeight) {
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
        d.setColor(new Color(252, 255, 235));
        d.fillRectangle(GameLevel.ORIGIN, GameLevel.ORIGIN,
                width, height);

        // Set of lightrays//
        d.setColor(new Color(255, 255, 189));
        for (int i = 0; i < WideEasy.MOST_LINE_BLOCKS * RAYS_PER_BLOCK; i++) {
            d.drawLine(7 * GameLevel.BORDER_SIZE,
                    height * 1 / 5, GameLevel.BORDER_SIZE
                            + WideEasy.BLOCK_WIDTH * i / RAYS_PER_BLOCK,
                    height * 3 / 8);
        }
        // The sun//
        d.setColor(new Color(253, 255, 225));
        d.fillCircle(7 * GameLevel.BORDER_SIZE,
                height * 1 / 5, OUTER_SUN_RADIOUS);
        d.setColor(new Color(242, 242, 32));
        d.fillCircle(7 * GameLevel.BORDER_SIZE,
                height * 1 / 5, SUN_RADIOUS);
        d.setColor(new Color(252, 252, 80));
        d.fillCircle(7 * GameLevel.BORDER_SIZE,
                height * 1 / 5, INNER_SUN_RADIOUS);

    }

    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }

}
