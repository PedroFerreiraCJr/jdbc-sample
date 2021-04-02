package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.dao.ProdutoDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaListagemComProdutoDAO {

	private static final Logger logger = LoggerFactory.getLogger(TestaListagemComProdutoDAO.class);

	public static void main(String[] args) throws SQLException {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(conn);
			dao.listar().stream().forEach(System.out::println);
		}
	}
}
