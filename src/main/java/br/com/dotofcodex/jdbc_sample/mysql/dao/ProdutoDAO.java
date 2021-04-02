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

import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class ProdutoDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoDAO.class);

	private Connection conn;

	public ProdutoDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public void salvar(Produto produto) throws SQLException {
		final String sql = "INSERT INTO produto(nome, descricao) VALUES(?, ?)";
		try (final PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
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

	public List<Produto> listar() throws SQLException {
		final List<Produto> produtos = new ArrayList<>();
		final String sql = "SELECT id, nome, descricao FROM produto";
		try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (final ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					produtos.add(new Produto(rs.getLong("id"), rs.getString("nome"), rs.getString("descricao")));
				}
			}
		}

		return produtos;
	}
}
