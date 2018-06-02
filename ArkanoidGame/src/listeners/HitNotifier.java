package listeners;
/**
 * Rules what every hit notifier must do.
 * @author idonata 305727802
 *
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the desired hit listener to be added.
     */
   void addHitListener(HitListener hl);
   /**
    * Remove hl from the list of listeners to hit events.
    * @param hl - the desired hit listener to be removed.
    */
   void removeHitListener(HitListener hl);
}
