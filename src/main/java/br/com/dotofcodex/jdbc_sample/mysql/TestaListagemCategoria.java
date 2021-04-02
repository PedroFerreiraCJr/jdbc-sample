package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.dotofcodex.jdbc_sample.mysql.dao.CategoriaDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaListagemCategoria {
	public static void main(String[] args) throws SQLException {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			final CategoriaDAO dao = new CategoriaDAO(conn);
			dao.listarComProduto().forEach((p) -> {
				System.out
						.println(String.format("Produto: %s, Categoria: %s.", p.getNome(), p.getCategoria().getNome()));
			});
		}
	}
}
