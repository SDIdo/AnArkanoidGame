package backgrounds;
import java.awt.Color;

import levels.GameLevel;
import biuoop.DrawSurface;

/**
 * Background of FinalFour level.
 *
 * @author idonata 305727802
 *
 */
public class FinalFourBackground implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    public static final int CLOUD_RADIUS = 30;
    public static final int CLOUDS = 2;
    public static final int STREAM_FRAGS = 10;
    public static final int STRAEM_LINE = 11;
    /*
     * MEMBERS
     */
    private int width;
    private int height;
    /**
     * Constructor of FinalFourBackground.
     * @param screenWidth - width of the screen.
     * @param screenHeight - height of the screen.
     */
    public FinalFourBackground(int screenWidth, int screenHeight) {
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
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(GameLevel.ORIGIN, GameLevel.ORIGIN,
                width, height);
        // Streams
        d.setColor(Color.WHITE);
        for (int j = 0; j < CLOUDS; j++) { // acts as a switch
            for (int i = 0; i < STRAEM_LINE; i++) {
                d.drawLine(
                        GameLevel.BORDER_SIZE * 4 + GameLevel.BALL_SIZE * 2
                                + (STREAM_FRAGS * i)
                                + (j * GameLevel.BORDER_SIZE * 20),
                        height * 5 / 6 - 4
                                * GameLevel.BORDER_SIZE
                                + (j * GameLevel.BALL_SIZE * 20),
                        GameLevel.BORDER_SIZE * 4 + (STREAM_FRAGS * i)
                                - STREAM_FRAGS
                                + (j * GameLevel.BORDER_SIZE * 20),
                        height);
            }
        }
        // Left Cloud
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(GameLevel.BORDER_SIZE * 5 + CLOUD_RADIUS / 3,
                height * 6 / 7 - 4 * GameLevel.BORDER_SIZE,
                CLOUD_RADIUS);
        d.fillCircle(GameLevel.BORDER_SIZE * 5, height * 5 / 6
                - 4 * GameLevel.BORDER_SIZE, (CLOUD_RADIUS * 4) / 5);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(GameLevel.BORDER_SIZE * 5 + 2 * CLOUD_RADIUS / 3,
                height * 5 / 6 - 4 * GameLevel.BORDER_SIZE,
                (CLOUD_RADIUS));
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(GameLevel.BORDER_SIZE * 5 + 4 * CLOUD_RADIUS / 3,
                height * 6 / 7 - 4 * GameLevel.BORDER_SIZE,
                (CLOUD_RADIUS * 4) / 5);
        d.fillCircle(GameLevel.BORDER_SIZE * 5 + 6 * CLOUD_RADIUS / 3,
                height * 5 / 6 - 4 * GameLevel.BORDER_SIZE,
                (CLOUD_RADIUS));
        // Right Cloud
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(GameLevel.BORDER_SIZE * 25 + CLOUD_RADIUS / 3,
                height * 6 / 7 - GameLevel.BALL_SIZE * 2,
                CLOUD_RADIUS);
        d.fillCircle(GameLevel.BORDER_SIZE * 24, height * 5
                / 6 - GameLevel.BALL_SIZE * 2, (CLOUD_RADIUS * 4) / 5);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(GameLevel.BORDER_SIZE * 25 + 2 * CLOUD_RADIUS / 3,
                height * 5 / 6 - GameLevel.BALL_SIZE * 2,
                (CLOUD_RADIUS));
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(GameLevel.BORDER_SIZE * 25 + 4 * CLOUD_RADIUS / 3,
                height * 6 / 7 - GameLevel.BALL_SIZE * 2,
                (CLOUD_RADIUS * 4) / 5);
        d.fillCircle(GameLevel.BORDER_SIZE * 25 + 6 * CLOUD_RADIUS / 3,
                height * 5 / 6 - GameLevel.BALL_SIZE * 2,
                (CLOUD_RADIUS));
    }

    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }
}
