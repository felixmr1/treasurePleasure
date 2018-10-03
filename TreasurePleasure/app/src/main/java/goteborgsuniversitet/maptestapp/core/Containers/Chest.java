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
    public void add(T item) throws Exception {

    }

    // Add multiple items at once
    public void add(List<T> items) throws Exception {

    }



    @Override
    public void removeAll() throws Exception {

    }

    @Override
    public List<T> getAllItems() {
        return null;
    }
}
