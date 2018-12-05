package br.pucrs.adt;

public class EmptyStackException extends RuntimeException{
	EmptyStackException()
	{
		super("Pilha vazia!");
	}

}
