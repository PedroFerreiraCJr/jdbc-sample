package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaRemocao {

	private static final Logger logger = LoggerFactory.getLogger(TestaRemocao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Removendo produtos na base loja_virtual.");
		removerProduto(4);
		logger.info("Loja Virtual - Execução concluída.");
	}

	private static void removerProduto(int id) {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			logger.info("Removendo valores duplicados da tabela de produtos.");

			final String sql = "DELETE FROM produto p WHERE p.id = ?";
			
			final PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();

			int deleted = stmt.getUpdateCount();
			if (deleted == 1) {
				logger.info("Deleção realizada com sucesso.");
			}
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
		}
	}
}
