package br.pucrs.adt.OldTrees;

import java.util.List;
/**
 * Interface que descreve o TAD �rvore.
 * @author Michael Mora
 * @param <Dado> Classe dos dados que ser�o armazenados nos nodos da �rvore.
 */

public interface TreeTAD<Dado> {
	/**
	 * Verifica se �rvore est� vazia.
	 * @return		true se a �rvore est� vazia, false caso contr�rio. 
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Recupera a quantidade de nodos da �rvore.
	 * @return		n�mero de nodos. 
	 */
	public abstract int size();
	
	/**
	 * Adiciona um novo nodo na �rvore.
	 * @param	d		Novo nodo a ser inserido.	
	 * @param	pos		Nodo ap�s o qual ser� inserido o novo nodo.
	 */
	public abstract void add(Dado d, Dado pos);
	
	/**
	 * Adiciona um novo nodo na �rvore, ap�s o nodo marcado como corrente. Nodo corrente �
	 * aquele que acessado na opera��o mais recente sobre a �rvore.
	 * @param	d		Novo nodo a ser inserido.	
	 */
	public abstract void addCurrent(Dado d);
	
	/**
	 * Remove o nodo da �rvore que armazena a primeira ocorr�ncia dado referenciado 
	 * pelo par�metro "d", preservando os filhos deste. Re-organiza a �rvore com 
	 * esta finalidade.
	 * @param	d		Refer�ncia para o dado a ser removido
	 * @return 			Refer�ncia para o dado armazenado na �rvore e que est� sendo
	 * 					removido. 
	 */
	public abstract Dado removeOcurrence(Dado d);
	
	/**
	 * Remove o nodo da �rvore, juntamente com todos os seus filhos, que armazena o dado 
	 * referenciado pelo par�metro "d".
	 * @param	d		Refer�ncia para o dado a ser removido 
	 */
	public abstract void removeBranch(Dado d);
	
	/**
	 * Recupera o dado armazenado nos filhos, indexando os ramos.
	 * @return    Dado armazenado no filho. 
	 */
	public abstract Dado getChild(int index);
	
	/**
	 * Recupera o dado armazenado na raiz da �rvore.
	 * @return    Dado armazenado na raiz da �rvore. 
	 */
	public abstract Dado getRoot();
	
	
	/**
	 * Recupera o dado armazenado no pai do nodo corrente da �rvore.
	 * @return    Dado armazenado no pai do nodo corrente. 
	 */
	public abstract Dado getPai();
	
	
	/**
	 * Recupera o dado armazenado no nodo corrente da �rvore.
	 * @return    Dado armazenado no nodo corrente. 
	 */
	public abstract Dado getCurrent();
	
	
	/**
	 * Inicializa uma iterador (interno) na raiz. Permite acessar o nodos, um a um, atrav�s
	 * dos m�todos getNextXXX().
	 */
	public abstract void initGetNext();
	
	
	/**
	 * Recupera o "pr�ximo nodo" da �rvore, seguindo "pr�-ordem".
	 */
	public abstract Dado getNextPre();
	
	
	/**
	 * Procura a primeira ocorr�ncia do dado na �rvore.
	 * @param	d		Dado sendo procurado.	
	 * @return    refer�ncia para o dado da �rvore.
	 */
	public abstract Dado search(Dado d);
	
	
	/**
	 * Procura a primeira ocorr�ncia do dado na �rvore, inicializando um
	 * iterador que permite buscar as ocorr�ncias seguintes.
	 * @param	d		Dado sendo procurado.	
	 * @return    refer�ncia para o dado da �rvore.
	 */
	public abstract Dado searchIterFirst(Dado d);
	
	
	/**
	 * Procura a ocorr�ncia seguinte do dado procurado atrav�s 
	 * do m�todo "searchIterFirst(Dado) na �rvore.	
	 * @return    refer�ncia para o dado da �rvore.
	 */
	public abstract Dado searchIterNext();
	
	
	/**
	 * Conta a quantidade de nodos da �rvore. 	
	 * @return    N�mero de nodos da �rvore
	 */
	public abstract int count();
	
	
	/**
	 * Conta a quantidade de nodos folha da �rvore. 	
	 * @return    N�mero de nodos folha da �rvore
	 */
	public abstract int countLeaves();
	
	
	/**
	 * Percorre a �rvore em pr�-ordem, retornando seus elementos. 	
	 * @return    Lista com os nodos da �rvore em pr�-ordem.
	 */
	public abstract List<Dado> traversalPre();
	
	
	/**
	 * Percorre a �rvore em p�s-ordem, retornando seus elementos. 	
	 * @return    Lista com os nodos da �rvore em pos-ordem.
	 */
	public abstract List<Dado> traversalPos();
	
	
	/**
	 * Percorre a �rvore em amplitude, retornando seus elementos. 	
	 * @return    Lista com os nodos da �rvore, ordenados n�vel � n�vel, da esquerda para 
	 * 				direita.
	 */
	public abstract List<Dado> traversalWidth();
	
	public String strTraversalPre();
}