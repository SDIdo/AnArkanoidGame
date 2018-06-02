package animacion;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * How to release a pause and the pause message.
 * @author idonata 30572780
 *
 */
public class YouWinScreen implements Animation {
    /*
     * MAGIC NUMBERS
     */
    private static final String MESSAGE = "You Win! Your score is ";
    private static final String PRESS_SPACE = "Press Space To Continue...";
    private static final int FONT_SIZE = 40;
    /*
     * MEMBERS
     */
   private KeyboardSensor keyboard;
   private boolean stop;
   private int score;
   /**
    * Constructor of YouWinScreen.
    * @param k - the key required to resume.
    * @param endingScore - the score with which player came to the screen.
    */
   public YouWinScreen(KeyboardSensor k, int endingScore) {
      this.keyboard = k;
      this.stop = false;
      this.score = endingScore;
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
       d.setColor(Color.ORANGE);
       for (int i = d.getHeight(); i > d.getHeight() / 13; i--) {
           d.fillRectangle(0, 10 * i, 800, 5);
       }
       for (int i = d.getWidth(); i > d.getWidth() / 13; i--) {
           d.fillRectangle(10 * i, 0, 5, 600);
       }
       d.setColor(Color.GRAY.brighter());
       d.drawCircle(150, 150, 60);
       d.drawCircle(150, 150, 30);
       d.drawCircle(150, 150, 10);
       d.setColor(Color.GREEN.darker());
       d.fillCircle(150, 150, 8);
       d.setColor(Color.GREEN.darker());
       d.drawText(d.getWidth() / 6, d.getHeight() / 2, MESSAGE + score, FONT_SIZE);

       d.drawText(d.getWidth() / 6, 2 * (d.getHeight() / 3), PRESS_SPACE, FONT_SIZE / 2);
       d.setColor(Color.GREEN);
       d.drawText(d.getWidth() / 6 + 3, 2 * (d.getHeight() / 3) + 3, PRESS_SPACE, FONT_SIZE / 2);
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
