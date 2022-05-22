/**
 * This class implements a shop simulation.
 *
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
*/

class JoinCounterqueEvent extends ShopEvent {

  private Customer customer; 
  private double startTime; 
  private Counter counter; 

  public JoinCounterqueEvent(Customer customer, 
      double startTime, Counter counter) {
    super(startTime);
    this.customer = customer; 
    this.startTime = startTime; 
    this.counter = counter; 
  }

  @Override
  public String toString() {
    String str = String.format("joined counter queue");
    return super.toString() + " " + this.customer.toString() + " " + str 
      + " (at " + this.counter.toString() + " " + this.counter.displayQue() + ")";
  }

  @Override
  public Event[] simulate() {

    this.counter.startQue(this.customer);
    return new Event[]{};


  }
}
