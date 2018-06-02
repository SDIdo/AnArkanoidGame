package mainfiles;
import java.util.ArrayList;
import java.util.List;
import levels.DirectHit;
import levels.FinalFour;
import levels.GreenThree;
import levels.LevelInformation;
import levels.WideEasy;
import animacion.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Ass3Game - This class is responsible to run and move between levels.
 * @author idonata 305727802
 *
 */
public class Ass5Game {
    /*
     * MAGIC NUMBERS
     */
    public static final String GAME_NAME = "Arkanoid";
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
/**
 * Main - runs the arkanoid gameLevel.
 * @param args - none
 */
    public static void main(String[] args) {
        GUI gui = new GUI(GAME_NAME, SCREEN_WIDTH, SCREEN_HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        if (args.length == 0) {
            levels.add(new DirectHit(SCREEN_WIDTH, SCREEN_HEIGHT));
            levels.add(new WideEasy(SCREEN_WIDTH, SCREEN_HEIGHT));
            levels.add(new GreenThree(SCREEN_WIDTH, SCREEN_HEIGHT));
            levels.add(new FinalFour(SCREEN_WIDTH, SCREEN_HEIGHT));
        } else {
            for (int i = 0; i < args.length; i++) {
                String reader = args[i].toString();
                if (reader.equals("1")) {
                    levels.add(new DirectHit(SCREEN_WIDTH, SCREEN_HEIGHT));
                }
                if (reader.equals("2")) {
                    levels.add(new WideEasy(SCREEN_WIDTH, SCREEN_HEIGHT));
                }
                if (reader.equals("3")) {
                    levels.add(new GreenThree(SCREEN_WIDTH, SCREEN_HEIGHT));
                }
                if (reader.equals("4")) {
                    levels.add(new FinalFour(SCREEN_WIDTH, SCREEN_HEIGHT));
                }
            }
        }
        GameFlow gf = new GameFlow(animationRunner, keyboard, gui, SCREEN_WIDTH, SCREEN_HEIGHT);
        gf.runLevels(levels);
        gui.close();
    }
}
