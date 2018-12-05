package br.pucrs.adt;

public interface PriorityQueueTAD<T extends Comparable<T>> {

	int size();

	void enqueue(Integer k, T el);

}