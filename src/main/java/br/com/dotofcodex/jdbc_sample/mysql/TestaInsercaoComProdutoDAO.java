package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.dao.ProdutoDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;
import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class TestaInsercaoComProdutoDAO {

	private static final Logger logger = LoggerFactory.getLogger(TestaInsercaoComProdutoDAO.class);

	public static void main(String[] args) throws SQLException {
		Produto produto = new Produto("Mouse", "Mouse Sem Fio");

		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(conn);
			dao.salvar(produto);
		}
	}
}
