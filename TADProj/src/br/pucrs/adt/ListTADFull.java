package br.pucrs.adt;
import java.util.*;

/*********
 * Interface para o T.A.D. Lista.
 * @author Michael da Costa M�ra
 * @param <E> Tipo dos dados armazenados na lista.
 */
public interface ListTADFull <E extends Comparable<E>> {
	/**********
	 * Adiciona um elemento na �ltima posi��o da lista.
	 * @param element Elemento a ser adicionado. 
	 * @return true se a opera��o funcionou, false caso contr�rio.
	 */
	public void add(E element);
	
	
	/**********
	 * Adiciona o elemento fornecido em uma dada posi��o da lista.
	 * @param index Posi��o da lista.
	 * @param element Elemento a ser adicionado. 
	 * @return true se a opera��o funcionou, false caso contr�rio.
	 */
	public void add(int index, E element);
	
	
	/**********
	 * Remove o elemento armazenado na posi��o fornecida da lista.
	 * @param pos Posi��o da lista. 
	 */
	public E remove(int pos);

	
	/**********
	 * Remove a primeira ocorr�ncia de um dado elemento da lista.
	 * @param element Elemento a ser adicionado. 
	 */
	public E remove(E element);
	
	
	/**********
	 * Recupera o elemento armazenado em uma dada posi��o da lista.
	 * @param pos Posi��o do elemento na lista.
	 * @return Elemento armazenado na lista. 
	 */	
	public E get(int pos);
	
	
	/**********
	 * Atualiza a posi��o fornecida da lista, com o elemento fornecido.
	 * @param index Posi��o da lista.
	 * @param element Elemento a ser armazenado na lista.
	 * @return true se a opera��o funcionou, false caso contr�rio. 
	 */	
	public void set(int index, E element);
	
	
	/**********
	 * Procura um elemento na lista, e retorna sua ocorr�ncia.
	 * @param element Elemento a ser procurado.
	 * @return refer�ncia para o elemento armazenado na lista. 
	 */	
	public E search(E element);
	
	
	/**********
	 * Verifica se a lista est� vazia.
	 * @return true se a lista est� vazia, false caso contr�rio. 
	 */		
	public boolean isEmpty();
	
	
	/**********
	 * Recupera a quantidade de elementos armazenados na lista.
	 * @return Quantidade de elementos. 
	 */			
	public int size(); 
	
	
	/********
	 * Conta quantas ocorr�ncias de um dado elemento est�o armazenadas na lista.
	 * @return A quantidade de ocorr�ncias do elemento fornecido.
	 */	
	public int count(E element);
	
	
	/**********
	 * Remove todos os elementos da lista.
	 */				
	public void clean();
	
	
	/**********
	 * Retorna um iterador para a lista.
	 * @return O iterador para a lista. 
	 */		
	public Iterator<E> iterator();
	
	
	/**********
	 * Retorna uma representa��o textual do conte�do da lista.
	 * @return Um String com o conte�do da lista. 
	 */		
	public String toString();	
	}
