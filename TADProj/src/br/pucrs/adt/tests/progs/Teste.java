package br.pucrs.adt.tests.progs;

import java.util.Random;

import br.pucrs.adt.ListDoubleLinked;
import br.pucrs.adt.ListSimpleLinkedFull;

public class Teste {
	public static void main(String [] argc) {
		Random rnd = new Random();
		ListSimpleLinkedFull<Integer> lst1 = new ListSimpleLinkedFull<>();
		ListDoubleLinked<Integer> lst2 = new ListDoubleLinked<>();
		
		int x ;

		for (int i = 0; i < 30; i++) {
			x = rnd.nextInt(50);
			lst1.add(x);
			lst2.add(x);
			System.out.print(x + " ");
		}
		System.out.println("");
		
		for (Integer i:lst1)
			System.out.print(i + " ");
		System.out.println("");
//		
//		for (Integer i:lst2)
//			System.out.print(i + " ");
//		System.out.println("");
	}

}
