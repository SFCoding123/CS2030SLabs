/**
 * The Array<T> for CS2030S 
 *
 * @author Wang ShaoFeng 
 * @version CS2030S AY21/22 Semester 2
 */

class Array<T extends Comparable<T>> { 
  private T[] array;

  public Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] t = (T[]) new Comparable[size];
    this.array = t; 
  }

  public void set(int index, T item) {
    this.array[index] = item; 
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T min = this.array[0]; 
    for (int x = 1; x < array.length; x++) {
      if (min.compareTo(this.array[x]) > 0) {
        min = this.array[x];
      } 
    }
    return min; 
  }

  public int length() {
    return array.length;
  }


  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
