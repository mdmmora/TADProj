package br.pucrs.adt;

import java.util.Iterator;

public interface IteratorList<E> extends Iterator<E> {
	public boolean hasNext(E element);
	public E next(E element);
	public boolean hasPrev();
	public E prev();
}
