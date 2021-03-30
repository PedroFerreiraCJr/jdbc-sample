package br.com.dotofcodex.jdbc_sample.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/loja_virtual?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "pedro";

	private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	private static ConnectionFactory instance;

	private ConnectionFactory() {
		super();
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			logger.info("Obtendo nova conexão...");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.info("Nova conexão obtida.");
		} catch (final SQLException e) {
			logger.info("Falha na obtenção de nova conexão.", e);
		}
		return conn;
	}

	public static ConnectionFactory getInstance() {
		logger.info("Obtendo instância de ConnectionFactory.");
		if (instance == null) {
			synchronized (ConnectionFactory.class) {
				if (instance == null) {
					logger.info("ConnectionFactory instanciada...");
					instance = new ConnectionFactory();
				}
			}
		}

		return instance;
	}
}
