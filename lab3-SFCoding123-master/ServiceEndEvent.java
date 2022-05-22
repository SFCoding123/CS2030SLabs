/**
 * This class encapsulates an ServiceEndEvent
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */

class ServiceEndEvent extends ShopEvent {

  private Shop shop; 
  private Customer customer;
  private Counter counter; 
  private double endTime; 

  public ServiceEndEvent(Shop shop, Customer customer, Counter counter,
      double endTime) {
    super(endTime); 
    this.shop = shop;
    this.customer = customer;
    this.counter = counter; 
    this.endTime = endTime; 

  }

  private void endService() {
    this.counter.initCounter(); 
  }


  @Override
  public String toString() {
    String str = String.format(" service done ");
    return super.toString() + " " + this.customer.toString() +
      str + "(by " + this.counter.toString() + " " + this.counter.displayQue() + ")"; 
  }

  @Override
  public Event[] simulate() {

    endService();
    if (this.counter.gotQue()) {
      if (this.shop.gotQue()) {
        return new Event[] {
          new DepartureEvent(this.customer, this.endTime),
          new ServiceBeginEvent(this.shop, this.counter.quitQue(), 
                  this.counter, this.endTime),
          new JoinCounterqueEvent(this.shop.quitQue(), 
                  this.endTime, this.counter)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.customer, this.endTime),
          new ServiceBeginEvent(this.shop, this.counter.quitQue(),
                  this.counter, this.endTime)
        };
      }
    } else {
      if (this.shop.gotQue()) { 
        return new Event[]{
          new DepartureEvent(this.customer, this.endTime), 
          new ServiceBeginEvent(this.shop, this.shop.quitQue(), this.counter, this.endTime)
        };
      } else {
        return new Event[]{ 
          new DepartureEvent(this.customer, this.endTime) 
        };
      }
    }
  }

}
