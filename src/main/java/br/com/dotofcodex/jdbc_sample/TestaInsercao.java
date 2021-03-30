package br.com.dotofcodex.jdbc_sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.datasource.ConnectionFactory;

public class TestaInsercao {

	private static final Logger logger = LoggerFactory.getLogger(TestaInsercao.class);

	public static void main(String[] args) {
		logger.info("Loja Virtual - Inserindo produtos na base loja_virtual.");
		inserirProduto();
		logger.info("Loja Virtual - Execução concluída.");
	}

	private static void inserirProduto() {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			logger.info("Inserindo valores na tabela de produtos.");
			final Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO produto(nome, descricao) VALUES('Mouse', 'Mouse Sem Fio')",
					Statement.RETURN_GENERATED_KEYS);
			
			ResultSet result = stmt.getGeneratedKeys();
			while (result.next()) {
				Integer id = result.getInt(1);
				logger.info(String.format("Id gerado para o produto inserido %d.", id));
			}
		} catch (SQLException e) {
			logger.error("Falha na conexão", e);
		}
	}
}
