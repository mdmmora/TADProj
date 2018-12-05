package br.pucrs.adt;
import java.io.Serializable;

public class PilhaEnc<T extends Comparable<T>> implements StackTAD<T>, Serializable {
	private ListTAD<T> lst = new ListDoubleLinked<T>();
	
	public void push(T dado)
	{
		lst.add(0, dado);
	}
	
	public T pop()
	{
		return lst.remove(0);
	}
	
	public T top()
	{
		return lst.get(0);
	}
	
	public boolean isEmpty()
	{
		return lst.isEmpty();
	}
	
	public int size()
	{
		return lst.size();
	}
}
