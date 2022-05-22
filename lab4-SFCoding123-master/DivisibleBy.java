/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Wang ShaoFeng (Lab Group)
 */ 

public class DivisibleBy implements BooleanCondition<Integer> {

  private int input;

  public DivisibleBy (int input) {
    this.input = input; 
  }

  @Override
  public boolean test(Integer I) {

    if(I.remainderUnsigned(I,input)==0) {
      return true; 
    } else {
      return false;
    }

  }
}


