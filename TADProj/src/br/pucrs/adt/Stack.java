
package br.pucrs.adt;
import java.io.Serializable;

public class Stack<T extends Comparable<T>> implements StackTAD<T>, Serializable {
	//private ListTAD<T> lst = new ListDoubleLinked<T>();
	private ListTAD<T> lst = new ListSimpleLinkedFull<T>();
	
	public void push(T dado)
	{
		lst.add(0, dado);
	}
	
	public T pop()
	{
		if (!lst.isEmpty())
			return lst.remove(0);
		else
			throw new EmptyStackException();
	}
	
	public T top()
	{
		if (!lst.isEmpty())
			return lst.get(0);
		else
			throw new EmptyStackException();
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
