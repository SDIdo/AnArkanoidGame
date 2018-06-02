package indicators;
import java.awt.Color;

import levels.GameLevel;
import backgrounds.Sprite;
import biuoop.DrawSurface;

/**
 * Shows score on the screen.
 * @author idonata 3057272802
 *
 */
public class ScoreIndicator implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    private static final int FONT_SIZE = 25;
    /*
     * MEMBERS
     */
    private GameLevel g;
    /**
     *Constructor of ScoreIndicator.
     * @param gameLevel - the main properties of any level in our game.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        this.g = gameLevel;
    }
    /**
     * A function that draws the score with regards to its value and size.
     *
     * @param d
     *            - a desired drawing utility instance
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(275, 22, "Score: " + Integer.toString(g.getScore().getValue()), FONT_SIZE);
    }

    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }

}
