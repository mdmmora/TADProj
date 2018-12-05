package br.pucrs.adt;

import java.util.Iterator;

public interface IteratorGeneralTree<E> extends Iterator<E> {
	public E getFather();
	public E getChild(int id);
	public E getElem();
}
