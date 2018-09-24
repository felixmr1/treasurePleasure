package goteborgsuniversitet.maptestapp.core.Containers;

import java.util.ArrayList;

/**
 * Created by John on 2018-09-24.
*/

public class Chest<T>  extends Inventory<T> {


    Chest(){
        this.slots = new ArrayList<>();

    }




    @Override
    public boolean appendTo(T item) {
        return false;
    }
}
