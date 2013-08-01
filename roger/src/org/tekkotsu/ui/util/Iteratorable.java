package org.tekkotsu.ui.util;

import java.util.Iterator;

public class Iteratorable<T> implements Iterator<T>, Iterable<T> {
	private Iterator<T> iter;
	
	public Iteratorable(Iterator<T> iter)
	{
		this.iter = iter;
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public T next() {
		return iter.next();
	}

	@Override
	public void remove() {
		iter.remove();
	}

	@Override
	public Iterator<T> iterator() {
		return iter;
	}
}
