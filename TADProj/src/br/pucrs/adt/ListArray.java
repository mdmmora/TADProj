package br.pucrs.adt;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListArray <D> implements ListTAD<D>, Serializable {
	private D [] vet;
	private int qtdElem = 0;
	
	public ListArray(int n)
	{
		int tam = n;
		if (n <= 0)
			tam = 100;
		
		vet = (D[]) new Comparable[tam];
	}
	
	public void set(int index, D elem)
	{
		if ((index >= 0) && (index < this.size()))
			{
				vet[index] = elem;
			}
		else
			throw new IndexOutOfBoundsException();
	}
	
	public D get(int index)
	{
		D res = null; 
		
		if ((index >= 0) && (index < this.size()))
			{
				res = vet[index];
			}
		else
			throw new IndexOutOfBoundsException();
		
		return res;
	}
	
	public void add(int index, D elem)
	{
		int i;
		
		if (this.size() == vet.length)
			throw new InvalidParameterException("Lista Cheia"); 
		
		if ((index >= 0) && (index <= this.size()))
		{
			for (i = this.size(); i > index; i--)
				vet[i] = vet[i-1];
			
			vet[index] = elem;
			qtdElem++;
		}
		else
			throw new IllegalArgumentException();
	}
	
	public void add(D elem)
	{
		if (this.size() == vet.length)
			throw new InvalidParameterException("Lista Cheia"); 

		vet[qtdElem] = elem;
		qtdElem++;
	}
	
	public D remove(int index)
	{
		int i;
		D res;
		
		if ((index >= 0) && (index < this.size()))
		{
			res = vet[index];
			
			for (i = index; i < this.size()-1; i++)
				vet[i] = vet[i+1];
			
			qtdElem--;
		}
		else
			throw new IllegalArgumentException();
	
		return res;
	}
	
	public boolean isEmpty()
	{
		boolean res = false;
		
		if (this.size() == 0)
			res = true;
		
		return res;
	}
	
	public int size()
	{
		return qtdElem;
	}

	public D remove(D element) {
		// TODO Auto-generated method stub
		return null;
	}

	public D search(D ob) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFirst(D d) {
		this.add(0, d);
	}
	
	public void addLast(D d) {
		this.add(d);
	}
	
	public void clean() {
		qtdElem = 0;
	}
	
	public int count(D element) {
		int res = 0;
		
		for (int i = 0; i < this.size(); i++)
			if (element.equals(vet[i]))
				res++;
		return res;
	}
	
	public D getFirst() {
		D res = null;
		
		if (this.size() > 0)
			res = vet[0];
		
		return res;
	}
	
	public D getLast() {
		D res = null;
		
		if (this.size() > 0)
			res = vet[this.size()-1];
		
		return res;
	}
	
	public D removeFirst() {
		D res = null;
		
		if (this.size() > 0) {
			res = vet[0];
			
			for (int i = 0; i < this.size()-1; i++)
				vet[i] = vet[i+1];
			
			qtdElem--;
		}
			
		return res;
	}
	
	public D removeLast() {
		D res = null;
		
		if (this.size() > 0) {
			res = vet[this.size() - 1];
			qtdElem--;
		}
		
		return res;
	}
	
	public String toString() {
		String res = "";
		
		for(int i = 0; i < this.size(); i++)
			res = res + vet[i].toString();
		
		return res;
	}
}
