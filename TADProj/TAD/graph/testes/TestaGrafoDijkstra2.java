package TAD.graph.testes;

import br.pucrs.adt.DijkstraResult;
import br.pucrs.adt.GrafoDirRotLisAdj;
import br.pucrs.adt.GrafoDirRotMatAdj;
public class TestaGrafoDijkstra2 {

	public static void main(String[] args) {
		GrafoDirRotMatAdj<String, Double> grfMat = new GrafoDirRotMatAdj<>(20);
		DijkstraResult<String> resMat, resLis;

		grfMat.addNode("N1");
		grfMat.addNode("N2");
		grfMat.addNode("N3");
		grfMat.addNode("N4");
		grfMat.addNode("N5");
		grfMat.addNode("N6");

		grfMat.addEdge("N1", "N2", 2000.0);
		grfMat.addEdge("N1", "N3", 5.0);
		grfMat.addEdge("N2", "N4", 10.0);
		grfMat.addEdge("N3", "N1", 7.0);
		grfMat.addEdge("N4", "N5", 8.0);
		grfMat.addEdge("N5", "N2", 6.0);

		System.out.println("Grafo Matriz: ");
		System.out.println(grfMat + "\n\n");


		System.out.println(grfMat.dijkstraMinimumPath_v1("N1"));
		resMat = grfMat.dijkstraMinimumPath_v1("N1");
		System.out.println(resMat);

		System.out.println("Resultado do Dijkstra Matriz(Bruto)");
		System.out.println(resMat + "\n\n");


		System.out.println("Para N2: " + resMat.getPath("N2"));
		System.out.println("    Custo: " + resMat.getCost("N2"));

		System.out.println("Para N3: " + resMat.getPath("N3"));
		System.out.println("    Custo: " + resMat.getCost("N3"));

		System.out.println("Para N4: " + resMat.getPath("N4"));
		System.out.println("    Custo: " + resMat.getCost("N4"));

		System.out.println("Para N5: " + resMat.getPath("N5"));
		System.out.println("    Custo: " + resMat.getCost("N5"));
	}

}
