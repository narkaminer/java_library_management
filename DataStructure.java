// Assignment: 1
// Student: Michael Kaminer

package HW1;

import java.util.Arrays;
/**
 * Represents a generic data structure that stores elements of type T.
 *
 * @param <T> The type of elements stored in the data structure.
 */
public class DataStructure<T> extends Object {

  private int size;
  private static final int DEFAULT_CAPACITY = 20;
  private T[] list;

  /**
   * Constructs a DataStructure with the specified capacity.
   *
   * @param capacity The initial capacity of the data structure.
   * @throws IllegalArgumentException If the specified capacity is negative.
   */

  public DataStructure(int capacity){
      if(capacity<=0){
        throw new IllegalArgumentException ("Capacity can't be negative");
      } else list = (T[]) new Object[capacity];
  }

  /**
   * Constructs a DataStructure with the default capacity.
   */
  public DataStructure(){
    list = (T[]) new Object[DEFAULT_CAPACITY];
  }

  /**
   * Adds an element at the specified index in the data structure.
   *
   * @param data  The element to add.
   * @param index The index at which to add the element.
   * @throws IllegalArgumentException If the specified index is negative.
   */

    public void add(T data, int index){
      if (index<0){
        throw new IllegalArgumentException("Index can't be a negative number!");
      }

      if (index >= size ){
        increaseCapacity(index+1);
      }

      for(int i = size;i>index;i--){
          list[i]=list[i-1];
      }
        list[index] = data;
         size++;
      }

  /**
   * Adds an element to the end of the data structure.
   *
   * @param data The element to add.
   */

    public void addToEnd(T data){
    if (size== list.length) {
      increaseCapacity(2);
    }
      list[size++]=data;
    }

  /**
   * Gets the element at the specified index.
   *
   * @param index The index of the element to retrieve.
   * @return The element at the specified index.
   */

    public T getValue(int index){
    return list[index];
    }

  /**
   * Deletes the first occurrence of the specified element from the data structure.
   *
   * @param data The element to delete.
   */

    public void delete(T data){
      for(int i=0;i<size;i++){
        if (list[i].equals(data)){
          delete(i);
          return;
        }
      }
    System.out.print("Data not found: "+data);
    }

  /**
   * Deletes the element at the specified index.
   *
   * @param index The index of the element to delete.
   * @throws IllegalArgumentException If the specified index is out of bounds.
   */
    public void delete(int index){
    if (index<0 || index>=size){
      throw new IllegalArgumentException("Illegal index.Can't delete.");
    }
        for (int i = index;i<size-1;i++){
          list[i] = list[i+1];
        }
        list[size-1] = null;
        size--;
    }

  /**
   * Checks if the data structure contains the specified element.
   *
   * @param data The element to check for.
   * @return true if the data structure contains the element, false otherwise.
   * @throws NullPointerException If the specified element is null.
   */

    public boolean contains(T data){
    if (data==null) {
        throw new NullPointerException("Invalid input...");
    }
    for(T x:list){
      if (x!=null && x.equals(data)) return true;
    }
        return false;
    }

  /**
   * Gets the current size of the data structure.
   *
   * @return The size of the data structure.
   */

    public int size(){
    int i =list.length-1;
      while (i>=0 && list[i]==null){
        i--;
      }
        return i+1;
    }
  /**
   * Returns a string representation of the data structure.
   *
   * @return A string representation of the data structure.
   */
  @Override
  public String toString() {
    return "DataStructure{" +
            "size=" + size +
            ", list=\n" + Arrays.toString(Arrays.copyOf(list,size())) +
            '}';
  }
  /**
   * Checks if this data structure is equal to another object.
   *
   * @param o The object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataStructure<?> that = (DataStructure<?>) o;
    return size == that.size && Arrays.equals(list, that.list);
  }

  /**
   * Increases the capacity of the data structure to accommodate more elements.
   *
   * @param tooBigIndex The new desired capacity of the data structure.
   */
  private void increaseCapacity(int tooBigIndex){
    int newCapacity = Math.max(list.length*2,tooBigIndex);
    T[] newList = Arrays.copyOf(list,newCapacity);
    list = newList;
  }



}
