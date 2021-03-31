package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaInsercaoComParametro {

	private static final Logger logger = LoggerFactory.getLogger(TestaInsercaoComParametro.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Inserindo produtos na base loja_virtual.");
		inserirProduto();
		logger.info("Loja Virtual - Execução concluída.");
	}

	private static void inserirProduto() {
		final Connection conn = ConnectionFactory.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			logger.info("Inserindo valores na tabela de produtos.");

			final String sql = "INSERT INTO produto(nome, descricao) VALUES(?, ?)";

			try (final PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				preencheValores("Smart TV", "42 Polegadas", stmt);
				preencheValores("SmartPhone", "Samsung", stmt);
			}

			conn.commit();
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void preencheValores(String nome, String descricao, final PreparedStatement stmt)
			throws SQLException {
		logger.info(String.format("Inserindo produto %s...", nome));

		stmt.setString(1, nome);
		stmt.setString(2, descricao);

		final ResultSet result = stmt.getGeneratedKeys();
		while (result.next()) {
			Integer id = result.getInt(1);
			logger.info(String.format("Id gerado para o produto inserido %d.", id));
		}
	}
}
