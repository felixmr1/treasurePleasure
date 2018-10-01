package goteborgsuniversitet.maptestapp.core.Containers;
import java.util.ArrayList;
import java.util.List;

/**
 * class invariant : nOfBusySlots <= maxSize
 **/

//TODO: write method for removing(Item), moveItem(index1,index2), 
public class BackPack <T> implements Inventory<T> {

    private List<T> items;

    private int backPackLevel;
    private int maxSize;
    private int nOfBusySlots;



    public BackPack(int maxSize){
        this.items = new ArrayList<>(maxSize);

        this.backPackLevel = 1;
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
    public void appendTo(T item) throws Exception {

        if(nOfBusySlots < maxSize){
            items.add(item);
            nOfBusySlots ++;
        }
        else {
            throw new Exception();
        }
    }

    @Override
    public void remove(T item) throws Exception {

    }


    public List<T> getAllItems(){
        return this.items;
    }

    public boolean isFull(){
        return nOfBusySlots >=  maxSize ;
    }

    public boolean isEmpty(){
        return (nOfBusySlots == 0);
    }

    public int getnOfEmptySlots(){return (maxSize - items.size());}

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

