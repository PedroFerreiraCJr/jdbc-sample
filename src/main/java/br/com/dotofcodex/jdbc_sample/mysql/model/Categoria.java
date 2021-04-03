package br.com.dotofcodex.jdbc_sample.mysql.model;

public class Categoria {

	private Long id;
	private String nome;

	public Categoria() {
		super();
	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
