/**
 * This class encapsulates a Service Begin event 
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceBeginEvent extends ShopEvent {

  private Shop shop; 
  private Customer customer; 
  private Counter counter; 

  public ServiceBeginEvent(Shop shop, Customer customer, Counter counter, double arrivalTime) {
    super(arrivalTime); 
    this.shop = shop; 
    this.customer = customer; 
    this.counter = counter; 
  }

  private void startService() {
    this.counter.closeCounter();
  }

  @Override 
  public String toString() {
    String str = String.format(" service begin ");
    return super.toString() + " " + this.customer.toString()
      + str + "(by " + this.counter.toString() + " " 
      + this.counter.displayQue() + ")"; 
  }

  @Override 
  public Event[] simulate() {
    startService();
    return new Event[] {
      new ServiceEndEvent(this.shop, this.customer, this.counter, 
          this.customer.endTime(this.getTime()))
    };
  }

}
