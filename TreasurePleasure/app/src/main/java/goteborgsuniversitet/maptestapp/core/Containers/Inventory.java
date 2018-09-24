package goteborgsuniversitet.maptestapp.core.Containers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Inventory<T> {

    Collection<T> slots;

    Inventory(){
        slots =  new ArrayList();

    }

    public abstract boolean appendTo(T item);








}
