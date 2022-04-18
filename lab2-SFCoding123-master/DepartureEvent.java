/**
 * Departure event class  
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */

class DepartureEvent extends ShopEvent { 

  private Customer customer;
  private double exitTime; 

  public DepartureEvent(Customer customer, double exitTime) {
    super(exitTime); 
    this.customer = customer; 
    this.exitTime = exitTime;
  }

  @Override 
  public String toString() {
    String str = String.format(" departed");
    return super.toString() + this.customer.toString() + str;

  }

  @Override
  public Event[] simulate() {

    return new Event[]{};

  }




}
