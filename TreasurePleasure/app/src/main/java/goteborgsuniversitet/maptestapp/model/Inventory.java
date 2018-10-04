package goteborgsuniversitet.maptestapp.model;

import java.util.List;

public interface  Inventory<T> {
     void add(T item) throws Exception;
     void removeAll() throws Exception;

     List<T> getAllItems();


}
