package backgrounds;

import java.util.List;
import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 * This class implements a sprite collection.
 * @author idonata 305727802
 *
 */
public class SpriteCollection {
    /*
     * MEMBERS
     */
    private List<Sprite> collection;
    /**
     * Constructor of SpriteCollection.
     */
    public SpriteCollection() {
        this.collection = new ArrayList<Sprite>();
    }
    /**
     * Function adds a sprite to the collection.
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }
    /**
     * Function removes a sprite from the collection.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }
    /**
     * Function calls time passed on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int j = 0; j < this.collection.size(); j++) {
            this.collection.get(j).timePassed();
        }
    }
    /**
     * Function calls drawOn of all the sprites desired.
     * @param d - the DrawSurface desired to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int j = 0; j < this.collection.size(); j++) {
            this.collection.get(j).drawOn(d);
        }
    }
}

