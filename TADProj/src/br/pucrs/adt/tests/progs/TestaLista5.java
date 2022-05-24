package br.pucrs.adt.tests.progs;

import br.pucrs.adt.ListSimpleLinked;

import java.io.*;

public class TestaLista5 {

	public static void mostraLista(ListSimpleLinked <String> l)
	{
		System.out.println(l);
		System.out.println("Fim mostraLista()");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListSimpleLinked <String> l = new ListSimpleLinked<>();		
		String s1 = new String("a");
		String s2 = new String("b");
		String s3 = new String("c");
		String s4 = new String("a");
		String s5 = new String("e");
        String s6 = new String("c");
		String s7 = new String("a");
		String s8 = new String("f");

		//addFirst()
		System.out.println("\nadd()");
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		l.add(s5);
        l.add(s6);
		l.add(s7);
		l.add(s8);
		mostraLista(l);
		//	System.exit(0);
		//Fim do primeiro teste. Comentar ou descomentar o exit(0).

		//-------------------------------		
		//	add() no final		
		System.out.println("\nupdateALL()");
		l.updateAll("a", "X");
		mostraLista(l);
        
        l.updateAll("c", "Y");
        mostraLista(l);
//		System.exit(0);
		//Fim do segundo teste. Comentar ou descomentar o exit(0).



	}

}

