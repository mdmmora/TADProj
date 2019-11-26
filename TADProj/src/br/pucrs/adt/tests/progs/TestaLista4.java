package br.pucrs.adt.tests.progs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.pucrs.adt.ListArray;
import br.pucrs.adt.ListSimpleLinkedFull;
import br.pucrs.adt.ListTAD;

public class TestaLista4 {
	public static void main(String[] args) {

									//
		ListTAD <String> l = new ListSimpleLinkedFull<>();	// = new SUA CLASSE AQUI;
//		ListTAD <String> l = new ListArray<>(100);	// = new SUA CLASSE AQUI;
									//
		String s1 = new String("a");
		String s2 = new String("b");
		String s3 = new String("c");
		String s4 = new String("d");
		String s5 = new String("e");
		
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		l.add(s5);
		
		System.out.println("Lista " + l);
	}
}
