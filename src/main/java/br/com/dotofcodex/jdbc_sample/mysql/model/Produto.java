package br.com.dotofcodex.jdbc_sample.mysql.model;

public class Produto {
	private Long id;
	private String nome;
	private String descricao;
	private Categoria categoria;

	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = new Categoria();
	}

	public Produto(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = new Categoria();
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

	public String getDescricao() {
		return descricao;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [id=").append(id).append(", nome=").append(nome).append(", descricao=")
				.append(descricao).append("]");
		return builder.toString();
	}
}
