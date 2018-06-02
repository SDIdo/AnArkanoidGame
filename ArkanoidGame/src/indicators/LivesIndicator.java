package indicators;
import java.awt.Color;

import levels.GameLevel;
import backgrounds.Sprite;
import biuoop.DrawSurface;
/**
 * Indicates how many lives left acts as a sprite.
 * @author idonata 30572780
 *
 */
public class LivesIndicator implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    private static final int FONT_SIZE = 25;
    /*
     * MEMBERS
     */
    private GameLevel g;
    /**
     * Constructor of LivesIndicator.
     * @param gameLevel - the main properties of a game level.
     */
    public LivesIndicator(GameLevel gameLevel) {
        this.g = gameLevel;
    }
    /**
     * A function that draws the indicator with regards to its color and size.
     *
     * @param d
     *            - a desired drawing utility instance
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(90, 22, "Lives: " + Integer.toString(g.getLives().getValue()), FONT_SIZE);
    }

    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }

}
