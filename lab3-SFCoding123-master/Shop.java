/**
 * Shop class handles datas of a shop
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */
class Shop {

  private  Array<Counter> counters;
  private  int counterId;
  private  Queue<Customer> shopQue;  

  public Shop(int numOfCounters, int maxCounterLength, Queue<Customer> shopQue) {

    initCounters(numOfCounters, maxCounterLength);
    this.shopQue = shopQue; 
  } // opening counters 


  public Counter findCounter() {

    for (int x = 0; x < counters.length(); x++) {
      if (this.counters.get(x).counterStatus()) {              
        this.counterId = x; 
        return this.counters.get(x);  
      }
    } 
    return null; 
  }


  public boolean counterAllFull() {

    boolean full = true;  
    for (int count = 0; count < counters.length(); count++) {
      full = full && counters.get(count).isFull(); 
    }
    return full; 
  }

  public void initCounters(int numberOfCounters, int maxCounterLength) {

    this.counters = new Array<Counter>(numberOfCounters); 
    for (int count = 0; count < numberOfCounters; count++) {
      Counter counter = new Counter(count, maxCounterLength);
      this.counters.set(count, counter); 
    }

  }


  public Counter minQue() {

    Counter counterq = counters.get(0); 
    for (int x = 1; x < counters.length(); x++) {
      if (counterq.compareTo(counters.get(x)) == 1) {
        counterq = counters.get(x);
      }
    }
    return counterq; 
  }



  public boolean gotQue() {
    return !(this.shopQue.isEmpty());  
  }

  public boolean fullQue() {
    return this.shopQue.isFull();
  }

  public void startQue(Customer customer) {
    shopQue.enq(customer); 
  }

  public Customer quitQue() {
    return (Customer) shopQue.deq(); 
  }

  public String displayQue() {
    return shopQue.toString(); 
  }



}
