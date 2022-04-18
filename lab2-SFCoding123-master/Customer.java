/**
 * This class store information about a customer who arrives at Shop 
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */
class Customer {

  private int customerId; 
  private double serviceTime; 
  private double arrivalTime;  

  public Customer(int id, double arrivalTime, double serviceTime) {
    this.customerId = id; 
    this.arrivalTime = arrivalTime; 
    this.serviceTime = serviceTime; 
  }

  public double endTime(double afterQue) {
    return afterQue + serviceTime;
  }

  public int displayId() {
    return customerId;
  }

  public String displayString() {
    return "C" + customerId; 
  }

  @Override 
  public String toString() {
    String str = String.format(" C%d", customerId);
    return str;

  } 



}


