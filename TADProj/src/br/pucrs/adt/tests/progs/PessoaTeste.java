package br.pucrs.adt.tests.progs;

public class PessoaTeste implements Comparable<PessoaTeste>{
	private String cpf;
	private String nome;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "PessoaTeste [cpf=" + cpf + ", nome=" + nome + "]";
	}
	public PessoaTeste(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}
	@Override
	public int compareTo(PessoaTeste o) {
		return cpf.compareTo(o.getCpf());
	}
}
