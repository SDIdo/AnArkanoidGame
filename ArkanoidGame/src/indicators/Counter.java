package indicators;
/**
 * A simple counter you can increase or decrease.
 * @author idonata 305727802
 *
 */
public class Counter {
    /*
     * MEMBERS
     */
    private int num;
    /**
     * add number to current count.
     * @param number - the value with which to increase the counter.
     */
   public void increase(int number) {
       this.num += number;
   }
   /**
    * subtract number from current count.
    * @param number - the value with which to decrease the counter.
    */
   public void decrease(int number) {
       this.num -= number;
   }
   /**
    * get current count.
    * @return - the current count.
    */
   public int getValue() {
       return this.num;
   }
}
