package indicators;
import java.awt.Color;

import backgrounds.Sprite;
import biuoop.DrawSurface;
/**
 * Indicator of level's name implements a sprite.
 * @author idonata 305727802
 *
 */
public class LevelNameIndicator implements Sprite {
    /*
     * MAGIC NUMBERS
     */
    private static final int FONT_SIZE = 25;
    /*
     * MEMBERS
     */
    private String levelName;
    /**
     * Constructor of levelNameIndicator.
     * @param newName - level's name.
     */
    public LevelNameIndicator(String newName) {
        this.levelName = newName;
    }
    /**
     * A function that draws the level name with regards to its color and size.
     *
     * @param d
     *            - a desired drawing utility instance
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(450, 22, "Level Name: " + this.levelName, FONT_SIZE);
    }
    /**
     * tells that time has passed.
     */
    public void timePassed() {
    }
}
