package br.pucrs.adt.tests.progs;
import java.util.Iterator;

import br.pucrs.adt.*;

public class TestaIteradores {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ListArray<String> l = new ListArray<String>(10);
		ListSimpleLinkedFull<String> l = new ListSimpleLinkedFull<String>();
		
		l.add("A");
		l.add("B");
		l.add("C");
		l.add("D");
		l.add("E");
		l.add("F");
		l.add("G");
		l.add("H");
		l.add("I");
		l.add("J");
		
		Iterator<String> it = l.iterator();
		
		while(it.hasNext())
			System.out.println(it.next());
		
		for(String s:l)
			System.out.println(s);
	}

}
