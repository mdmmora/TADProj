package br.pucrs.adt;

import java.util.Iterator;
import java.util.List;

/**
 * Interface que descreve o TAD �rvore Bin�ria de Pesquisa.
 * @author Michael Mora, J�lio Machado, Isabel Manssour
 */

public interface BinarySearchTreeTAD<E extends Comparable<E>> {
	/**
	 * Verifica se �rvore est� vazia.
	 * @return true se a �rvore est� vazia, false caso contr�rio. 
	 */
	boolean isEmpty();
	
	/**
	 * Recupera a quantidade de nodos da �rvore.
	 * @return n�mero de nodos. 
	 */
	int size();

	/**
	 * Recupera a quantidade de nodos da �rvore atrav�s de c�lculo recursivo.
	 * @return n�mero de nodos. 
	 */	
	int count();
	
	/**
	 * Recupera o dado armazenado na raiz da �rvore.
	 * @return dado armazenado na raiz da �rvore. 
	 * @throws EmptyTreeException se a �rvore est� vazia.
	 */
	E getRoot();
	
	/**
	 * Adiciona um novo nodo na �rvore.
	 * @param element Novo nodo a ser inserido.	
	 */
	void addRec(E element);
	
	/**
	 * Remove o nodo da �rvore.
	 * @param element Refer�ncia para o dado a ser removido.
	 * @return o elemento removido da �rvore.
	 */
	E remove(E element);
	
	/**
	 * Remove o nodo da �rvore, juntamente com todos os seus filhos.
	 * @param element Refer�ncia para o dado a ser removido.
	 * @return true se a �rvore cont�m o elemento especificado.
	 */
	boolean removeBranch(E element);
	
	/**
	 * Recupera o dado armazenado no filho da esquerda do nodo da �rvore
	 * @param element Refer�ncia para o nodo a ser buscado.
	 * @return dado armazenado na esquerda do nodo informado. 
	 */
	E getLeft(E element);
		
	/**
	 * Recupera o dado armazenado no filho da direita do nodo da �rvore
	 * @param element Refer�ncia para o nodo a ser buscado.
	 * @return dado armazenado na direita do nodo informado. 
	 */
	E getRight(E element);
			
	/**
	 * Recupera o dado armazenado no pai do nodo da �rvore
	 * @param element Refer�ncia para o nodo a ser buscado.
	 * @return dado armazenado no pai do nodo informado. 
	 */
	E getFather(E element);
	
    /**
     * Retorna true se a �rvore cont�m o elemento especificado
     * @param element o elemento a ser buscado
     * @return true se a �rvore cont�m o elemento especificado
     */
    boolean contains(E element); 

    /**
     * Retorna uma refer�ncia para o item armazenado na �rvore
     * que compar�vel ao elemento especificado
     * @param element objeto com o elemento a ser buscado
     * @return refer�ncia para o objeto armazenado na �rvore, se a �rvore cont�m o elemento especificado
     */
    E search(E element); 

    
    /**
     * Substitui a primeira ocorr�ncia do elemento indicado por um novo elemento
     * @param old elemento a ser substitu�do
     * @param element novo elemento
     * @return elemento que foi substitu�do ou null se o elemento n�o est� na �rvore
     */
    E set(E old, E element);
    
    /**
	 * Instancia o navegador  para a �rvore bin�ria.
	 * @return Navigator para a �rvore. 
	 */
	NavigatorBinTree<E> navigator();
	
	/**
	 * Instancia o iterador "default" para a �rvore bin�ria.
	 * @return iterador para a �rvore. 
	 */
	Iterator<E> iterator();

	/**
	 * Instancia um iterador para a �rvore bin�ria.
	 * @param tipo do iterador desejado: WIDTH, POST, PRE ou CENTRAL
	 * @return iterador para a �rvore.  
	 */
	Iterator<E> iterator(IteratorTreeType t);
	
    
	/**
	 * Percorre a �rvore em pr�-ordem, retornando seus elementos. 	
	 * @return Lista com os nodos da �rvore em pr�-ordem.
	 */
	List<E> traversalPre();
	
	/**
	 * Percorre a �rvore em p�s-ordem, retornando seus elementos. 	
	 * @return Lista com os nodos da �rvore em p�s-ordem.
	 */
	List<E> traversalPos();
	
	/**
	 * Percorre a �rvore em ordem central (ou in-fixa), retornando seus elementos. 	
	 * @return Lista com os nodos da �rvore em ordem central.
	 */
	List<E> traversalCentral();
	
	/**
	 * Percorre a �rvore em amplitude, retornando seus elementos. 	
	 * @return Lista com os nodos da �rvore, ordenados n�vel a n�vel, da esquerda para direita. 
	 */
	List<E> traversalWidth();
	
	/**
	 * Percorre a �rvore em pr�-ordem, retornando uma string com o conte�do dos elementos
	 * da �rvore concatenados. 	
	 * @return String com os nodos da �rvore concatenados em pr�-ordem.
	 */
	String strTraversalPre();
	
	/**
	 * Percorre a �rvore em p�s-ordem, retornando uma string com o conte�do dos elementos
	 * da �rvore concatenados. 	
	 * @return String com os nodos da �rvore concatenados em p�s-ordem.
	 */
	String strTraversalPos();
	
	/**
	 * Percorre a �rvore em ordem central, retornando uma string com o conte�do dos elementos
	 * da �rvore concatenados. 	
	 * @return String com os nodos da �rvore concatenados em ordem central.
	 */
	String strTraversalCentral();
}
