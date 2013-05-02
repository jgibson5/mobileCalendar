package com.example.calendarapp;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * An implementation of List for manipulating binary data.
 * 
 * This uses a linked list to store Integers, and each bit of the Integer to store a
 * boolean value.
 *
 */
public class BitList implements List<Boolean>, Iterable<Boolean>, Cloneable {
	private LinkedList<Integer> ints = new LinkedList<Integer>();
	private int length = 0;
	
	public BitList() {
	}
	
	/**
	 * Create a new BitList from body of text containing 1's and 0's.
	 * @param seq
	 */
	public BitList(CharSequence seq) {
		parse(seq);
	}
	
	@Override
	public boolean add(Boolean e) {
		checkNull(e);
		uncheckedAdd(e);
		return true;
	}

	@Override
	public void add(int index, Boolean element) {
		checkNull(element);
		if (index == 0 && length == 0) {
			uncheckedAdd(element);
			return;
		}
		checkIndex(index);
		uncheckedAdd(uncheckedGet(length - 1));
		for (int i = index; i < length - 1; i++) {
			uncheckedSet(i + 1, uncheckedGet(i));
		}
		uncheckedSet(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Boolean> c) {
		checkNull(c);
		if (c.isEmpty()) return false;
		for (Boolean element: c) {
			checkNull(element);
		}
		for (Boolean element: c) {
			uncheckedAdd(element);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Boolean> c) {
		checkNull(c);
		if (c.isEmpty()) return false;
		checkIndex(index);
		for(Boolean element: c) {
			checkNull(element);
		}
		for(Boolean element: c) {
			add(index, element);
			index++;
		}
		return true;
	}

	@Override
	public void clear() {
		ints.clear();
		length = 0;
	}

	@Override
	public boolean contains(Object o) {
		checkNull(o);		
		boolean other = (Boolean) o;
		if (isEmpty()) return false;
		
		int last = ints.getLast();
		for (int i = 0; i < length / Integer.SIZE; i++) {
			if (other ^ (ints.get(i) == 0)) return true;
		}
		for (int i = 0; i < length % Integer.SIZE; i++) {
			if (other ^ (last & (1 << i)) == 0) return true;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		checkNull(c);
		if (c.isEmpty()) return false;
		if (c.contains(true) && !contains(true)) return false;
		if (c.contains(false) && !contains(false)) return false;
		return true;
	}

	@Override
	public Boolean get(int index) {
		checkIndex(index);
		return uncheckedGet(index);
	}

	@Override
	public int indexOf(Object o) {
		checkNull(o);
		for (int i = 0; i < length; i++) {
			if (uncheckedGet(i) == (Boolean) o) return i;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public Iterator<Boolean> iterator() {
		return new BitListIterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		checkNull(0);
		for (int i = length - 1; i >= 0; i--) {
			if (uncheckedGet(i) == (Boolean) o) return i;
		}
		return -1;
	}

	@Override
	public ListIterator<Boolean> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<Boolean> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Add trues and falses to the list from 1's and 0's in a CharacterSequence.
	 * 
	 * Skips all other characters.
	 * 
	 * @param seq
	 */
	public void parse(CharSequence seq) {
		for (int i = 0; i < seq.length(); i++) {
			if (seq.charAt(i) == '1') add(true);
			else if (seq.charAt(i) == '0') add(false);
		}
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean remove(int index) {
		checkIndex(index);
		boolean old = uncheckedGet(index);
		for (int i = index; i < length - 1; i++) {
			uncheckedSet(i, uncheckedGet(i + 1));
		}
		length--;
		if (length % 8 == 0) ints.removeLast();
		return old;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean set(int index, Boolean element) {
		checkNull(element);		
		checkIndex(index);
		boolean oldBit = uncheckedGet(index);
		uncheckedSet(index, element);
		return oldBit;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public List<Boolean> subList(int fromIndex, int toIndex) {
		checkIndex(fromIndex);
		checkIndex(toIndex);
		List<Boolean> sub = new BitList();
		for (int i = fromIndex; i < toIndex; i++) {
			sub.add(uncheckedGet(i));
		}
		return sub;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[length];
		for (int i = 0; i < length; i++) {
			array[i] = (Boolean) uncheckedGet(i);
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (uncheckedGet(i)) builder.append('1');
			else builder.append('0');
		}
		return builder.toString();
	}
	
	public class BitListIterator implements Iterator<Boolean> {
		private int position = 0;

		@Override
		public boolean hasNext() {
			return position < length;
		}

		@Override
		public Boolean next() {
			if (!hasNext()) throw new NoSuchElementException();
			boolean result = uncheckedGet(position);
			position++;
			return result;
		}

		@Override
		public void remove() {
			BitList.this.remove(position);
		}
		
	}
	
	@Override
	public BitList clone() {
		BitList bits = new BitList();
		bits.addAll(this);
		return bits;
	}
	
	private void checkIndex(int index) {
		if (index >= length || index < 0) throw new IndexOutOfBoundsException();
	}
	
	private void checkNull(Object element) {
		if (element == null) throw new NullPointerException();
	}
	
	private boolean uncheckedGet(int index) {
		int longIndex = index / Integer.SIZE;
		int bitIndex = index % Integer.SIZE;
		long currentInt = ints.get(longIndex);
		return (currentInt & (1 << bitIndex)) != 0;
	}
	
	private void uncheckedAdd(boolean element) {
		length++;
		if (length % Integer.SIZE == 1) ints.add(0);
		uncheckedSet(length - 1, element);
	}
	
	private void uncheckedSet(int index, boolean element) {
		int intIndex = index / Integer.SIZE;
		int bitIndex = index % Integer.SIZE;
		int currentInt = ints.get(intIndex);
		if (element) currentInt |= 1 << bitIndex;
		else currentInt &= ~(1 << bitIndex);
		
		ints.set(intIndex, currentInt);
	}

}
