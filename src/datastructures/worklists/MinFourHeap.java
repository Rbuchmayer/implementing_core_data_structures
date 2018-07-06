package datastructures.worklists;

import cse332.interfaces.worklists.PriorityWorkList;

import java.util.NoSuchElementException;

import cse332.exceptions.NotYetImplementedException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java for method
 * specifications.
 */
public class MinFourHeap<E extends Comparable<E>> extends PriorityWorkList<E> {

	/*
	 * Do not change the name of this field; the tests rely on it to work correctly.
	 */
	private E[] data;
	private int back;

	public MinFourHeap() {
		data = (E[]) new Comparable[10];
		back = -1;
	}

	@Override
	public boolean hasWork() {
		return size() > 0;
	}

	@Override
	public void add(E work) {
		if (!hasWork()) {
			back++;
			data[back] = work;
		} else {
			if (back == data.length - 1) {
				E[] resized = (E[]) new Comparable[data.length * 2];
				for (int i = 0; i < data.length; i++) {
					resized[i] = data[i];
				}
				data = resized;
			}

			back++;
			data[back] = work;

			int current = back;
			while (data[((current - 1) / 4)].compareTo(work) > 0) {
				int parentIndex = (current - 1) / 4;
				E temp = data[current];
				data[current] = data[parentIndex];
				data[parentIndex] = temp;
				current = parentIndex;
			}
		}
	}

	@Override
	public E peek() {
		if (!hasWork()) {
			throw new NoSuchElementException();
		}
		return data[0];
	}

	@Override
	public E next() {
		E work = peek();
		data[0] = data[back];
		data[back] = null;
		back--;

		int parentIndex = 0;
		int minChildIndex = 0;
		E child = data[minChildIndex];

		for (int i = 4 * parentIndex + 1; i <= 4 * parentIndex + 4 && i < size(); i++) {
			if (data[i].compareTo(child) < 0) {
				child = data[i];
				minChildIndex = i;
			}
		}

		while (parentIndex != minChildIndex) {
			data[minChildIndex] = data[parentIndex];
			data[parentIndex] = child;
			child = data[minChildIndex];
			parentIndex = minChildIndex;

			for (int i = 4 * parentIndex + 1; i <= 4 * parentIndex + 4 && i < size(); i++) {
				if (data[i].compareTo(child) < 0) {
					child = data[i];
					minChildIndex = i;
				}
			}
		}

		return work;
	}

	@Override
	public int size() {
		return back + 1;
	}

	@Override
	public void clear() {
		data = (E[]) new Comparable[10];
		back = -1;
	}
}
