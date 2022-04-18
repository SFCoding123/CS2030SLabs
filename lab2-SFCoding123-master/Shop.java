/**
  * Shop class handles datas of a shop
  * @author Wang ShaoFeng
  * @version CS2030S AY21/22 Semester 2
*/
class Shop {

  private  Counter[] counters;
  private  int counterId;
  private  Queue que; 

  public Shop(int numbers, Queue que) {

    initCounters(numbers);
    this.que = que; 
  } // opening counters 


  public Counter find_counter() {

    for (int x = 0; x < counters.length; x++) {
      if (this.counters[x].counter_status()) {              
        this.counterId = x; 
        return counters[x];  
      }
    }
    return null; 
  }

  public void service_done(int counterId) {
    this.counters[counterId].init_counter(); 
  }

  public void service_start(int counterId) {
    this.counters[counterId].close_counter();
  }

  public void initCounters(int numberOfCounters) {

    this.counters = new Counter[numberOfCounters]; 
    for (int count = 0; count < numberOfCounters; count++) {
      this.counters[count] = new Counter(count); 
    }

  }

  public boolean gotQue() {
    return !(this.que.isEmpty());  
  }

  public boolean fullQue() {
    return this.que.isFull();
  }

  public void startQue(Customer customer) {
    que.enq(customer); 
  }

  public Customer quitQue() {
    return (Customer) que.deq(); 
  }

  public String displayQue() {
    return que.toString(); 
  }
}
