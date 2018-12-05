package br.pucrs.adt.tests.progs;

import br.pucrs.adt.PriorityQueue;
import br.pucrs.adt.PriorityQueueTAD;

public class TestePriorityQueue {

	public static void main(String[] args) {
		PriorityQueueTAD<Integer> pq = new PriorityQueue<>();
		PriorityQueueTAD<String> pq1 = new PriorityQueue<>();
		PriorityQueueTAD<PessoaTeste> pq2 = new PriorityQueue<>();

		pq.offer(15);
		System.out.println(pq.toString());
		pq.offer(60);
		System.out.println(pq.toString());
		pq.offer(72);
		System.out.println(pq.toString());
		pq.offer(70);
		System.out.println(pq.toString());
		pq.offer(56);
		System.out.println(pq.toString());
		pq.offer(32);
		System.out.println(pq.toString());
		pq.offer(62);
		System.out.println(pq.toString());
		pq.offer(92);
		System.out.println(pq.toString());
		pq.offer(45);
		System.out.println(pq.toString());
		pq.offer(30);
		System.out.println(pq.toString());
		pq.offer(65);
		System.out.println(pq.toString());
		
		Node n2 = new Node("B", 9);
		fila.offer(n2);
		
		System.out.print("\n" + pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.print(pq1.pull() + " ");
		System.out.println("\n");

		pq2.offer(new PessoaTeste("15", "Joao"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("60", "Jose"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("72", "Pedro"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("70", "Paulo"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("56", "Maria"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("32", "Ana"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("62", "Joana"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("92", "Marcelo"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("45", "Marina"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("30", "Tiago"));
		System.out.println(pq2.toString());
		pq2.offer(new PessoaTeste("65", "Eduarda"));
		System.out.println(pq2.toString());
		
		System.out.print("\n" + pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.print(pq2.pull() + " ");
		System.out.println("\n");

		
	}

}
