package treasure.pleasure.model;

import java.util.ArrayList;
import java.util.List;

/**
 * class invariant : nOfBusySlots <= maxSize
 **/

//TODO: write method for removing(Item), moveItem(index1,index2), 
class Backpack<T> {

  private List<T> items;

  private int backPackLevel;
  private int maxSize;
  private int nOfBusySlots;


  Backpack(int maxSize) {
    this.items = new ArrayList<>(maxSize);

    this.backPackLevel = 1;
    this.maxSize = maxSize;
    this.nOfBusySlots = 0;
  }


  /**
   * @param nOfMoreSlots int > 0, if given negative input it will not mutate the object in any way
   */
  void upgrade(int nOfMoreSlots) {
    if (nOfMoreSlots > 0) {
      this.maxSize = maxSize + nOfMoreSlots;
      this.backPackLevel++;
    }
  }

  void add(T item) throws Exception {

    if (nOfBusySlots < maxSize) {
      items.add(item);
      nOfBusySlots++;
    } else {
      throw new Exception();
    }
  }

  void removeAll() {

    System.out.println(nOfBusySlots);

      this.items = new ArrayList<>(0);
      nOfBusySlots = 0;


  }


  List<T> getAllItems() {
    return this.items;
  }

  boolean isFull() {
    return nOfBusySlots >= maxSize;
  }

  boolean isEmpty() {
    return (nOfBusySlots == 0);
  }

  int getnOfEmptySlots() {
    return (maxSize - items.size());
  }

  int getnOfBusySlots() {
    return nOfBusySlots;
  }

  int getBackPackLevel() {
    return backPackLevel;
  }

  int getMaxSize() {
    return this.maxSize;
  }


}

