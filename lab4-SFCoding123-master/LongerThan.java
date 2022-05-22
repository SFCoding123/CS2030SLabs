/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Wang ShaoFeng(Lab Group)
 */ 

public class LongerThan implements BooleanCondition<String> {

  private int length;

  public LongerThan(int length) {
    this.length = length; 
  }

  @Override 
  public boolean test(String str) {
    return str.length()>length; 
  }
}


