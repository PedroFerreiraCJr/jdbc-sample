package br.com.dotofcodex.jdbc_sample.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.model.Categoria;
import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class ProdutoDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoDAO.class);

	private Connection conn;

	public ProdutoDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void salvar(Produto produto) throws SQLException {
		final String sql = "INSERT INTO produto(nome, descricao, categoria_id) VALUES(?, ?, ?)";
		try (final PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setLong(3, produto.getCategoria().getId());
			System.out.println(produto.getCategoria().getId());
			stmt.execute();

			try (final ResultSet rs = stmt.getGeneratedKeys()) {
				while (rs.next()) {
					produto.setId(rs.getLong(1));
				}

				logger.info(String.format("Produto criado com id: %d, nome: %s, descrição: %s.", produto.getId(),
						produto.getNome(), produto.getDescricao()));
			}
		}
	}

	public void salvarComCategoria(Produto produto) throws SQLException {
		String sql = "INSERT INTO produto (nome, descricao, categoria_id) VALUES (?, ?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setLong(3, produto.getCategoria().getId());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getLong(1));
				}
			}
		}
	}

	public List<Produto> listar() throws SQLException {
		final List<Produto> produtos = new ArrayList<>();
		final String sql = "SELECT id, nome, descricao FROM produto";

		try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.execute();

			trasformarResultSetEmProduto(produtos, stmt);
		}

		return produtos;
	}

	public List<Produto> buscar(Categoria categoria) throws SQLException {
		final List<Produto> produtos = new ArrayList<>();
		final String sql = "SELECT id, nome, descricao FROM produto WHERE categoria_id = ?";

		try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, categoria.getId());
			stmt.execute();

			trasformarResultSetEmProduto(produtos, stmt);
		}

		return produtos;
	}

	public void deletar(Long id) throws SQLException {
		try (PreparedStatement stm = conn.prepareStatement("DELETE FROM produto WHERE ID = ?")) {
			stm.setLong(1, id);
			stm.execute();
		}
	}

	public void alterar(String nome, String descricao, Integer id) throws SQLException {
		try (PreparedStatement stm = conn
				.prepareStatement("UPDATE produto p SET p.nome = ?, p.descricao = ? WHERE ID = ?")) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		}
	}

	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getLong(1), rst.getString(2), rst.getString(3));

				produtos.add(produto);
			}
		}
	}
}
