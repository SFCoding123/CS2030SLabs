   /**
    * This class encapsulates an ServiceEndEvent
    *
    * @author Wang ShaoFeng
    * @version CS2030S AY21/22 Semester 2
   */


class ServiceEndEvent extends ShopEvent{

    private Customer c1; 
    private Counter  c2;
    private int counterId; 

    public ServiceEndEvent(int counterId,Customer r1, Counter r2){
        super(2,counterId,r1,r2); 
        this.c1=r1;
        this.c2=r2; 
        this.counterId = r2.counterId; 
    }


}
