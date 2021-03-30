package br.com.dotofcodex.jdbc_sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.datasource.ConnectionFactory;

public class TestaRemocao {

	private static final Logger logger = LoggerFactory.getLogger(TestaRemocao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Removendo produtos na base loja_virtual.");
		removerProduto();
		logger.info("Loja Virtual - Execução concluída.");
	}

	private static void removerProduto() {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			logger.info("Removendo valores duplicados da tabela de produtos.");

			final Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM produto p WHERE p.id = 4");

			int deleted = stmt.getUpdateCount();
			if (deleted == 1) {
				logger.info("Deleção realizada com sucesso.");
			}
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
		}
	}
}
