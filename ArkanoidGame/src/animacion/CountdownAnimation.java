package animacion;
import java.awt.Color;

import backgrounds.SpriteCollection;
import biuoop.DrawSurface;
/**
 * The CountdownAnimation will display the given gameScreen for k seconds.
 * On top of them it will show a countdown from countFrom back to 1,
 * where each number will appear on the screen for (numOfSeconds / countFrom)
 * secods, before it is replaced with the next one.
 *
 * @author idonata 305727802
 *
 */
public class CountdownAnimation implements Animation {
    /*
     * MAGIC NUMBERS
     */
    private static final int TEXT_SIZE = 70;
    /*
     * MEMBERS
     */
    private int countStartsFrom;
    private SpriteCollection screen;
    private boolean stop;
    private double start;
    private double seconds;
    /**
     * CountdownAnimation constructor.
     * @param numOfSeconds - seconds desired untill next frame.
     * @param countFrom - counters start value.
     * @param gameScreen - a collection of sprites representing current game situation.
     */
   public CountdownAnimation(double numOfSeconds,
                             int countFrom,
                             SpriteCollection gameScreen) {
       this.countStartsFrom = countFrom;
       this.screen = gameScreen;
       this.seconds = numOfSeconds * 1000 / countFrom;
       this.start = System.currentTimeMillis();
   }
   /**
    * One frame of animation.
    * @param d - the draw surface upon which the animation is implemented.
    */
   public void doOneFrame(DrawSurface d) {
       screen.drawAllOn(d);
       if (this.countStartsFrom <= 0) {
           this.stop = true;
       }
       if (System.currentTimeMillis() < this.start + this.seconds) { //Exactly 2 seconds.
           d.setColor(new Color(141, 66, 123));
           d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countStartsFrom), TEXT_SIZE);
       } else {
           this.countStartsFrom--;
           this.start = System.currentTimeMillis();
       }
   }
   /**
    * Function checks whether animation should stop.
    * @return - true or false according to need.
    */
   public boolean shouldStop() {
       return this.stop;
   }
}
