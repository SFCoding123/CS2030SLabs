/**
 * Counter class 
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
*/
class Counter {

  private int counterId; 
  private boolean available; 


  public Counter(int counterId) {

    this.counterId = counterId;
    this.available = true; 

  } //default constructor of opening counters 



  public void init_counter() {

    this.available = true; 
  }

  public void close_counter() {

    this.available = false; 
  }

  public boolean counter_status() {
    return this.available; 
  }

  public int get_counterId() {
    return counterId; 
  }

  @Override 
  public String toString() {
    String str = String.format("(by S%d)", counterId); 
    return str; 
  }


}
