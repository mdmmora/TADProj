package br.pucrs.adt;

import java.util.*;


/******
 * Implementaóóo de um Grafo Dirigido e Rotulado utilizando Matriz de Adjacóncias.
 * Esta implementaóóo suporta apenas uma aresta entre um determinado par de nodos.
 * @param N - Genórico. Tipo do nodo do grafo
 * @param A - Genórico. Tipo do rótulo da aresta do grafo
 * @author CAA
 * @version 1.0
 */

public class GrafoDirRotMatAdj<N, A> {
	private class Node <T> {
		private T elem;
		private boolean marked = false;

		public Node(T n) {
			this.elem = n;
			unMark();
		}
		public T getElem() {
			return elem;
		}
		public void setElem(T elem) {
			this.elem = elem;
		}
		public boolean isMarked() {
			return marked;
		}
		public void mark() {
			this.marked = true;
		}

		public void unMark() {
			this.marked = false;
		}

		public String toString(){
			return elem.toString();
		}
	}

	public final class PQNodeDijkstra<D, C extends Comparable<C>> implements Comparable<PQNodeDijkstra<D, C>> {
		private D nodeLocation;
		private C pathLength;

		public PQNodeDijkstra(D p, C w) {
			nodeLocation = p;
			pathLength = w;
		}

		public D getNodePos() {
			return nodeLocation;
		}

		public void setNodePos(D nodePos) {
			this.nodeLocation = nodePos;
		}

		public C getNodeWeight() {
			return pathLength;
		}

		public void setNodeWeight(C nodeWeight) {
			this.pathLength = nodeWeight;
		}

		public int compareTo(PQNodeDijkstra<D, C> e) {

			return this.getNodeWeight().compareTo(e.getNodeWeight());
		}

		public boolean equals(PQNodeDijkstra<D, C> e){
			return this.getNodePos().equals(e.getNodePos());
		}
	}

	private int max;
	private List<Node<N>> nodeList;
	private A [][] matAdj;

	public GrafoDirRotMatAdj(int n) {
		if (n > 0)
			max = n;
		else
			max = 300;

		nodeList = new ArrayList<Node<N>>(max);
		matAdj = (A[][]) new Object[max][max];
	}


	public int indexOfNode(N elem) {
		int res = -1;

		for (int i = 0; i < nodeList.size() && res == -1; i++)
			if (nodeList.get(i).getElem().equals(elem))
				res = i;

		return res;
	}

	/* (non-Javadoc)
	 * @see TAD.graph.GraphTAD#addNode(N)
	 */
	public void addNode(N elem) {
		Node<N> novo = new Node<N>(elem);

		if (nodeList.size() < max)
			if (this.indexOfNode(elem) == -1)
				nodeList.add(novo);
			else
				throw new IllegalArgumentException();
		else
			throw new OutOfMemoryError();
	}

	/* (non-Javadoc)
	 * @see TAD.graph.GraphTAD#addEdge(N, N, A)
	 */
	public void addEdge(N orig, N dest, A rot) {
		int posOrig = this.indexOfNode(orig);
		int posDest = this.indexOfNode(dest);

		if (posOrig == -1 || posDest == -1)
			throw new IllegalArgumentException("Invalide Node: orig");
		else if (matAdj[posOrig][posDest] == null)
			matAdj[posOrig][posDest] = rot;
		else
			throw new IllegalArgumentException("Invalid Label: dest");
	}

	private List<Node<N>> getAllNeighbours(N elem){

		List<Node<N>> res = new ArrayList<Node<N>>();
		int index = this.indexOfNode(elem);

		if (index < 0)
			throw new IllegalArgumentException("Invalid Node");
		else {
			for (int i = 0; i < nodeList.size(); i++)
				if (matAdj[index][i] != null)
					res.add(nodeList.get(i));
		}

		return(res);
	}

	private List<Integer> getAllNeighbours(int nodePos){

		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < nodeList.size(); i++)
			if (matAdj[nodePos][i] != null)
				res.add(i);

		return(res);

	}

	public void markAllNodes() {
		for (Node<N> n:nodeList)
			n.mark();
	}

	public void unMarkAllNodes() {
		for (Node<N> n:nodeList)
			n.unMark();
	}

	public List<N> pathFromTo(N orig, N dest) {
		int index, index1;

		this.unMarkAllNodes();
		index = this.indexOfNode(orig);
		index1 = this.indexOfNode(dest);
		if ((index == -1) || (index1 == -1))
			throw new IllegalArgumentException("InvNode");
		else {
			return this.pathFromToAux(orig, dest);
		}
	}
	private List<N> pathFromToAux(N orig, N dest) {
		List<N> res = null;

		if (orig.equals(dest)){
			res = new ArrayList<N>();
			res.add(dest);
			return(res);
		}
		int pos = indexOfNode(orig);
		nodeList.get(pos).mark();
		for(Node<N> v:this.getAllNeighbours(orig)) {
			if (! v.isMarked()) {
					res = pathFromToAux(v.getElem(), dest);

					if (res != null) {
						res.add(0, orig);
						return(res);
					}
				}
			}
		return(res);
	}

	public boolean containsNode(N n) {
		boolean res = nodeList.contains(n);
		return res;
	}

	/* *********************************
	Algo: dfs
	Entr: Nodo orig
	Said: Lista de Nodos

	Lista result;
	DESMARCA TODOS OS NODOS;
	dfsAux(orig, result);
	RETORNA result;
	 ***********************************
	Algo: dfsAux
	Entr: Nodo orig, Lista de Nodos result
	Said: NULL

	result.add(orig);
	orig.marca();

	PARA CADA VIZINHO NóO MARCADO v DE orig
	FAóA dfsAux(v, result);
	 ***********************************/

	/* (non-Javadoc)
	 * @see TAD.graph.GraphTAD#dfs(N)
	 */

	public List<N> traversalDepth(N orig) {
		List<N> result = new ArrayList<N>();
		int index;

		index = this.indexOfNode(orig);
		if (index == -1)
			throw new IllegalArgumentException("InvNod");
		else {
			this.unMarkAllNodes();
			traversalDepth(nodeList.get(index), result);
		}
		return result;
	}

	private void traversalDepth(Node<N> orig, List<N> result) {
		result.add(orig.getElem());
		orig.mark();

		for(Node<N> v:getAllNeighbours(orig.getElem()))
			if (! v.isMarked())
				traversalDepth(v, result);
	}

	/* *********************************
	Algo: wfs
	Entr: Nodo orig
	Said: Lista de Nodos

	Fila fAux, result;
	Nodo nAux;

	DESMARCA TODOS OS NODOS;
	fAux.offer(orig);
	orig.marca();

	ENQUANTO (fAux nóo estó vazia)
	FAóA {
	      nAux = fAux.poll();
	      result.enqueue(nAux);

	      PARA CADA VIZINHO NóO MARCADO v DE nAUX
	      FAóA {v.marca();
	      		fAux.offer(v);}
	     }
	 RETORNA result
	 **********************************/

	/* (non-Javadoc)
	 * @see TAD.graph.GraphTAD#wfs(N)
	 */
//  ==> To review
//
//	public List<N> traversalWidth(N orig) {
//		List<N> result = new ArrayList<N>();
//		Queue<Node<N>> fAux = new LinkedList<Node<N>>();
//		Node<N> nAux;
//		int index;
//
//		index = this.indexOfNode(orig);
//		if (index == -1)
//			throw new IllegalArgumentException("InvNod");
//		else {	this.unMarkAllNodes();
//
//		result.add(nodeList.get(index).getElem());
//		fAux.offer(nodeList.get(index));
//		nodeList.get(index).mark();
//
//		while(!fAux.isEmpty()) {
//			nAux = fAux.poll();
//
//			for(Node<N> v:this.getAllNeighbours(nAux.getElem()))
//				if (!v.isMarked()) {
//					result.add(v.getElem());
//					v.mark();
//					fAux.offer(v);  }
//		}
//		}
//		return result;
//	}

	/**********************************
	Algo: Dijkstra  -  SSSP(G, v)
	Entrada: G - grafo nóo dirigido e valorado, com valores numóricos nóo negativos
	               v -  nodo de origem
	Saóda: Um valor D[u], para cada vórtice u de G, tal que D[u]
		ó o tamanho do menor caminho de v ató u em G

	Inicialize D[v] = 0 e D[u] = +infinito para cada u!=v
	FilaPrioridade Q = todos os vórtices u de G usando os labels D[u] como chave

	ENQUANTO Q nóo vazia {
		u  = Q.removeMin();

		PARA CADA vórtice z adjacente ó u tal que z estó em Q
		FAóA {
			SE D[u] + valor((u,z))  <  D[z]
			ENTóO D[z] = D[u] + valor((u,z))

			Troque para D[z] a chave do vórtice z em Q
		      }
		}
	RETORNE o rótulo D[u] de cada vórtice u
	 **********************************/
//==> To review
//
//	public DijkstraResult<N> dijkstraMinimumPath_v1(N orig){
//		DijkstraResult<N> res = new DijkstraResult<N>(orig);
//		PriorityQueueTAD<PQNodeDijkstra<Integer, Double>> queue = new PriorityQueue<PQNodeDijkstra<Integer, Double>>(nodeList.size());
//		int indexOrig = this.indexOfNode(orig);
//		PQNodeDijkstra<Integer, Double> uNode;
//
//		//Inicializa a tabela de pesos e
//		//Inicializa Priority Queue. Nesta implementaóóo
//		//  os pesos estóo junto do elemento na PriorityQueue, alóm de
//		//  estarem em um ArrayList separado
//		//
//		//No algoritmo:
//		//Inicialize D[v] = 0 e D[u] = +infinito para cada u!=v
//		//FilaPrioridade Q = todos os vórtices u de G usando os labels D[u] como chave
//		for (int i = 0; i < nodeList.size(); i++)
//			if (i != indexOrig) {
//				queue.offer(new PQNodeDijkstra<Integer, Double>(i, Double.MAX_VALUE));
//				res.addNode(nodeList.get(i).getElem(), null, Double.MAX_VALUE);
//			}
//			else {
//				queue.offer(new PQNodeDijkstra<Integer, Double>(i, 0.0));
//				res.addNode(nodeList.get(i).getElem(), null,0.0);
//			}
//
//		while (!queue.isEmpty()) {
//			uNode = queue.poll();
//
//			for (int colAdjacente = 0; colAdjacente < nodeList.size(); colAdjacente++)    // Percorrendo a coluna da Matriz para achar adjacentes
//				if (matAdj[uNode.getNodePos()][colAdjacente] != null)   //    Achou uma aresta -> ó adejacente.
//					for (PQNodeDijkstra<Integer, Double> n:queue)  //  Verificando se o adjacente estó la fila de nodos nóo resolvidos
//						if (n.getNodePos() == colAdjacente) {  // Sim, estó! Tenta relaxar o nodo -> acertar peso e nodo anterior, se for o caso.
//							Double pesoNodoCorrente = uNode.getNodeWeight();
//							int posicaoNodoCorrente = uNode.getNodePos();
//							N elementoNodoAdjacente = nodeList.get(colAdjacente).getElem();
//							N elementoNodoCorrente = nodeList.get(uNode.getNodePos()).getElem();
//
//							if ((pesoNodoCorrente + (Double)matAdj[posicaoNodoCorrente][colAdjacente]) <
//								res.getCost(elementoNodoAdjacente)){
//
//								queue.remove(new PQNodeDijkstra<Integer, Double>(colAdjacente, -1.0));
//								res.setNode(elementoNodoAdjacente, elementoNodoCorrente,
//												pesoNodoCorrente + (Double)matAdj[posicaoNodoCorrente][colAdjacente]);
//								queue.offer(new PQNodeDijkstra<Integer, Double>(colAdjacente,
//																				res.getCost(elementoNodoAdjacente)));
//
//							}
//							break;
//						}
//		}
//
//		return res;
//	}


	private double edgeCost(int i, int j) {
		double res;

		if ((i == j) && (matAdj[i][j] == null))
			res = 0;
		else if (matAdj[i][j] == null)
			res = Double.MAX_VALUE;
		else
			res = (Double) matAdj[i][j];

		return res;
	}

	public double [][] floydWarshall() {
		double [][]path = new double [nodeList.size()] [nodeList.size()];

		for (int i = 0; i < nodeList.size(); i++)
			for (int j = 0; j < nodeList.size(); j++)
				path[i][j] = edgeCost(i,j);

		System.out.print("\n*");	     //<<<<<------ Apenas para ex de complexidade. Comentar!
		int nDots = 0;				     //<<<<<------ Apenas para ex de complexidade. Comentar!
		for (int k = 0; k < nodeList.size(); k++) {
			System.out.print(".");       //<<<<<------ Apenas para ex de complexidade. Comentar!
			if (nDots >= 150){		     //<<<<<------ Apenas para ex de complexidade. Comentar!
				nDots = 0;			     //<<<<<------ Apenas para ex de complexidade. Comentar!
				System.out.println("");  //<<<<<------ Apenas para ex de complexidade. Comentar!
			}
			else
				nDots++;	   			 //<<<<<------ Apenas para ex de complexidade. Comentar!

			for (int i = 0; i < nodeList.size(); i++)
				for (int j = 0; j < nodeList.size(); j++)
					if (path[i][j] > path[i][k] + path[k][j])
						path[i][j] = path[i][k] + path[k][j];
		}

		return path;
	}


	public String toString(){
		String res = "";
		for (int i = 0; i < nodeList.size(); i++){
			res = res + nodeList.get(i).getElem() + "\n";

			for (int j = 0; j < nodeList.size(); j++)
				if (matAdj[i][j] != null)
				res = res + "(nodo=" +  nodeList.get(j).getElem() + "," +
						"aresta=" + matAdj[i][j].toString() + ") ";

			res = res + "\n";
		}
		return res;
	}


	//***********************************************************
	//* Exercicios sobre estruturas em grafos. Nóo fazem parte
	//* da interface padróo. Nóo entregar antes de passar o
	//* exercócio.
	//***********************************************************

	public int countReachable(N elem) {
		int res = 0;
		int nodePos;

		unMarkAllNodes();
		nodePos = indexOfNode(elem);
		if (nodePos == -1)
			throw new IllegalArgumentException("Invalid Node");
		else
			return countReachable(nodePos, res);
	}

	private int countReachable(int nodePos, int count){

		nodeList.get(nodePos).mark();

		for (int n:getAllNeighbours(nodePos))
			if (!nodeList.get(n).isMarked())
				count = 1 + countReachable(n, count);

		return count;
	}


	public List<N> getTwoLevelsAhead(N elem) {

		List<N> res = new ArrayList<N>();
		unMarkAllNodes();
		int nElem = indexOfNode(elem);
		nodeList.get(nElem).mark();
		//Pega adjacentes de elem
		for(int i = 0; i < nodeList.size(); i++)
			if (matAdj[nElem][i] != null)
				if (!nodeList.get(i).isMarked()){
					nodeList.get(i).mark();
					res.add(nodeList.get(i).getElem());
				}
		//Pega adjacentes dos adjacentes
		int tam = res.size();
		for (int i = 0; i < tam; i++) {
			nElem = indexOfNode(res.get(i));

			for(int j = 0; j < nodeList.size(); j++)
				if (matAdj[nElem][i] != null)
					if (!nodeList.get(i).isMarked()){
						nodeList.get(i).mark();
						res.add(nodeList.get(i).getElem());
					}  }
		return res;
	}
}
