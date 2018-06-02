package animacion;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Class of animation runners.
 * @author idonata 305727802
 *
 */
public class AnimationRunner {
    /*
     * MEMBERS
     */
   private GUI gui;
   private int framesPerSecond = 60;
   private Sleeper sleeper = new Sleeper();
   /**
    * Constructor of AnimationRunner.
    * @param newGui - the graphical user interface upon to run the animation.
    */
   public AnimationRunner(GUI newGui) {
       this.gui = newGui;
   }
   /**
    * 2nd Constructor of AnimationRunner.
    */
   public AnimationRunner() {
   }
   /**
    * Function runs a desired animation.
    * @param animation - the animation to run.
    */
   public void run(Animation animation) {
      int millisecondsPerFrame = 1000 / framesPerSecond;
      while (!animation.shouldStop()) { //each implementor of animation shouldStop flag
         long startTime = System.currentTimeMillis(); // timing
         DrawSurface d = gui.getDrawSurface();
         animation.doOneFrame(d); // Each implementor of animation will do it's frame.
         gui.show(d);
         long usedTime = System.currentTimeMillis() - startTime;
         long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
         if (milliSecondLeftToSleep > 0) {
             this.sleeper.sleepFor(milliSecondLeftToSleep);
         }
      }
   }
}