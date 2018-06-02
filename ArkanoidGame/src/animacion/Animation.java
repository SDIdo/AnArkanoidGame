package animacion;

import biuoop.DrawSurface;
/**
 * Interface of animation objects.
 * @author idonata 305727802
 *
 */
public interface Animation {
    /**
     * Function runs one frame of animation.
     * @param d - the draw surface upon which animation is to be.
     */
   void doOneFrame(DrawSurface d);
   /**
    * Function checks whether to cut the scene.
    * @return - true or false.
    */
   boolean shouldStop();
}
