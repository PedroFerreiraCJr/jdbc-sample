package br.com.dotofcodex.jdbc_sample.mysql;

import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaPoolConexoes {
	public static void main(String[] args) {
		final ConnectionFactory factory = ConnectionFactory.getInstance();
		for (int i = 0; i < 20; i++) {
			factory.getConnection();
			System.out.println("Conexão de número: " + i);
		}
	}
}
