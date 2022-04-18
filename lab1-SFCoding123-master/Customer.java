  /**
    * This class store information about a customer who arrived at the shop 
    *
    * @author Wang ShaoFeng
    * @version CS2030S AY21/22 Semester 2
   */
class Customer {
   
    public int CustomerId; 
    public double serviceTime; 
    public double arrivalTime;  

    public Customer(int id, double arrivalTime,double serviceTime){
        this.CustomerId = id; 
        this.arrivalTime = arrivalTime; 
        this.serviceTime = serviceTime; 
    }//Constructor for Customer in shop 

    public Customer(int id, double arrivalTime){
        this.CustomerId = id; 
        this.arrivalTime = arrivalTime;
    }//Constructor for Customer arrival
    
    

}


