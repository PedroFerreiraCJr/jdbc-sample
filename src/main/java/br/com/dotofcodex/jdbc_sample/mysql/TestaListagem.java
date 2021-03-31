package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaListagem {

	private static final Logger logger = LoggerFactory.getLogger(TestaConexao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Consultando produtos na base loja_virtual.");
		consultaProdutos();
		logger.info("Loja Virtual - Execução concluída.");
	}

	private static void consultaProdutos() {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			logger.info("Consultando...");
			
			final String sql = "SELECT id, nome, descricao FROM produto";
			
			final PreparedStatement stmt = conn.prepareStatement(sql);
			boolean hasResultSet = stmt.execute();
			
			if (hasResultSet) {
				logger.info("Recuperando valores resultantes da consulta...");
				final ResultSet result = stmt.getResultSet();
				while (result.next()) {
					Integer id = result.getInt("id");
					String nome = result.getString("nome");
					String descricao = result.getString("descricao");
					logger.info(String.format("ID: %d, NOME: %s, DESC: %s.", id, nome, descricao));
				}
			}

			logger.info("Consulta concluída.");
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
		}
	}
}
