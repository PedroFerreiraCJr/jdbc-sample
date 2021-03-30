package br.com.dotofcodex.jdbc_sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.datasource.ConnectionFactory;

public class TestaListagem {

	private static final Logger logger = LoggerFactory.getLogger(TestaConexao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Consultando produtos na base loja_virtual.");
		consultaProdutos();
	}

	private static void consultaProdutos() {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			logger.info("Consultando...");
			final Statement stmt = conn.createStatement();
			boolean hasResultSet = stmt.execute("SELECT id, nome, descricao FROM produto");

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