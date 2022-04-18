/**
 * This class encapsulates an event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Wang ShaoFeng
 * @version CS2030S AY21/22 Semester 2
 */
class ShopEvent extends Event {
  

  protected Customer r1; 
  protected Counter r2; 

  protected int counterId = -1;
  protected int cusomterId; 
  protected int eventType;

  protected static final int ARRIVAL = 0;
  protected static final int SERVICE_BEGIN = 1;
  protected static final int SERVICE_END = 2;
  protected static final int DEPARTURE = 3;

  /**
   * Constructor for a shop event.
   *
   * @param eventType   The indicator for which type of 
   *                    event
   *
   * @param Customer r1 Customer Object 
   *                    
   * @param counterId   The id of the counter associated with
   *                    this event.
   *
   * @param Counter r2  Counter Object.
   */
  public ShopEvent(int eventType, int counterId, Customer r1, Counter r2) {
    this(eventType, r1);
    this.r1 = r1; 
    this.r2 = r2;
    this.eventType = eventType; 
    this.counterId = counterId;
  }

 /**
    * Constructor for a shop event.
     *
     * @param eventType   The indicator for which type of
     *                    event
     *
     * @param Customer r1 Customer Object
     *
     *
     * @param Counter r2  Counter Object.
     */

  public ShopEvent(int eventType, Customer r1, Counter r2){
      this(eventType,r1); 
      this.r1 = r1;
      this.r2 = r2;
      this.counterId = r2.find_counter();
  }

   /**
     * Constructor for a shop event.
     *
     * @param eventType   The indicator for which type of
     *                    event
     *
     * @param Customer r1 Customer Object
 */

  public ShopEvent(int eventType, Customer r1){
       super(r1.arrivalTime);
       this.r1 = r1;
       this.eventType = eventType; 
  }


  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = "";
    if (this.eventType == ShopEvent.ARRIVAL) {
      str = String.format(": Customer %d arrives", r1.CustomerId);
    } else if (this.eventType == ShopEvent.SERVICE_BEGIN) {
      str = String.format(": Customer %d service begin (by Counter %d)", 
          r1.CustomerId,this.counterId);
    } else if (this.eventType == ShopEvent.SERVICE_END) {
      str = String.format(": Customer %d service done (by Counter %d)", 
          r1.CustomerId, this.counterId);
    } else if (this.eventType == ShopEvent.DEPARTURE) {
      str = String.format(": Customer %d departed",r1.CustomerId);
    }
    return super.toString() + str;
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    if (this.eventType == ShopEvent.ARRIVAL) {
      // The current event is an arrival event.  
      // Fnd the first available counter.
      //Arrival_departEvent a1 = new Arrival_departEvent(eventType,r1); 
      this.counterId = r2.find_counter();

      if (this.counterId == -1) {
        // If no such counter can be found, the customer
        // should depart.
        return new Event[] { 
           new Arrival_departEvent(ShopEvent.DEPARTURE,r1)
        };
      } else {
        // Else, the customer should go the the first 
        // available counter and get served.
        
        return new Event[] { 
          new ServiceBeginEvent(r1,r2) 
        };
        
      }
    } else if (this.eventType == ShopEvent.SERVICE_BEGIN) {
      // The current event is a service-begin event.  
      // Mark the counter is unavailable, then schedule
      // a service-end event at the current time + service time.
      r2.close_counter(this.counterId); 
      double endTime = this.getTime() + r1.serviceTime;
      r1.arrivalTime = endTime; 
      
      return new Event[] { 
        new ServiceEndEvent(counterId, r1, r2)    
      };

      

    } else if (this.eventType == ShopEvent.SERVICE_END) {
      // The current event is a service-end event.  
      // Mark the counter is available, then schedule
      // a departure event at the current time.
      r2.open_counter(this.counterId);
      return new Event[] { 
        new Arrival_departEvent(ShopEvent.DEPARTURE, r1)
      };
    } else if (this.eventType == ShopEvent.DEPARTURE) {
      // do nothing
    } else {
      // shouldn't reach here.
    }
    return new Event[] {};
  }
}
