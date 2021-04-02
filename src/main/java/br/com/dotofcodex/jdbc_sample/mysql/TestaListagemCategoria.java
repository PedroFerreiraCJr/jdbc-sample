package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.dotofcodex.jdbc_sample.mysql.dao.CategoriaDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaListagemCategoria {
	public static void main(String[] args) throws SQLException {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			CategoriaDAO dao = new CategoriaDAO(conn);
			dao.listar().forEach((System.out::println));
		}
	}
}
