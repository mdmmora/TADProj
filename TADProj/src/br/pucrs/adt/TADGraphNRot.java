package br.pucrs.adt;

import java.util.*;
/**
 * Interface que descreve o TAD Grafo.
 * @author Michael Mora
 * @param <E> Classe dos dados que ser�o armazenados nos nodos do grafo.
 */
public interface TADGraphNRot<E extends Comparable, R extends Comparable> {
	/**
	 * Adiciona um nodo no grafo. 
	 * @param d - Elemento a ser adicionado no grafo.
	 */
	public void addNode(E d);
	
	/**
	 * Adiciona uma aresta no grafo.
	 * @param origem - inst�ncia (parcial) do nodo de origem da aresta
	 * @param destino - inst�ncia (parcial) do nodo destino da aresta 
	 */
	public void addEdge(E origem, E destino, R label);
	
	/**
	 * Procura um elemento no grafo.
	 * @param elem - inst�ncia (parcial) do elemento que se busca no grafo
	 * @return refer�ncia para o nodo no grafo 
	 */	
	//public Dado searchNode(Dado elem);
	
	public List<E> depthFirstTraversal(E origem);

}
