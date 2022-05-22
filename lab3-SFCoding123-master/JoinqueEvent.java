/*
 *  Join Queue Event class  
 *  @author Wang ShaoFeng
 *  @version CS2030S AY21/22 Semester 2
*/

class JoinqueEvent extends ShopEvent {

  private Shop shop;
  private Customer customer;
  private double startTime; 

  public JoinqueEvent(Shop shop, Customer customer, double startTime) {
    super(startTime);
    this.shop = shop;
    this.customer = customer; 
    this.startTime = startTime; 
  }


  @Override 
  public String toString() {
    String str = String.format(" joined shop queue " + shop.displayQue());
    return super.toString() + " " + this.customer.toString() + str; 
  }

  @Override 
  public Event[] simulate() {

    this.shop.startQue(this.customer); 
    return new Event[]{};


  }



}
