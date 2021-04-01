package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;
import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class TestaInsercaoComProduto {

	private static final Logger logger = LoggerFactory.getLogger(TestaInsercaoComProduto.class);

	public static void main(String[] args) throws SQLException {
		Produto produto = new Produto("Mouse", "Mouse Sem Fio");

		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
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
	}
}
