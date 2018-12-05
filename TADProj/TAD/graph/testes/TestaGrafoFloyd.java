package TAD.graph.testes;

import java.util.Random;

import org.w3c.dom.NodeList;

import br.pucrs.adt.DijkstraResult;
import br.pucrs.adt.GrafoDirRotLisAdj;
import br.pucrs.adt.GrafoDirRotMatAdj;
import pucrs.alpro.util.*;

public class TestaGrafoFloyd {

	private static void mostraMatriz(double [][] mat){

		System.out.println("Matriz Bruta");

		for (int i = 0; i < mat.length; i++){
			for (int j = 0; j < mat.length; j++)
				System.out.print(mat[i][j] + "\t");
		    System.out.println("");
		}
	}

	private static GrafoDirRotMatAdj<String, Double> createKGraph(int k){
		Random rnd = new Random(30);

		GrafoDirRotMatAdj<String, Double>grfMat = new GrafoDirRotMatAdj<>(k);

		for (int i = 1; i <= k; i++)
			grfMat.addNode("N" + i);

		String orig = "";
		for (int i = 1; i <= k; i++) {
			orig = "N" + i;
			for (int j = 1; j <= k; j++)
				if (i != j)
					grfMat.addEdge(orig, "N"+j, (double)rnd.nextInt(k*10));
		}

		return grfMat;
	}

	public static void main(String[] args) {
		GrafoDirRotMatAdj<String, Double> grfMat = new GrafoDirRotMatAdj<>(20);

		TimeCounter tc = new TimeCounter();
		double tempo;
		double [][] resMat = null;

		grfMat.addNode("N1");
		grfMat.addNode("N2");
		grfMat.addNode("N3");
		grfMat.addNode("N4");


		grfMat.addEdge("N1", "N2", 2000.0);
		grfMat.addEdge("N1", "N3", 5.0);
		grfMat.addEdge("N1", "N4", 10.0);
		grfMat.addEdge("N2", "N1", 7.0);
		grfMat.addEdge("N2", "N3", 6.0);
		grfMat.addEdge("N2", "N4", 8.0);
		grfMat.addEdge("N3", "N1", 2.0);
		grfMat.addEdge("N3", "N2", 6.0);
		grfMat.addEdge("N3", "N4", 5.0);
		grfMat.addEdge("N4", "N1", 11.0);
		grfMat.addEdge("N4", "N2", 10.0);
		grfMat.addEdge("N4", "N3", 4.0);

		System.out.println("Grafo Matriz: Teste Manual ");
		System.out.println(grfMat + "\n\n");

		resMat = grfMat.floydWarshall();
		System.out.println("Floyd Grafo Matriz: ");
		mostraMatriz(resMat);

		//******************
		//Para o K10
		//******************

		grfMat = createKGraph(10);
		System.out.println("\n\nGrafo Matriz: K10 ");
		System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);


		//******************
		//Para o K100
		//******************
		grfMat = createKGraph(100);
		System.out.println("\n\nGrafo Matriz: K100 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);

		//******************
		//Para o K200
		//******************
		grfMat = createKGraph(200);
		System.out.println("\n\nGrafo Matriz: K200 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);

		//******************
		//Para o K500
		//******************
		grfMat = createKGraph(500);
		System.out.println("\n\nGrafo Matriz: K500 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);

		//******************
		//Para o K1000
		//******************
		grfMat = createKGraph(1000);
		System.out.println("\n\nGrafo Matriz: K1000 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);

		//******************
		//Para o K10000
		//******************
		grfMat = createKGraph(10000);
		System.out.println("\n\nGrafo Matriz: K10000 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);

		//******************
		//Para o K100000
		//******************
		grfMat = createKGraph(100000);
		System.out.println("\n\nGrafo Matriz: K100000 ");
		//System.out.println(grfMat + "\n\n");

		tc.initCounter();
		resMat = grfMat.floydWarshall();
		tempo = tc.getCounter();
		System.out.println("\nFloyd: ");
		System.out.println("Tempo: " + tempo);
		//mostraMatriz(resMat);


	}

}
