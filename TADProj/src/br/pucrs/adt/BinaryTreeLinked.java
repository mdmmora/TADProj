package br.pucrs.adt;

import java.util.*;
import java.io.Serializable;


/**
 * Implementaóóo do TAD órvore Binória.
 * @author Michael Mora, Jólio Machado, Isabel Manssour
 */
public class BinaryTreeLinked <E extends Comparable<E>> implements BinaryTreeTAD<E>, Iterable<E>, Serializable {
	private final class Node {
		private Node father;
		private Node left;
		private Node right;
		private E item;		
		public Node(E element) {
			father = null;
			left = null;
			right = null;
			item = element;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node n) {
			right = n;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node n) {
			left = n;
		}
		public Node getFather() {
			return father;
		}
		public void setFather(Node n) {
			father = n;
		}
		public E getItem() {
			return item;
		}
		public void setItem(E element) {
			item = element;
		}
		public int compareTo(Node arg0) {
			return this.getItem().compareTo(arg0.getItem());
		}		
	}
	private int count; //quantidade de nodos
	private Node refRoot; //referóncia para o nodo raiz
	
	public BinaryTreeLinked() {
		count = 0;
		refRoot = null;
	}
	
	public BinaryTreeLinked(E [] vet)
	{
		List <Node> fila = new LinkedList<Node>();
		int i;
		Node novo = null, primeiro = null;
		NodePosition pos = null;

		this.count = vet.length;

		if (vet.length > 0)
		{
			novo = new Node(vet[0]);
			refRoot = novo;

			fila.add(0, novo);
			pos = NodePosition.LEFT;

			for(i = 1; i < vet.length; i++)
			{
				novo = new Node(vet[i]);
				fila.add(novo);

				primeiro = fila.get(0);
				novo.setFather(primeiro);

				if (pos.equals(NodePosition.LEFT))
				{
					primeiro.setLeft(novo);
					pos = NodePosition.RIGHT;
				}
				else
				{
					primeiro.setRight(novo);
					fila.remove(0);
					pos = NodePosition.LEFT;
				} 
			}
		}
	}

	public List<E> traversalPre()
	{
		List<E> res = new ArrayList<E>();
		traversalPre(refRoot, res);
		return res;
	}

	private void traversalPre(Node n, List<E> res)
	{
		if (n != null) {
			res.add(n.getItem()); //Visita o nodo
			traversalPre(n.getLeft(), res); //Visita a subárvore da esquerda
			traversalPre(n.getRight(), res); //Visita a subárvore da direita
		} 
	
	}

	public List<E> traversalPos()
	{
		List<E> res = new ArrayList<E>();
		traversalPos(refRoot, res);
		return res;
	}

	private void traversalPos(Node n, List<E> res)
	{
		if (n != null) {
			traversalPos(n.getLeft(), res); //Visita a subárvore da esquerda
			traversalPos(n.getRight(), res); //Visita a subárvore da direita
			res.add(n.getItem()); //Visita o nodo
		}
	}

	public List<E> traversalCentral()
	{
		List<E> res = new ArrayList<E>();
		traversalCentral(refRoot, res);
		return res;
	}

	private void traversalCentral(Node n, List<E> res)
	{
		if (n != null) {
			traversalCentral(n.getLeft(), res); //Visita a subárvore da esquerda
			res.add(n.getItem()); //Visita o nodo
			traversalCentral(n.getRight(), res); //Visita a subárvore da direita
		}
	}

	public List<E> traversalWidth()
	{
		List<Node> fila = new LinkedList<Node>();
		Node atual = null;
		List<E> res = new ArrayList<E>();
		if (refRoot != null) {
			fila.add(refRoot);
			while (!fila.isEmpty()) {
				atual = fila.remove(0);
				if (atual.getLeft() != null) {
					fila.add(atual.getLeft());
				}
				if (atual.getRight() != null) {
					fila.add(atual.getRight());
				}
				res.add(atual.getItem());				
			}
		}
		return res;
	}

	
	public Iterator<E> iterator() {
		Iterator<E> it = new IteratorPre();
		
		return it;
	}
	
	public Iterator<E> iterator(IteratorTreeType t){
		Iterator<E> it = null;
		
		if (t.equals(IteratorTreeType.WIDTH)) 
			it = new IteratorWidth();
		else if (t.equals(IteratorTreeType.POST))
			it = null;
		else if (t.equals(IteratorTreeType.PRE))
			it = new IteratorPre();
		else if (t.equals(IteratorTreeType.CENTRAL))
			it = null;
		
		return it;
		}
	
	public ReferenceBinTree<E> reference() {
		ReferenceBinTree<E> ref = new Reference();
		
		return ref;
	}
	
	public boolean add(E element, E father, NodePosition position)
	{
		Node n = new Node(element);
		Node nAux = null;
		boolean res = false;
		//Verifica se estó inserindo na raiz
		if (father == null) {
			if (position == NodePosition.LEFT)
				n.setLeft(refRoot);
			else 
				n.setRight(refRoot);
			//Atualiza o pai da raiz
			if (refRoot != null)
				refRoot.setFather(n);
			//Atualiza a raiz
			refRoot = n;
			res = true;
			count++;
		}
		//Insere no meio da órvore
		else {
			nAux = searchNodeRef(father, refRoot);
			if (nAux != null) {
				n.setFather(nAux);
				if (position == NodePosition.LEFT) {
					//Insere como subórvore da esquerda
					n.setLeft(nAux.getLeft());
					if (nAux.getLeft() != null)
						nAux.getLeft().setFather(n);
					nAux.setLeft(n);
				}
				else {
					//Insere como subórvore da direita
					n.setRight(nAux.getRight());
					if (nAux.getRight() != null)
						nAux.getRight().setFather(n);
					nAux.setRight(n);
				}
				res = true;
				count++;
			}
		}
		return res;
	}

	public boolean removeBranch(E element)
	{
		Node nAux = null;
		Node father = null;
		int qtdAux = 0;
		boolean rem = false;
		if (refRoot != null) {
			if (refRoot.getItem().equals(element)) {
				refRoot = null;
				count = 0;
				rem = true;
			}
			else {
				nAux = searchNodeRef(element, refRoot);
				if (nAux != null) {
					qtdAux = count(nAux);
					father = nAux.getFather();
					if (father.getLeft() == nAux) {
						father.setLeft(null);
					}
					else if (father.getRight() == nAux) {
						father.setRight(null);
					}
					count = count - qtdAux;
					rem = true;
				}
			}
		}
		return rem;
	}

	public E getRoot()
	{
		if(isEmpty())
			throw new EmptyTreeException();
		return refRoot.getItem();
	}

	public void setRoot(E element)
	{
		if(isEmpty())
			throw new EmptyTreeException();
		refRoot.setItem(element);
	}

	public E set(E old, E element) {
		E res = null;
		Node nAux = searchNodeRef(old, refRoot);
		if (nAux != null) {
			res = old;
			nAux.setItem(element);
		}
		return res;
	}

	public E getLeft(E element)
	{
		E res = null;
		Node nAux = searchNodeRef(element, refRoot);
		if(nAux != null) {
			if(nAux.getLeft()!=null) {
				res = nAux.getLeft().getItem();
			}
		}
		return res;
	}

	public E getRight(E element)
	{
		E res = null;
		Node nAux = searchNodeRef(element, refRoot);
		if(nAux != null) {
			if(nAux.getRight()!=null) {
				res = nAux.getRight().getItem();
			}
		}
		return res;
	}
	
	public E getFather(E element)
	{
		E res = null;
		Node nAux = searchNodeRef(element, refRoot);
		if(nAux != null) {
			if(nAux.getFather()!=null) {
				res = nAux.getFather().getItem();
			}
		}
		return res;
	}
	
	public boolean contains(E element)
	{
		Node nAux = searchNodeRef(element, refRoot);
		return (nAux != null);
	}
	
	private Node searchNodeRef(E element) {
		return searchNodeRef(element, refRoot);
	}
	
	private Node searchNodeRef(E element, Node target)
	{
		Node res = null;
		if ((target != null) && (element != null)) {
			if(target.getItem().equals(element)) {
				res = target;
			}
		    else {
		    	res = searchNodeRef(element, target.getLeft());
		    	if (res == null) {
		    		res = searchNodeRef(element, target.getRight());
		    	}
		    }
		}
		return res;
	}

	public boolean isEmpty()
	{
		return (refRoot == null);
	}

	public int size()
	{
		return count;
	}

	public int count()
	{
		return count(refRoot);
	}

	private int count(Node n)
	{
		if (n == null)
			return 0;
		else 
			return 1 + 
					count(n.getLeft()) + 
					count(n.getRight());
	}

	public int countLeaves() {
		return countLeaves(refRoot);
	}

	private int countLeaves(Node n) {
		int res = 0;
		
		if (n != null)		{
			if ((n.getLeft() == null) &&  (n.getRight() == null))
				res = 1;
			else 
				res = countLeaves(n.getLeft()) +  countLeaves(n.getRight());
		}
		return res;
	}

	public int countBranches() {
		return countBranches(refRoot);
	}

	private int countBranches(Node n) {
		int res = 0;
		
		if (n != null)		{
			if ( n.getFather()!=null && (n.getLeft()!=null || n.getRight()!=null) )
				res = 1 + countBranches(n.getLeft()) +  countBranches(n.getRight());
			else 
				res = countBranches(n.getLeft()) +  countBranches(n.getRight());
		}
		return res;
	}

	public int height () {
        return height(refRoot);
    }    
    
    private int height (Node n) {
        if (n == null) 
            return -1;
        else {
            int heightLeft = height(n.getLeft());
            int heightRight = height(n.getRight());
            if (heightLeft < heightRight) 
                return heightRight + 1;
            else 
                return heightLeft + 1;
        }
    }	
    
    public boolean isRoot(E element)
    {
    	if (refRoot != null)
            if (refRoot.getItem().equals(element))
            	return true;
         return false;
    }    
    
    public boolean isLeave(E element)
    {
        if (refRoot == null)
            return false;
            
        Node aux = searchNodeRef(element, refRoot);
        if (aux == null)
            return false;
        else if (aux.getLeft()==null && aux.getRight()==null)
            return true;
        else
            return false;
    }
    
    public boolean isBranch(E element)
    {
        if (refRoot == null)
            return false;
        else if (refRoot.getItem().equals(element))
        	return false;
        Node aux = searchNodeRef(element, refRoot);
        if (aux == null)
            return false;
        else if (aux.getLeft()!=null || aux.getRight()!=null)
            return true;
        else
            return false;
    }    
    
    public int level(E element)
    {
        Node aux = searchNodeRef(element, refRoot);
        if (aux == null)
            return -1;    
        Node father = aux.getFather();
        if (father == null)
            return 0;
        else
            return 1 + level(father.getItem());
    }      


	public String strTraversalPre()
	{
		return strTraversalPre(refRoot);
	}

	private String strTraversalPre(Node n)
	{
		String res = "";
		if (n != null)
		{
			res = n.getItem().toString() + "\n" +
				  strTraversalPre(n.getLeft()) +
				  strTraversalPre(n.getRight());
		}
		return res;
	}

	public String strTraversalPos()
	{
		return strTraversalPos(refRoot);
	}

	private String strTraversalPos(Node n)
	{
		String res = "";
		if (n != null)
		{
			res = strTraversalPos(n.getLeft()) +
				  strTraversalPos(n.getRight()) +
				  n.getItem().toString() + "\n";
		}
		return res;
	}

	public String strTraversalCentral()
	{
		return strTraversalCentral(refRoot);
	}

	private String strTraversalCentral(Node n)
	{
		String res = "";
		if (n != null)
		{
			res = strTraversalCentral(n.getLeft()) +
			      n.getItem().toString() + "\n" +
				  strTraversalCentral(n.getRight());
	
		}
		return res;
	}

	public String strTraversalWidth()
	{
		List<Node> fila = new LinkedList<Node>();
		Node atual = null;
		String res = "";
		if (refRoot != null) {
			fila.add(refRoot);
			while (!fila.isEmpty()) {
				atual = fila.remove(0);
				if (atual.getLeft() != null) {
					fila.add(atual.getLeft());
				}
				if (atual.getRight() != null) {
					fila.add(atual.getRight());
				}
				res = res + atual.getItem();				
			}
		}
		return res;
	}
	
	//***********************************************************
	//* Exercicios sobre estruturas em órvores. Nóo fazem parte 
	//* da interface padróo. Nóo entregar antes de passar o 
	//* exercócio.
	//***********************************************************

	public int count(E it)
	{
		return count(it, refRoot);
	}

	private int count(E it, Node n)
	{
		if ((n == null) || (it == null)) {
			return 0;
		}
		else if (it.equals(n.getItem()))
				return 1 + 
						count(it, n.getLeft()) + 
						count(it, n.getRight());
		else 
			return count(it, n.getLeft()) + 
					count(it, n.getRight());
	}
	
	public boolean search(E it) {
		return search(it, refRoot);
	}
	
	private boolean search(E element, Node target)
	{
		boolean res = false;
		if ((target != null) && (element != null)) {
			if(target.getItem().equals(element)) {
				res = true;
			}
		    else {
		    	res = search(element, target.getLeft());
		    	if (res == false) {
		    		res = search(element, target.getRight());
		    //Alternativa:
		    //   else res = (search(element, target.getLeft()) || 
		    //		         search(element, target.getRight());
		    //
		    //	
		    	}
		    }
		}
		return res;
	}
	
	public boolean hasRepeat(E el) { 
		int cont = 0;
		boolean res;
		if  (hasRepeat(el, refRoot, cont) <= 1)
			res = false;
		else
			res = true;
		
		return res;
	}
	
	private int hasRepeat(E el, Node n, int cont) {
		if (n != null) {
			if (el.equals(n.getItem()))
				cont ++;
			if (cont <= 1){
				cont += hasRepeat(el, n.getLeft(), cont);
				if (cont <= 1)
					cont += hasRepeat(el, n.getRight(), cont);
			}
		}
		
		return cont;
	}
	
	public List<E> allRepeat() {
		BinarySearchTreeLinked<E> primOcor = new BinarySearchTreeLinked<E>();
		BinarySearchTreeLinked<E> repetido = new BinarySearchTreeLinked<E>();
		allRepeat(refRoot, primOcor, repetido);
		return repetido.traversalCentral();
	}
	
	private void allRepeat(Node n, BinarySearchTreeLinked<E> primOcor, 
							BinarySearchTreeLinked<E> repetido){
		if (n != null) {
			if (!primOcor.contains(n.getItem()))
				primOcor.addRec(n.getItem());
			else if (!repetido.contains(n.getItem()))
					repetido.addRec(n.getItem());
			
			allRepeat(n.getLeft(), primOcor, repetido);
			allRepeat(n.getRight(), primOcor, repetido);
		}
				
	}
	
	public void invLeftRight() {
		invLeftRight(refRoot);
	}
	
	private void invLeftRight(Node n){
		Node aux;
		
		if (n != null) {
			aux = n.getLeft();
			n.setLeft(n.getRight());
			n.setRight(aux);
			
			invLeftRight(n.getLeft());
			invLeftRight(n.getRight());
		}
	}
	
	public List<E> getInternalElelments() {
		return getInternalElements(refRoot, new ArrayList<E>());
	}
	
	private List<E> getInternalElements(Node n, List<E> lst) {
		if (n != null) {
			if ((n != refRoot) && 
				((n.getLeft() != null) || (n.getRight() != null)))
				lst.add(n.getItem());
			
			lst = getInternalElements(n.getLeft(), lst);
			lst = getInternalElements(n.getRight(), lst);
		}
		
		return lst;
	}
	
	public int getPathSize(E element) {
		int res = -1;
		
		Node aux = getNodetoPathSize(element, refRoot);
		
		if (aux != null)
			while (aux != null) {
				aux = aux.getFather();
				res++;
			}
		
		return res;
	}
	
	private Node getNodetoPathSize(E element, Node n){
		Node res = null;
		
		if (n != null)
			if (element.equals(n.getItem()))
				res = n;
			else {
				res = getNodetoPathSize(element, n.getLeft());
				
				if (res == null)
					res = getNodetoPathSize(element, n.getRight());
			}
		
		return res;
	}
	
	
	private final class IteratorWidth implements IteratorBinTree<E> {
	
		private List<Node> fila = new LinkedList<Node>();
		
		public IteratorWidth() {
			if (refRoot != null)
				fila.add(refRoot);
			else
				throw new EmptyTreeException();
		}
		
		public boolean hasNext() {
			boolean res = false;
			
			if (!fila.isEmpty())
				res = true;
			
			return res;
		}
	
		public E next() {
			E res = null;
			Node aux;
			
			if (fila.get(0) != null){
				aux = fila.remove(0);
				
				if (aux.getLeft() != null)
					fila.add(aux.getLeft());
				if (aux.getRight() != null)
					fila.add(aux.getRight());
				
				res = aux.getItem();
			}
			
			return res;
		}
	
		public void remove() {
			throw new UnsupportedOperationException();
			}
	
		public E getFather() {
			E res = null;
			Node aux;
			
			if (!fila.isEmpty()) {
				aux = fila.get(0);
				
				if (aux.getFather() != null)
					res = aux.getFather().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getElem() {
			E res = null;
			Node aux;
			
			if (!fila.isEmpty()) {
				aux = fila.get(0);
				
				res = aux.getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getLeft() {
			E res = null;
			Node aux;
			
			if (!fila.isEmpty()) {
				aux = fila.get(0);
				
				if (aux.getLeft() != null)
					res = aux.getLeft().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getRight() {
			E res = null;
			Node aux;
			
			if (!fila.isEmpty()) {
				aux = fila.get(0);
				
				if (aux.getRight() != null)
					res = aux.getRight().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
				
	}
	
	private final class IteratorPre implements IteratorBinTree<E> {
	
		private List<Node> pilha = new LinkedList<Node>();
		
		public IteratorPre() {
			if (refRoot != null)
				pilha.add(0,refRoot);
		}
		
		public boolean hasNext() {
			boolean res = false;
			
			if (!pilha.isEmpty())
				res = true;
			
			return res;
		}
	
		public E next() {
			E res = null;
			Node aux;
			
			if (pilha.get(0) != null){
				aux = pilha.remove(0);
				
				if (aux.getRight() != null)
					pilha.add(0,aux.getRight());
				if (aux.getLeft() != null)
					pilha.add(0, aux.getLeft());
				
				res = aux.getItem();
			}
			
			return res;
		}
	
		public void remove() {
			throw new UnsupportedOperationException();
			}
	
		public E getFather() {
			E res = null;
			Node aux;
			
			if (!pilha.isEmpty()) {
				aux = pilha.get(0);
				
				if (aux.getFather() != null)
					res = aux.getFather().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getElem() {
			E res = null;
			Node aux;
			
			if (!pilha.isEmpty()) {
				aux = pilha.get(0);
				
				res = aux.getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getLeft() {
			E res = null;
			Node aux;
			
			if (!pilha.isEmpty()) {
				aux = pilha.get(0);
				
				if (aux.getLeft() != null)
					res = aux.getLeft().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
	
		public E getRight() {
			E res = null;
			Node aux;
			
			if (!pilha.isEmpty()) {
				aux = pilha.get(0);
				
				if (aux.getRight() != null)
					res = aux.getRight().getItem();
			}
			else
				throw new EmptyTreeException();
			
			return res;
		}
				
	}
	
	private final class Reference implements ReferenceBinTree<E> {
		private Node ref;
		
		public Reference(){
			ref = refRoot;
		}
		
		public Reference(Node r){
			ref = r;
		}
		
		public boolean add(E elem, NodePosition position) {
			Node n = new Node(elem);
			Node nAux = null;
			boolean res = false;
			
			//Verifica se estó inserindo na raiz
			if ((ref == null) || (ref == refRoot)) {
				if (position == NodePosition.LEFT)
					n.setLeft(refRoot);
				else 
					n.setRight(refRoot);
				//Atualiza o pai da raiz
				if (refRoot != null)
					refRoot.setFather(n);
				//Atualiza a raiz
				refRoot = n;
				res = true;
				count++;
			}
			//Insere no meio da órvore
			else {
				nAux = ref;
				if (nAux != null) {
					n.setFather(nAux);
					if (position == NodePosition.LEFT) {
						//Insere como subórvore da esquerda
						n.setLeft(nAux.getLeft());
						if (nAux.getLeft() != null)
							nAux.getLeft().setFather(n);
						nAux.setLeft(n);
					}
					else {
						//Insere como subórvore da direita
						n.setRight(nAux.getRight());
						if (nAux.getRight() != null)
							nAux.getRight().setFather(n);
						nAux.setRight(n);
					}
					res = true;
					count++;
				}
			}
			return res;
		}

		public ReferenceBinTree<E> search(E elem) {
			Node res = searchNodeRef(elem, refRoot);
			
			return new Reference(res);
		}

		public ReferenceBinTree<E> goLeft() {
			Reference res = null;
			
			if ((ref != null) && (ref.getLeft() != null))
				res = new Reference(ref.getLeft());
			
			return res;
		}

		public ReferenceBinTree<E> goRight() {
			Reference res = null;
			
			if ((ref != null) && (ref.getRight() != null))
				res = new Reference(ref.getRight());
			
			return res;
		}

		public ReferenceBinTree<E> goFather() {
			Reference res = null;
			
			if ((ref != null) && (ref.getFather() != null))
				res = new Reference(ref.getFather());
			
			return res;
		}

		public ReferenceBinTree<E> goRoot() {
			Reference res = null;
			
			res = new Reference(refRoot);
			
			return res;
		}

		public boolean hasLeft() {
			boolean res = true;
			
			if ((ref == null) || (ref.getLeft() == null))
			return false;
			
			return res;
		}

		public boolean hasRight() {
			boolean res = true;
			
			if ((ref == null) || (ref.getRight() == null))
			return false;
			
			return res;
		}

		public boolean hasFather() {
			boolean res = true;
			
			if ((ref == null) || (ref.getFather() == null))
			return false;
			
			return res;
		}

		public E getItem() {
			E res = null;
			
			if (ref != null)
				res = ref.getItem();
				
			return res;
		}

		public boolean setItem(E item) {
			boolean res = true;
			
			if (ref != null)
				ref.setItem(item);
			else
				res = false;
				
			return res;
		}
		
	}
		
}

