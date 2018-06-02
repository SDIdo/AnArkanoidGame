package mainfiles;
import indicators.Counter;

import java.util.List;

import levels.GameLevel;
import levels.LevelInformation;
import animacion.AnimationRunner;
import animacion.GameOverScreen;
import animacion.YouWinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
 * Responsible to move between situations in the game.
 * @author idonata 305727802
 *
 */
public class GameFlow {
    /*
     * MAGIC NUMBERS
     */
    public static final int INIT_LIVES = 7;
    /*
     * MEMBERS
     */
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private int height;
    private Counter score = new Counter();
    private Counter lives = new Counter();
    private AnimationRunner runner;
    /**
     * Constructor of GameFlow.
     * @param ar - an animation runner.
     * @param ks - a keyboard to play with.
     * @param newGui - a graphical utility.
     * @param screenWidth - the width of the screen.
     * @param screenHeight - the height of the screen.
     */
   public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI newGui, int screenWidth, int screenHeight) {
       this.keyboardSensor = ks;
       this.animationRunner = ar;
       this.gui = newGui;
       this.height = screenHeight;
       }
   /**
    * Main function of this class - implements going through a level.
    * @param levels - a list of levels the game goes about.
    */
   public void runLevels(List<LevelInformation> levels) {
       this.runner = new AnimationRunner(gui);
       this.lives.increase(INIT_LIVES);
      for (LevelInformation levelInfo : levels) {
         GameLevel level = new GameLevel(levelInfo, this.gui, this.keyboardSensor, this.height, this.animationRunner,
                 this.score, this.lives);

         level.initialize();
         while (level.getBlocksToRemove().getValue() > 0 && level.getLives().getValue() >= 0) {
             level.playOneTurn();
         }
         if (level.getLives().getValue() < 0) {
             this.runner.run(new GameOverScreen(this.keyboardSensor, this.score.getValue()));
            return;
         }
      }
      this.runner.run(new YouWinScreen(this.keyboardSensor, this.score.getValue()));
      return;
   }
}