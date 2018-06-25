package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FIFOWorkList;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
	//
	@SuppressWarnings("hiding")
	private class Node<E> {
		private E data;
		private Node<E> next;
	}

	private Node<E> front;
	private Node<E> back;
	private int size;

	public ListFIFOQueue() {
		front = null;
		back = null;
		size = 0;
	}

	@Override
	public void add(E work) {
	    size++;
		Node<E> n = new Node<E>();
		n.data = work;
		n.next = null;
		if (front == null) {
			front = n;
			back = n;
		} else {
			back.next = n;
			back = n;
		}
	}

	@Override
	public E peek() {
		if(front == null) {
			throw new NoSuchElementException();
		}
		E data = front.data;
		return data;
	}

	@Override
	public E next() {
	    E nextRet = peek();
		front = front.next;
		if(front == null) {
			back = null;
		}
		size--;
		return nextRet;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		front = null;
		back = null;
	}
}
