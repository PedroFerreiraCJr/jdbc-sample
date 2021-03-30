package br.com.dotofcodex.jdbc_sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestaConexao {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/loja_virtual?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "pedro";

	private static final Logger logger = LoggerFactory.getLogger(TestaConexao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Testando conexão com o banco MySQL.");
		testaConexao();
	}

	private static void testaConexao() {
		try (final Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			logger.info("Conexão estabelecida com sucesso!");
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
		}
	}
}
