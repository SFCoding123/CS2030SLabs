 /**
    * This class encapsulates an arrival and departure event in the shop
    * @author Wang ShaoFeng
    * @version CS2030S AY21/22 Semester 2
  */

class Arrival_departEvent extends ShopEvent{


    private Customer c2;
    private int counts=0; 

    public Arrival_departEvent(int type, Customer c1){
        super(type,c1);
        this.c2 = c1; 
        this.counts++; 
     }   



}
