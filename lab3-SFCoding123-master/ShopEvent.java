/**
 * ShopEvent abstract class
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
*/

abstract class ShopEvent extends Event {


  public ShopEvent(double eventTime) {
    super(eventTime);
  }


  @Override
  public String toString() {
    return super.toString() + ":"; 
  }


  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
