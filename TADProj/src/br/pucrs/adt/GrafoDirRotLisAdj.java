package br.pucrs.adt;

import java.util.*;

import br.pucrs.adt.GrafoDirRotMatAdj.PQNodeDijkstra;



/******
 * Implementaóóo de um Grafo Dirigido e Rotulado utilizando Lista de Adjacóncias.
 * Esta implementaóóo suporta apenas uma aresta entre um determinado par de nodos.
 * @param N - Genórico. Tipo do nodo do grafo
 * @param A - Genórico. Tipo do rótulo da aresta do grafo
 * @author CAA
 * @version 1.0
 */

public class GrafoDirRotLisAdj<N, A> {
	private static final class Edge<M, R> {
		private R label;
		private Node<M, R> dest;

		public Edge(Node<M,R> n, R rot) {
			if ((n != null) && (rot !=null)){
				label = rot;
				dest = n;
			}
			else
				throw new IllegalArgumentException();

		}
		public R getLabel() {
			return label;
		}
		public void setLabel(R label) {
			this.label = label;
		}
		public Node<M, R> getDest() {
			return dest;
		}
		public void setDest(Node<M, R> dest) {
			this.dest = dest;
		}
	}
	private static final class Node <M, R> {
		private M elem;
		private boolean marked = false;
		private List<Edge<M, R>> lstAdj;

		public Node(M n) {
			this.elem = n;
			lstAdj = new LinkedList<Edge<M, R>>();
			unMark();
		}
		public M getElem() {
			return elem;
		}
		public void setElem(M elem) {
			this.elem = elem;
		}
		public int indexOfNode(M elem) {
			int res = -1;

			for (int i = 0; i < lstAdj.size() && res == -1; i++)
				if (lstAdj.get(i).getDest().getElem().equals(elem))
					res = i;

			return res;
		}

		public void addAdjNode(Node<M, R> n, R rot){
			Edge<M, R> edge;

			int pos = indexOfNode(n.getElem());

			if (pos == -1) {
				edge = new Edge<M, R>(n, rot);
				lstAdj.add(edge);
			}
			else
				throw new IllegalArgumentException("Node already adjacent.");
		}

		public List<Edge<M, R>> getLstAdj() {
			return lstAdj;
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

	private List<Node<N,A>> nodeList;

	public GrafoDirRotLisAdj() {
		nodeList = new LinkedList<Node<N,A>>();
	}

	public int indexOfNode(N elem) {
		int res = -1;

		for (int i = 0; i < nodeList.size() && res == -1; i++)
			if (nodeList.get(i).getElem().equals(elem))
				res = i;

		return res;
	}

	public void addNode(N elem) {
		Node<N, A> newNode;

		if (this.indexOfNode(elem) == -1) {
			newNode = new Node<N, A>(elem);
			nodeList.add(newNode);
		}
		else
			throw new IllegalArgumentException("Node already exists");
	}

	public void addEdge(N orig, N dest, A rot) {
		int posOrig = this.indexOfNode(orig);
		int posDest = this.indexOfNode(dest);
		int adj;

		if (posOrig == -1 || posDest == -1)
			throw new IllegalArgumentException("Invalide Node: orig");
		else  if (rot != null) {
			adj = nodeList.get(posOrig).indexOfNode(dest);
			if (adj != -1)
				throw new  IllegalArgumentException("Invalide Node: dest");
			else {
				Node<N,A> nodeDest = nodeList.get(posDest);
				Edge<N,A> newEdge = new Edge<N, A>(nodeDest, rot);
				nodeList.get(posOrig).addAdjNode(nodeDest, rot);
			}
		}
		else
			throw new IllegalArgumentException("Invalid Label");
	}

	private List<Node<N, A>> getAllAdjacents(N elem){
		List<Node<N, A>> res = new ArrayList<Node<N, A>>();
		int index = this.indexOfNode(elem);

		if (index < 0)
			throw new IllegalArgumentException("Invalid Node");
		else {
			List<Edge<N,A>> lstAux = nodeList.get(index).getLstAdj();
			for (int i = 0; i < lstAux.size(); i++)
				res.add(lstAux.get(i).getDest());
		}

		return(res);
	}

	private void markAllNodes() {
		for (Node<N, A> n:nodeList)
			n.mark();
	}

	private void unMarkAllNodes() {
		for (Node<N, A> n:nodeList)
			n.unMark();
	}

	/***********************
	Algo: caminhoAB
	      tambóm chamada "singleSourceSingleDest"
	Entr: Nodo orig, Nodo dest
	Said: Lista de Nodos

	DESMARQUE TODOS OS NODOS     <<--
	MARQUE O NODO orig
	RETORNE caminhoABAux(orig, dest)
	----------------------------
	Algo: caminhoABAux
	Entr: Nodo orig, Nodo dest
	Said: Lista de Nodos

	Lista res = [];

	PARA CADA NODO v VIZINHO DE orig NóO MARCADO   <<--
	FAóA {MARQUE v COMO VISITADO
	      SE (v == dest)
	      ENTóO { res.addFisrt(orig);
	       		  res.addLast(v);
	              RETORNE (res);
	            }
	      SENóO {res = caminhoABAux(v, dest);
	             SE (res NóO ESTó VAZIA)
	             ENTóO {res.addFirst(orig);
	                   RETORNE (res);}
	            }
	RETORNE (res);
	 ************************/
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
		for(Node<N, A> v:this.getAllAdjacents(orig)) {
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

	/**********************************
	Algo: traversalDepth
	Entr: Nodo orig
	Said: Lista de Nodos

	Lista result;
	DESMARCA TODOS OS NODOS;
	dfsAux(orig, result);
	RETORNA result;
	 ***********************************
	Algo: traversalDepthAux
	Entr: Nodo orig, Lista de Nodos result
	Said: NULL

	result.add(orig);
	orig.marca();

	PARA CADA VIZINHO NóO MARCADO v DE orig
	FAóA traversalDepthAux(v, result);
	 ***********************************/

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

	private void traversalDepth(Node<N, A> orig, List<N> result) {
		result.add(orig.getElem());
		orig.mark();

		for(Node<N, A> v:getAllAdjacents(orig.getElem()))
			if (! v.isMarked())
				traversalDepth(v, result);
	}

	/**********************************
	Algo: traversalWidth
	Entr: Nodo orig
	Said: Lista de Nodos

	Fila fAux, result;
	Nodo nAux;

	DESMARCA TODOS OS NODOS;
	fAux.enqueue(orig);
	orig.marca();

	ENQUANTO (fAux.first() != null)
	FAóA {
	      nAux = fAux.dequeue();
	      result.enqueue(nAux);

	      PARA CADA VIZINHO NóO MARCADO v DE nAUX
	      FAóA {v.marca();
	      		fAux.enqueue(v);}
	     }
	 RETORNA result
	 **********************************/

	public List<N> traversalWidth(N orig) {
		List<N> result = new ArrayList<N>();
		List<Node<N, A>> fAux = new LinkedList<Node<N, A>>();
		Node<N, A> nAux;
		int index;

		index = this.indexOfNode(orig);
		if (index == -1)
			throw new IllegalArgumentException("InvNod");
		else {
			this.unMarkAllNodes();

			result.add(nodeList.get(index).getElem());
			fAux.add(nodeList.get(index));
			nodeList.get(index).mark();

			while(!fAux.isEmpty()) {
				nAux = fAux.remove(0);

				for(Node<N, A> v:this.getAllAdjacents(nAux.getElem()))
					if (!v.isMarked()) {
						result.add(v.getElem());
						v.mark();
						fAux.add(v);
					}
			}
		}

		return result;
	}

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
// ====> to review
	
//	public DijkstraResult<N> dijkstraMinimumPath_v1(N orig){
//		DijkstraResult<N> res = new DijkstraResult<N>(orig);
//		PriorityQueueTAD<PQNodeDijkstra<Node<N, A>, Double>> queue = new PriorityQueue<PQNodeDijkstra<Node<N,A>, Double>>(nodeList.size());
//		int indexOrig = this.indexOfNode(orig);
//		PQNodeDijkstra<Node<N,A>, Double> uNode;
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
//				queue.offer(new PQNodeDijkstra<Node<N,A>, Double>(nodeList.get(i), Double.MAX_VALUE));
//				res.addNode(nodeList.get(i).getElem(), null, Double.MAX_VALUE);
//			}
//			else {
//				queue.offer(new PQNodeDijkstra<Node<N,A>, Double>(nodeList.get(i), 0.0));
//				res.addNode(nodeList.get(i).getElem(), null,0.0);
//			}
//
//		while (!queue.isEmpty()) {
//			uNode = queue.poll();
//			Double pesoNodoCorrente = uNode.getNodeWeight();
//			Node<N,A> nodoCorrente = uNode.getNodePos();
//
//			for (Edge<N, A> edg:nodoCorrente.getLstAdj()) //Percorre a lista de adjacentes do nodo corrente
//				for (PQNodeDijkstra<Node<N,A>, Double> n:queue)  //  Verificando se o adjacente estó la fila de nodos nóo resolvidos
//					if (n.getNodePos().getElem().equals(edg.getDest().getElem())) {  // Sim, estó! Tenta relaxar o nodo -> acertar peso e nodo anterior, se for o caso.
//						N elementoNodoAdjacente = edg.getDest().getElem();
//						N elementoNodoCorrente = uNode.getNodePos().getElem();
//
//						if ((pesoNodoCorrente + (Double)edg.getLabel()) < res.getCost(elementoNodoAdjacente)){
//
//							queue.remove(new PQNodeDijkstra<Node<N,A>, Double>(n.getNodePos(), -1.0));
//							res.setNode(elementoNodoAdjacente, elementoNodoCorrente, pesoNodoCorrente + (Double)edg.getLabel());
//							queue.offer(new PQNodeDijkstra<Node<N,A>, Double>(n.getNodePos(), res.getCost(elementoNodoAdjacente)));
//
//						}
//						break;
//					}
//		}
//
//		return res;
//	}


	public String toString(){
		String res = "";
		for (int i = 0; i < nodeList.size(); i++){
			res = res + nodeList.get(i).getElem() + "\n";

			for (int j = 0; j < nodeList.get(i).getLstAdj().size(); j++)
				res = res + "(nodo=" +  nodeList.get(i).getLstAdj().get(j).getDest().getElem() + "," +
						"aresta=" + nodeList.get(i).getLstAdj().get(j).getLabel() + ") ";

			res = res + "\n";
		}
		return res;
	}

}
