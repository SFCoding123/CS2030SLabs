/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Wang ShaoFeng (Lab Group)
 */

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  
  private int digits; 

  public LastDigitsOfHashCode (int digits) {
    this.digits = digits; 
  } 


  @Override
  public Integer transform(Object obj) { 
    
    int codes = obj.hashCode();

    if(codes<0) {
      codes = -codes; 
    }

    int sum = 0;
    for(int x = 0; x < digits ; x++) {
      sum += (codes % 10)*Math.pow(10,x); 
      codes/=10;
    }
    return sum;
  }
  
}
