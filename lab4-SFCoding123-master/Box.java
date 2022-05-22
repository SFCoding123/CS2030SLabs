/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Wang ShaoFeng (Lab Group)
 */

class Box<T> {

  private final T t;
  private final static Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T t) {
    this.t = t; 
  }

  public static <T> Box<T> of(T t) {

    if(t == null) {

      return null;

    } else { 
      return new Box<T>(t); 
    }
  }

  public static <T> Box<T> ofNullable(T t) {

    if(t==null) {
      return empty();
    } else {
      return new Box<T>(t);
    }
  }

  public static <T> Box<T> empty() {

    @SuppressWarnings("unchecked")
    Box<T> box = (Box<T>)EMPTY_BOX;
    return box;
  }

  public boolean isPresent() {
    return !(t==null);  
  }

  public Box<T> filter(BooleanCondition<? super T> CC) {

    if(this.isPresent()) { 
      if(CC.test(this.t)) {
        return this; 
      } else {
        return Box.empty();
      } 
    } else {
      return this;
    }
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U > TF) {

    if(!(this.isPresent())) {
      return empty();
    } else {
      return ofNullable(TF.transform(this.t)); 
    }

  }


  @Override 
  public String toString() {
    if(t==null) {
      return "[]";
    } else {
      return "[" + t + "]";
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } 

    if (obj instanceof Box<?>) {
      Box<?> b1 = (Box<?>) obj; 

      if (this.t == b1.t) {
        return true; 
      } 
      if (this.t ==null || b1.t == null) {
        return false; 
      }
      return this.t.equals(b1.t); 
    }
    return false; 
  }


}
