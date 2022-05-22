package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException; 

/**
 * A class consists of infiniteList functions.
 * @author Wang ShaoFeng 
 * @version CS2030S AY21/22 Sem 
 *
 */
public class InfiniteList<T> {

  /** head and tail which build using Lazy and Maybe.*/
  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  /** class level field to represent an Empty list.*/
  private static final InfiniteList<?> SENTINEL = new Sentinel();

  /** default constructor setting head and tail to null.*/
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /** 
   * A Private constructor to initialize with given head and tail. 
   * @param head (type T)
   * @param tail (type Producer)
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /** 
   * A Private constructor to initialize with given head and tail. 
   * @param head (type Lazy)
   * @param tail (type Lazy)
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * A class method to generate a infinite list.
   * @param producer the value to describe, and must be type Producer.
   * @param <T> type of value.
   * @return A lazy generated infinite list.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer)));

  }

  /**
   * A class method to iterate a infinite list.
   * @param seed The element of the iteration, stating from first. 
   * @param next the value to describe, and must be type transformer.
   * @param <T> type of value.
   * @return A lazy iterated infinite list.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /** 
   * An instance method that get the head value.   
   * @return the first non-null element of infinite list. 
   */
  public T head() {
    return getMaybe(head).orElseGet(() -> getList(tail).head());
  }

  /** 
   * An instance method that get the tail value.   
   * @return the next infinite list. 
   */
  public InfiniteList<T> tail() {
    return getMaybe(head).map(x -> getList(tail)).orElseGet(() -> getList(tail).tail()); 
  }


  /**
   * An instance method that get Maybe from head.
   * @param lazy the value to describe, and must be same type as head. 
   * @return a Maybe type Object.
   */
  public Maybe<T> getMaybe(Lazy<Maybe<T>> lazy) {
    return lazy.get();
  } 

  /**
   * An instance method that get infinite list from tail. 
   * @param lazy the value to describe, and must be same type as tail. 
   * @return a InfiniteList type Object.
   */
  public InfiniteList<T> getList(Lazy<InfiniteList<T>> lazy) {
    return lazy.get();
  }

  /** An instance method that return a delayed map function to the infinite list.
   * @param <R> the type of value of infinite list returned by the mapping function.
   * @param mapper  the mapping function of Transformer type.
   * @return an infinite list containing result of applying function to the elements.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail().map(mapper)));
  }

  /**
   * An instance method that return a delayed filter function to the infinite list.
   * @param predicate the filter function of BooleanCondition type.
   * @return an infinite list containing result of applying filter to the elements. 
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(this.head.map(x -> x.filter(predicate)),
        this.tail.map(x -> x.filter(predicate)));
  }

  /**
   * This is a nested Sentinel class, which is the child of InfiniteList.
   */
  private static class Sentinel extends InfiniteList<Object> {

    /**
     * Private constructor for an Sentinel.
     */
    private Sentinel() {
      super(Lazy.of(() -> Maybe.none()), Lazy.of(() -> new Sentinel()));
    }

    /**
     * An instance method for sentinel type list when head method is called. 
     * @return A NoSuchElementException object.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /** 
     * An instance method for sentinel type list when tail method is called.
     * @return A Sentinel type Object.
     */
    @Override
    public InfiniteList<Object> tail() {
      return sentinel(); 
    }

    /** 
     * An instance method for sentinel type list when map method is called.
     * @return A Sentinel type Object.
     */
    @Override 
    public <R> InfiniteList<R> map(Transformer<Object,  ? extends R> mapper) {
      return sentinel(); 
    }

    /** 
     * An instance method for sentinel type list when filter method is called.
     * @return A Sentinel type Object.
     */
    @Override 
    public InfiniteList<Object> filter(BooleanCondition<Object> predicate) {
      return sentinel();
    }

    /** 
     * An instance method for sentinel type list when limit method is called.
     * @return A Sentinel type Object.
     */
    @Override 
    public InfiniteList<Object> limit(long n) {
      return sentinel();
    }

    /** 
     * An instance method for sentinel type list when takeWhile method is called.
     * @return A Sentinel type Object.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<Object> predicate) {
      return sentinel(); 
    }

    /** 
     * An instance method for sentinel type list when toString method is called.
     * @return A String type Object.
     */
    @Override 
    public String toString() {
      return "-"; 
    }

    /** 
     * An instance method for sentinel type list when isSentinel method is called.
     * @return A boolean result. 
     */ 
    @Override
    public boolean isSentinel() {
      return true; 
    } 
  }

  /**
   * Returns an Sentinel type list.
   * @param <T> The type of value.
   * @return A Sentinel type Object.
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> empty = (InfiniteList<T>) new Sentinel(); 
    return empty;
  }

  /** 
   * An instance method that turn infinite list into a finite list of maximum size n.
   * @param  n The size of finitie list.
   * @return A finite list with maximum size of n.
   */
  public InfiniteList<T> limit(long n) {
    return n <= 0 ? this.sentinel() 
      : new InfiniteList<T>(this.head, 
          Lazy.of(() -> getMaybe(head).map(x -> getList(tail).limit(n - 1))
            .orElseGet(() -> getList(tail).limit(n))));
  }

  /**
   * An instance method that iterates through an infinite list, 
   * and truncates it once an element is result in false by the predicate.
   * @param predicate The condition used to truncate the list.
   * @return A finite list with element that evaluable the before first false appear.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {

    Lazy<Boolean> headNonEmpty = this.head.filter(x -> x.map(y -> true).orElse(false));  
    Lazy<Boolean> headPass = this.head.filter(x -> x.filter(predicate)
        .map(y -> true).orElse(false));

    Lazy<Boolean> combinedHead = headNonEmpty.combine(headPass, (x, y) -> x && y); 
    Lazy<Boolean> combinedTail = headNonEmpty.combine(headPass, (x, y) -> !x || y); 

    return new InfiniteList<T>(
        combinedHead.map(x -> x ? getMaybe(this.head) : Maybe.none()),
        combinedTail.map(y -> y ? this.tail.map(z -> z.takeWhile(predicate)).get()         
          : sentinel()));
  }

  /**
   * Returns true if it is Sentinal or false otherwise.
   * @return A boolean type.
   */
  public boolean isSentinel() {
    return false; 
  }

  /**
   * An instance terminal operation to accumulate the element in the list.
   * @param <U> the type of value of accumulating the elemets in the list.
   * @param identity the starting point for reduce.
   * @param accumulator describe function to accumulate the element, and must be a type Combiner  
   * @return a value obtained from reducing the infinite list.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {

    InfiniteList<T> sum = this; 
    U output = identity;

    while (!sum.isSentinel()) {
      U temp = output;
      output = sum.getMaybe(sum.head).map(x -> accumulator.combine(temp, x)).orElse(temp);
      sum = sum.getList(sum.tail); 
    } 

    return output; 
  }

  /**
   * An instance terminal opertation that counts number of elements in the list.
   * @return the number of elements in the list.
   */
  public long count() {
    return this.reduce(0, (x, y) -> x + 1); 
  }

  /**
   * An instance method that returns a list containing the elements inside the list.
   * @return A ArrayList containing the elements in the list.
   */
  public List<T> toList() {
    InfiniteList<T> sum = this; 
    List<T> output = new ArrayList<>();

    while (!sum.isSentinel()) {
      sum.getMaybe(sum.head).map(x -> output.add(x));
      sum = sum.getList(sum.tail); 
    } 

    return output;            
  }

  /**
   * An instance method to visualise value in the list which suitable for troubleshooting.
   * @return the string representation of this instance
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
