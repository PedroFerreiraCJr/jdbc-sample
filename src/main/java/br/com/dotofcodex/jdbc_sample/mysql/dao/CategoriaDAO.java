package br.com.dotofcodex.jdbc_sample.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.model.Categoria;
import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class CategoriaDAO {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaDAO.class);

	private Connection conn;

	public CategoriaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<Categoria> listar() throws SQLException {
		final List<Categoria> categorias = new ArrayList<>();
		final String sql = "SELECT id, nome FROM categoria";
		try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (final ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					categorias.add(new Categoria(rs.getLong("id"), rs.getString("nome")));
				}
			}
		}

		return categorias;
	}

	public List<Produto> listarComProduto() throws SQLException {
		final List<Produto> produtos = new ArrayList<>();
		final String sql = "SELECT c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao FROM categoria c JOIN produto p ON (c.id = p.categoria_id)";
		try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (final ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Categoria c = new Categoria(rs.getLong("c_id"), rs.getString("c_nome"));
					Produto p = new Produto(rs.getLong("p_id"), rs.getString("p_nome"), rs.getString("p_descricao"));
					p.setCategoria(c);
					produtos.add(p);
				}
			}
		}

		return produtos;
	}
}
