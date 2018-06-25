package datastructures.worklists;

import cse332.interfaces.worklists.PriorityWorkList;
import cse332.exceptions.NotYetImplementedException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;
    
    public MinFourHeap() {
        data = (E[]) new Object[1000]; // TODO CHANGE SIZE
        size = 0;
    }

    @Override
    public boolean hasWork() {
        return size > 0;
    }

    @Override
    public void add(E work) {
        data[size] = work;
        percUp(size);
        size++;            
    }
    
    public void percUp(int i) {
        if (i > 0) {
            int parent = (i - 1) / 4;
            if (data[i].compareTo(data[parent]) > 0) {
                E temp = data[i];
                data[i] = data[parent];
                data[parent] = data[i];
                percUp(parent);
            }
        }
    }

    @Override
    public E peek() {
        throw new NotYetImplementedException();
    }

    @Override
    public E next() {
        throw new NotYetImplementedException();
    }

    @Override
    public int size() {
        throw new NotYetImplementedException();
    }

    @Override
    public void clear() {
        throw new NotYetImplementedException();
    }
}
