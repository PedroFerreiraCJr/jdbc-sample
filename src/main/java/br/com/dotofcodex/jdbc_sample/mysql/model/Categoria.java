package br.com.dotofcodex.jdbc_sample.mysql.model;

public class Categoria {

	private Long id;
	private String nome;

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

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [id=").append(id).append(", nome=").append(nome).append("]");
		return builder.toString();
	}
}
