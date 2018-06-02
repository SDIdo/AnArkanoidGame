package backgrounds;
import java.awt.Color;

import levels.GameLevel;
import biuoop.DrawSurface;

/**
 * The appearance of level 3 : Green 3.
 *
 * @author idonata 305727802
 *
 */
public class GreenThreeBackground implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    public static final int OUTER_SPARK = 15;
    public static final int SPARK = 10;
    public static final int INNER_SPARK = 5;
    public static final int WINDOWS = 6;
    public static final int WINDOW_SIZE = 15;
    public static final int WINDOW_MARGIN = 20;
    public static final int WINDOW_SPACE = 5;
    /*
     * MEMBERS
     */
    private int width;
    private int height;
    /**
     * Constructor of GreenThreeBackground.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public GreenThreeBackground(int screenWidth, int screenHeight) {
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
        d.setColor(Color.GREEN.darker());
        d.fillRectangle(GameLevel.ORIGIN, GameLevel.ORIGIN,
                width, height);
        // Building Creation

        d.setColor(new Color(94, 92, 82));
        d.fillRectangle(
                GameLevel.BORDER_SIZE * 5 - (GameLevel.BORDER_SIZE / 4),
                height * 1 / 4, GameLevel.BORDER_SIZE / 2,
                height);

        d.setColor(new Color(63, 62, 57));
        d.fillRectangle(GameLevel.BORDER_SIZE * 4,
                height * 6 / 11, GameLevel.BORDER_SIZE * 2,
                height);

        d.setColor(Color.BLACK);
        d.fillRectangle(GameLevel.BORDER_SIZE * 2,
                height * 2 / 3, GameLevel.BORDER_SIZE * 6,
                height);

        // Spark Creation
        d.setColor(Color.ORANGE);
        d.fillCircle(GameLevel.BORDER_SIZE * 5,
                height * 1 / 4, OUTER_SPARK);
        d.setColor(Color.RED.brighter());
        d.fillCircle(GameLevel.BORDER_SIZE * 5,
                height * 1 / 4, SPARK);
        d.setColor(Color.WHITE);
        d.fillCircle(GameLevel.BORDER_SIZE * 5,
                height * 1 / 4, INNER_SPARK);

        // Windows Creation
        d.setColor(new Color(250, 249, 239));
        for (int i = 0; i < WINDOWS; i++) {
            for (int j = 0; j < WINDOWS; j++) {
                d.fillRectangle(GameLevel.BORDER_SIZE * 2 + 3 * WINDOW_SPACE
                        + (WINDOW_MARGIN * i), height * 2 / 3
                        + 3 * WINDOW_SPACE + (2 * WINDOW_MARGIN * j),
                        WINDOW_SIZE, 2 * WINDOW_SIZE);
            }
        }
    }
    /**
     * tells that time has passed.
     */
    public void timePassed() {

    }

}
