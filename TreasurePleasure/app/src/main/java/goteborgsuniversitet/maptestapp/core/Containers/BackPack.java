package goteborgsuniversitet.maptestapp.core.Containers;
import java.util.Collection;

/**
 * class invariant : currentSize <= maxSize
 */

//TODO: write method for removing(Item), moveItem(index1,index2), 
public class BackPack <T> extends Inventory<T> {

    private boolean wasSuccesful;
    private int backPackLevel;
    private int maxSize;
    private int nOfBusySlots;



    public BackPack(int maxSize){
        super();
        this. backPackLevel = 1;
        this.maxSize = maxSize;
        this.nOfBusySlots = 0;
    }



    /**
     * @param nOfMoreSlots int > 0, if given negative input it will not mutate the object in any way
     */
    public void upgrade(int nOfMoreSlots){

        if(nOfMoreSlots > 0) {

            this.maxSize = maxSize + nOfMoreSlots;
            this.backPackLevel++;
        }

    }


    @Override
    public boolean appendTo(T item) {

        if(nOfBusySlots < maxSize){
            slots.add(item);
            nOfBusySlots ++;

            return true;

        }
        else {
            return false;}

    }





    public Collection<T> getAllSlots(){
        return this.slots;
    }

    public boolean isFull(){
        return nOfBusySlots >=  maxSize ;
    }

    public boolean isEmpty(){
        return (nOfBusySlots == 0);
    }

    public int getnOfBusySlots(){
        return nOfBusySlots;
    }

    public int getBackPackLevel() {
        return backPackLevel;
    }

    public int getMaxSize() {
        return this.maxSize;
    }



}

