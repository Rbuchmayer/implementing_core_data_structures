package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
    
    private E[] arr;
    private int back;
    private int front;
    private int sizeREMOVE;

    
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        arr = (E[]) new Object[capacity];
        front = 0;
        back = 0;
        sizeREMOVE = 0;
    }

    @Override
    public void add(E work) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (back == arr.length - 1) {
            back = 0;
        } else {
            back++;
        }
        arr[back] = work;
        sizeREMOVE++;
    }

    @Override
    public E peek() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        return arr[front];
    }
    
    @Override
    public E peek(int i) {
        if (!hasWork()) {
            throw new NoSuchElementException();
        } else if (i >= size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arr[(front + i) % (arr.length - 1)];
    }
    
    @Override
    public E next() {
        if (!hasWork()) {
            throw new NoSuchElementException();
        }
        E data = peek();
        front = front++ % arr.length;
        sizeREMOVE--;
        return data;
    }
    
    @Override
    public void update(int i, E value) {
        if (i >= size() || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        arr[(front + i) % arr.length] = value;
    }
    
    @Override
    public int size() {
        return sizeREMOVE;
    }
    
    @Override
    public void clear() {
        arr = (E[]) new Object[super.capacity()];
        front =  -1;
        back = -1;
        sizeREMOVE = 0;
        // TODO change this
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in p2. Leave this method unchanged for p1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in p2. Leave this method unchanged for p1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in p2. Leave this method unchanged for p1.
        throw new NotYetImplementedException();
    }
}
