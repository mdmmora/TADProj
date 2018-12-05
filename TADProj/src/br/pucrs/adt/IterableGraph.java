package br.pucrs.adt;

import java.util.Iterator;

public interface IterableGraph<N> {
	public Iterator<N> iteratorWidth(N origem);
	public Iterator<N> iteratorDepth(N origem);
}
