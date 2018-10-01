package goteborgsuniversitet.maptestapp.core.Containers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 2018-09-24.
*/

public class Chest<T>  implements Inventory<T> {


    Chest(){


    }


    @Override
    public void appendTo(T item) throws Exception {

    }

    @Override
    public void remove(T item) {

    }

    @Override
    public List<T> getAllItems() {
        return null;
    }
}
