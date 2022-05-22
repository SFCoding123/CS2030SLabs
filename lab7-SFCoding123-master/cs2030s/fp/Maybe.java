/**
 * CS2030s Lab5 Maybe Class. 
 * @author Wang ShaoFeng (Lab Group)
 * @version AY21/22 Semester 2
 */ 

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  private final T t; 
  private static final None empty = new None(); 

  private Maybe(T t) {
    this.t = t; 
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> e1 = (Maybe<T>) empty; 
    return e1; 
  } 

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  } 

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none(); 
    } else {
      return some(t); 
    }
  }

  public boolean isPresent() {
    return !(this.t == null);
  }

  public boolean isEmpty() {
    return this.t == null; 
  }

 

  

  protected abstract T get();
  
  public abstract Maybe<T> filter(BooleanCondition<? super T> cc); 
  
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> tf);
  
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> tf);   
  
  public abstract  <U extends T> T orElse(U u);

  public abstract  T orElseGet(Producer<? extends T> pro); 

  public abstract Maybe<T> consumeWith(Consumer<? super T> consumer); 

  private static class None extends Maybe<Object> {
    None() {
      super(null); 
    }

    @Override 
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true; 
      } 
      return false;
    } 

    @Override 
    protected Object get() {
      throw new NoSuchElementException(); 
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<Object> cc) {
      return this;
    }

    @Override
    public <U> Maybe<U> map(Transformer<Object, ? extends U> tf) {
      return none(); 
    }

    @Override 
    public <U> Maybe<U> flatMap(Transformer<Object, ? extends Maybe<? extends U>> tf) {
      return none();
    }

    @Override
    public Object orElse(Object obj) {
      return obj; 
    }
    
    @Override 
    public Object orElseGet(Producer<? extends Object> pro) {
      return pro.produce(); 
    }

    @Override
    public Maybe<Object> consumeWith(Consumer<Object> consumer) {
      return none(); 
    }


  }

  private static class Some<T> extends Maybe<T> { 
    private T t; 
    
    Some(T t) { 
      super(t);
      this.t = t; 
    } 

    @Override
    public String toString() {
      return "[" + this.t + "]"; 
    }

    @Override 
    public boolean equals(Object obj) {
      if (obj == this) { 
        return true; 
      }
      if (obj instanceof Some<?>) {
        Some<?> s1 = (Some<?>) obj; 
        if (this.t == s1.t) {
          return true; 
        } 
        if (this.t == null || s1.t == null) {
          return false; 
        }

        return this.t.equals(s1.t); 
      } 
      return false; 
    } 

    @Override
    protected T get() {
      return this.t; 
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> cc) {
      if (!super.isPresent()) {
        return this; 
      } else {
        if (!cc.test(this.t)) {

          return none(); 
        } else {
          return this; 
        }

      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> tf) {
      return some(tf.transform(this.t)); 
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> tf) {
      @SuppressWarnings("unchecked")
      Maybe<U> c1 = (Maybe<U>) tf.transform(this.t);
      return c1; 
    }

    @Override
    public <U extends T> T orElse(U u) {
      return this.t;  
    }

    @Override
    public T orElseGet(Producer<? extends T> pro) {
      return this.t; 
    } 

    @Override 
    public Maybe<T> consumeWith(Consumer<? super T> consumer) {
      this.t = null; 
      return this;
    }

  }
}
