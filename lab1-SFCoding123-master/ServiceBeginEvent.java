   /**
    * This class encapsulates a Service Begin event 
    * @author Wang ShaoFeng
    * @version CS2030S AY21/22 Semester 2
    */
  class ServiceBeginEvent extends ShopEvent{

    private Customer c1;
    private Counter c2;  
    private int counter; 
    private final int type = 1;

    public ServiceBeginEvent(Customer r1, Counter r2){
        super(1,r1,r2);  
        this.c1=r1;
        this.c2=r2; 
        this.counter = super.counterId;
    }

}
