  /**
  * This class contains simulate a counter datebase 
  * with methods and data store an event in the shop
  * 
  * @author Wang ShaoFeng 
  * @version CS2030S AY21/22 Semester 2
 */

class Counter{

    private boolean[] available_counters;
    private int numberOfCounters;  
    public int counterId; 

    public Counter(int numberOfCounters){

        this.numberOfCounters = numberOfCounters;
        init_counter(); 

    } //default constructor of opening counters 

     

    public void init_counter(){


        available_counters = new boolean[numberOfCounters];     
        for (int i = 0; i < numberOfCounters; i++) {
            available_counters[i] = true;
        }
    }//method to init counters 

    public int find_counter(){

        int x = -1; 

        for(int i = 0; i< available_counters.length; i++){
            if(available_counters[i]){
                x = i;
                this.counterId = x;
                break; 

            }

        } 

        return x; 

    }// method to find the empty working counter

    public void open_counter(int counterId){

        this.available_counters[counterId]=true;

    } 

    public void close_counter(int counterId){

        this.available_counters[counterId]=false; 

    }







}
