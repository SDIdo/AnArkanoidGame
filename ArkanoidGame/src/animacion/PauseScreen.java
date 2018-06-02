package animacion;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * How to release a pause and the pause message.
 * @author idonata 30572780
 *
 */
public class PauseScreen implements Animation {
    /*
     * MAGIC NUMBERS
     */
    private static final String PAUSE_MESSAGE = "Paused";
    private static final String PRESS_SPACE = "Press Space To Continue...";
    private static final int FONT_SIZE = 40;
    private static final int SPACE = 100;
    /*
     * MEMBERS
     */
   private KeyboardSensor keyboard;
   private boolean stop;
   /**
    * Constructor of PauseScreen.
    * @param k - the key required to resume.
    */
   public PauseScreen(KeyboardSensor k) {
      this.keyboard = k;
      this.stop = false;
   }
   /**
    * A function that draws the message with regards to text and size.
    *
    * @param d
    *            - a desired drawing utility instance
    */
   public void doOneFrame(DrawSurface d) {
       d.setColor(Color.WHITE);
       d.fillRectangle(0, 0, 800, 600);
       d.setColor(Color.CYAN);
      d.drawText(d.getWidth() / 6, d.getHeight() / 2, PAUSE_MESSAGE, FONT_SIZE);
      d.setColor(Color.BLACK);
      d.drawText(d.getWidth() / 6 - 5, d.getHeight() / 2 - 5, PAUSE_MESSAGE, FONT_SIZE);
      d.setColor(Color.gray.darker());
      d.fillCircle(d.getWidth() / 2 + SPACE, d.getHeight() / 2 + 50, 50); //Symbol
      d.setColor(Color.cyan);
      d.fillCircle((d.getWidth() / 2) + 3 + SPACE, d.getHeight() / 2 + 53, 50);
      d.setColor(Color.gray); //LINES..
      d.fillRectangle((d.getWidth() / 2) - 10 + SPACE, d.getHeight() / 2 + 40, 10, 30);
//      d.setColor(Color.black);
      d.fillRectangle((d.getWidth() / 2) + 10 + SPACE, d.getHeight() / 2 + 40, 10, 30);
      d.setColor(Color.blue.darker()); //LINES..
      d.fillRectangle((d.getWidth() / 2) - 13 + SPACE, d.getHeight() / 2 + 37, 10, 30);
//      d.setColor(Color.cyan);
      d.fillRectangle((d.getWidth() / 2) + 7 + SPACE, d.getHeight() / 2 + 37, 10, 30);
      d.setColor(Color.CYAN);
     d.drawText(d.getWidth() / 6, 2 * (d.getHeight() / 3), PRESS_SPACE, FONT_SIZE / 2);
     d.setColor(Color.BLACK);
     d.drawText(d.getWidth() / 6 - 5, 2 * (d.getHeight() / 3) - 5, PRESS_SPACE, FONT_SIZE / 2);
      if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
          this.stop = true;
          }
   }
   /**
    * answer when to stop presenting this sprite.
    * @return - true or false accordingly.
    */
   public boolean shouldStop() {
       return this.stop;
       }
}