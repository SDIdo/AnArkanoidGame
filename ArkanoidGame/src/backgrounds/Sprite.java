package backgrounds;
import biuoop.DrawSurface;
/**
 * Sprite - this interface sets all that is required from a sprite.
 * @author idonata 305727802
 *
 */
public interface Sprite {
    /**
     * This function draws the sprite on the screen.
     * @param d - the DrawSurface required to draw the sprite upon.
     */
    void drawOn(DrawSurface d);
    /**
     * Function notifies a sprite that time passed to call for a certain action.
     */
    void timePassed();
 }