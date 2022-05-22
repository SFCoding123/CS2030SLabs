package cs2030s.fp;

/**
 * A class to allow Lazy evalution using Producer and Maybe objects.
 * @author Wang ShaoFeng 
 * @version CS2030S AY21/22 Sem 
 *
 */
public class Lazy<T> {

  /** Private fields of Producer and Maybe with default values.*/
  private  Producer<? extends T> producer = () -> null;
  private  Maybe<T> value = Maybe.of(null); 

  /** 
   * A private constructor to initialize with given value.
   * @param v (type T)
   */
  private Lazy(T v) {
    Maybe<T> value = Maybe.some(v);
    this.value = value;
  }

  /** 
   * A private constructor to initialize with given producer.
   * @param s (type Producer)
   */
  private Lazy(Producer<? extends T> s) {  
    this.producer = s;
  }

  /** 
   * A class factory method that create and return new Lazy object.
   * @param <T> type of value
   * @param v the value to describe and it can be null 
   * @return a Lazy Object with v 
   */
  public static <T> Lazy<T> of(T v) {
    Lazy<T> init = new Lazy<>(v);
    return init; 
  } 


  /** 
   * A class factory method that create and return new Lazy object.
   * @param <T> type of value
   * @param s the value to describe, and must be type Producer, or nullary expression. 
   * @return a Lazy Object with the the producer present
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    Lazy<T> pro = new Lazy<>(s); 
    return pro; 
  } 


  /** 
   * An instance method that return value if present, otherwise producer get evaluated.
   * @return the value described by this Lazy 
   */
  public T get() { 
    T  t  = value.orElseGet(producer);
    this.value = Maybe.some(t); 
    return t; 
  }

  /** An instance method that return a delayed map function to the value.
   * @param <U> the type of value of Lazy returned by the mapping function
   * @param tf  the mapping function of Transformer type 
   * @return an Lazy Object containing result of applying function to the value
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> tf) {
    Producer<U> p1 = () -> tf.transform(get()); 
    return Lazy.of(p1); 
  } 
 
  /**
   * An instance method that return a delayed filter function to the value.
   * @param cc the filter function of BooleanCondition type
   * @return an Lazy Object containing result of applying filter to the value 
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> cc) {
    Producer<Boolean> p1 = () -> cc.test(get());
    return Lazy.of(p1); 
  }

  /** An instance method that used when value is Lazy type and it will
   * return a delayed map function to the value without double boxing.  
   * @param <U> the type of value of Lazy returned by the mapping function
   * @param tf  the mapping function of Transformer type 
   * @return an Lazy Object containing result of applying function to the value
   */ 
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> tf) { 
    Producer<U> p1 = () -> tf.transform(get()).get();
    return Lazy.of(p1); 
  }


  /**
   * An instance method that used to combine the Lazy type objects in the given boundaries.
   * @param <S> the input type of Lazy object 
   * @param <R> the output type of Lazy after applying combine function
   * @param ls the Lazy object containing the value
   * @param com the combine function of type Combiner  
   * @return an Lazy Object containing the result of applying combine to Lazy Objects
   */
  public <S, R> Lazy<R> combine(Lazy<S> ls, Combiner<? super T, ? super S, ? extends R> com) {
    Producer<R> p1 = () -> com.combine(get(), ls.get());
    return Lazy.of(p1);
  }
 

  /**
   * An instance method to visualise value in Lazy which suitable for troubleshooting.
   * @return the string representation of this instance
   */
  @Override 
  public String toString() {
    return this.value.map((input) -> String.valueOf(input)).orElse("?"); 
  }

  /**
   * An instance method to check for equivalence of Lazy objects.
   * Equivalence define as it is a Lazy type and present values are equals to each other
   * @return true if they are equals and otherwise false
   */  
  @Override 
  public boolean equals(Object obj) {
    if (obj == this) {
      return true; 
    } 
    if (obj instanceof Lazy<?>) {
      Lazy<?> s1 = (Lazy<?>) obj;
      if (this.get() == s1.get()) {
        return true; 
      } 
      if (this.get() == null || s1.get() == null) {
        return false; 
      } 
      return this.get().equals(s1.get());
    } 
    return false; 
  }

}
