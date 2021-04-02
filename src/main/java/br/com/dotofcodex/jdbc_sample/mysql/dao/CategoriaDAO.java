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
}
