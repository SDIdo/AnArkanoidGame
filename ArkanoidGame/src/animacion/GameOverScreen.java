package animacion;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * How to release a pause and the pause message.
 *
 * @author idonata 30572780
 *
 */
public class GameOverScreen implements Animation {
    /*
     * MAGIC NUMBERS
     */
    private static final String MESSAGE = "Game Over. Your score is ";
    private static final String PRESS_SPACE = "Press Space To Continue...";
    private static final int FONT_SIZE = 40;
    private static final int BARS = 60;
    private static final int SPACE = 160;
    /*
     * MEMBERS
     */
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * Constructor of GameOverScreen.
     *
     * @param k
     *            - the key required to resume.
     * @param endingScore
     *            - the score with which player came to the screen.
     */
    public GameOverScreen(KeyboardSensor k, int endingScore) {
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
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.WHITE);
        for (int i = 0; i < d.getHeight() / BARS; i++) {
            d.fillRectangle(0, 10 * i, 800, 5);
        }
        for (int i = 0; i < d.getWidth() / BARS; i++) {
            d.fillRectangle(10 * i, 0, 5, 600);
        }
        d.fillOval(d.getWidth() - SPACE, 2 * (d.getHeight() / 3), 90, 100);
        d.setColor(Color.RED);
        d.drawLine(d.getWidth() - SPACE, 2 * (d.getHeight() / 3) + SPACE / 2,
                d.getWidth() - (SPACE / 2), 2 * (d.getHeight() / 3) + SPACE);

        d.drawLine(d.getWidth() / 2 + 2 * SPACE, 2 * (d.getHeight() / 3)
                + SPACE, d.getWidth() - 2 * SPACE, 2 * (d.getHeight() / 3)
                + SPACE / 2);
        d.setColor(Color.RED);
        d.fillRectangle(d.getWidth() - SPACE, 2 * (d.getHeight() / 3), 90, 50);
        d.setColor(Color.BLUE);
        d.fillCircle(d.getWidth() - 12 * (SPACE / 13), d.getHeight() - 12
                * (SPACE / 13), 3);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 6, d.getHeight() / 2, MESSAGE + score,
                FONT_SIZE);
        d.drawText(d.getWidth() / 6, 2 * (d.getHeight() / 3), PRESS_SPACE,
                FONT_SIZE / 2);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 6 + 3, 2 * (d.getHeight() / 3) + 3,
                PRESS_SPACE, FONT_SIZE / 2);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * answer when to stop presenting this sprite.
     *
     * @return - true or false accordingly.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}