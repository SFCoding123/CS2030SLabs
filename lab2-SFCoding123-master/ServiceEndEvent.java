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

  public void endService() {
    this.counter.init_counter(); 
  }


  @Override
  public String toString() {
    String str = String.format(" service done ");
    return super.toString() + this.customer.toString() +
      str + this.counter.toString(); 
  }

  @Override
  public Event[] simulate() {

    endService();
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
