/**
 * Counter class 
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */
class Counter implements Comparable<Counter> {

  private int counterId; 
  private boolean available; 
  private Queue<Customer> counterQue; 

  public Counter(int counterId, int maxQuelength) {

    this.counterId = counterId;
    this.available = true; 
    initCounterQue(maxQuelength); 

  } //default constructor of opening counters 



  public void initCounter() {

    this.available = true; 
  }

  public void closeCounter() {

    this.available = false; 
  }

  public boolean counterStatus() {
    return this.available; 
  }

  private void initCounterQue(int maxQuelength) {
    this.counterQue = new Queue<Customer>(maxQuelength);  
  }

  public void startQue(Customer customer) {
    this.counterQue.enq(customer); 
  }

  public Customer quitQue() {
    return this.counterQue.deq(); 
  }

  public int currentQuelength() {
    return counterQue.length();  
  }

  public boolean isFull() {
    return counterQue.isFull();
  }

  public boolean gotQue() {
    return !(counterQue.isEmpty());
  }

  public String displayQue() {
    return counterQue.toString(); 
  }


  @Override 
  public String toString() {
    String str = String.format("S%d", counterId); 
    return str; 
  } 

  @Override 
  public int compareTo(Counter c) {
    if (this.currentQuelength() > c.currentQuelength()) {
      return 1;
    } else if (this.currentQuelength() == c.currentQuelength()) {
      return 0;
    } else {
      return -1; 
    }

  }

}
