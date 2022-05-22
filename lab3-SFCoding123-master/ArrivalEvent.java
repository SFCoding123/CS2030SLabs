/**Arrival Event class  
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */

class ArrivalEvent extends ShopEvent {

  private Shop shop;
  private Customer customer; 
  private double arrivalTime;

  public ArrivalEvent(Shop shop, Customer customer,
      double arrivalTime) {

    super(arrivalTime);
    this.shop = shop; 
    this.customer = customer;
    this.arrivalTime = arrivalTime;
  } 





  @Override 
  public String toString() { 
    String str = String.format(" arrived ");
    return super.toString() + " " + this.customer.toString() + str +
      this.shop.displayQue(); 
  } 

  @Override 
  public Event[] simulate() {

    if (this.shop.findCounter() != null) {
      Counter counter = this.shop.findCounter(); 
      return new Event[] {
        new ServiceBeginEvent(this.shop, this.customer, 
            counter, this.arrivalTime) 
      };

    } else {

      if (this.shop.counterAllFull()) {
        if (this.shop.fullQue()) {
          return new Event[] {
            new DepartureEvent(this.customer, this.arrivalTime)
          };
        } else {
          return new Event[] {
            new JoinqueEvent(this.shop, this.customer, this.arrivalTime)
          };
        }


      } else {
        Counter counter = shop.minQue(); 
        return new Event[] {
          new JoinCounterqueEvent(this.customer, this.arrivalTime,
              counter)
        };
      }






    } 
  }
}
