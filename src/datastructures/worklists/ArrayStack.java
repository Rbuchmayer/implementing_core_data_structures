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
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		arr = (E[]) new Object[10];
		back = -1;
		size = 0;
	}

	@Override
	public void add(E work) {
		try {
			back++;
			arr[back] = work;
			size++;
		} catch (IndexOutOfBoundsException resize) {

			@SuppressWarnings("unchecked")
			E[] resized = (E[]) new Object[arr.length * 2];
			for (int i = 0; i < arr.length; i++) {
				resized[i] = arr[i];
			}
			arr = resized;
			back++;
			size++;
			arr[back] = work;
		}

	}

	@Override
	public E peek() {
		if (size <= 0) {
			throw new NoSuchElementException();
		}
		E data = arr[back];
		return data;
	}

	@Override
	public E next() {
	    E dataRet = peek();
		back--;
		size--;
		return dataRet;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (E[]) new Object[10];
		back = -1;
		size = 0;
	}
}
