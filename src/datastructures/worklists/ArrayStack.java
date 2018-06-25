    package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
	//
	private E[] arr;
	private int back;
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		arr = (E[]) new Object[10];
		back = -1;
	}

	@Override
	public void add(E work) {
		if (back == arr.length - 1) {
		    E[] resized = (E[]) new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++) {
				resized[i] = arr[i];
			}
			arr = resized;
		}
        back++;
        arr[back] = work;
	}

	@Override
	public E peek() {
		if (back + 1 <= 0) {
			throw new NoSuchElementException();
		}
		E data = arr[back];
		return data;
	}

	@Override
	public E next() {
	    E dataRet = peek();
		back--;
		return dataRet;
	}

	@Override
	public int size() {
		return back + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (E[]) new Object[10];
		back = -1;
	}
}
