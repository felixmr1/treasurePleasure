package treasure.pleasure.model;

import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.data.Data;

/**
 *
 *
 * class invariant : nOfBusySlots < maxSize
 * @author John
 */

//TODO: write method for removing(Item), moveItem(index1,index2), 
class Backpack<T extends Item> {

  private List<T> items;

  private int backPackLevel;
  private int maxSize;
  private int nOfBusySlots;


  Backpack(int maxSize) {
    this.items = new ArrayList<>(maxSize);
    this.backPackLevel = Data.getInitialBackpackLevel();
    this.maxSize = maxSize;
    this.nOfBusySlots = Data.getInitialNOfBusySlots();
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

  /**
   * adds item to backpack
   * @param item item to be added
   * @throws Exception if full
   */
  void add(T item) throws Exception {

    if (nOfBusySlots < maxSize) {
      items.add(item);
      nOfBusySlots++;
    } else {
      throw new Exception();
    }
  }

  /**
   * clears backpack if content.
   */
  void removeAll() {
    this.items = new ArrayList<>(0);
    nOfBusySlots = Data.getInitialNOfBusySlots();
  }

  List<T> getAllItems() {
    return this.items;
  }

  boolean isFull() {
    return nOfBusySlots >= maxSize;
  }

  boolean isNotFull() {
    return !isFull();
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

