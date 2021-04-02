package br.com.dotofcodex.jdbc_sample.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.dotofcodex.jdbc_sample.mysql.dao.CategoriaDAO;
import br.com.dotofcodex.jdbc_sample.mysql.dao.ProdutoDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;

public class TestaListagemCategoria {
	public static void main(String[] args) throws SQLException {
		try (final Connection conn = ConnectionFactory.getInstance().getConnection()) {
			final CategoriaDAO categoriaDAO = new CategoriaDAO(conn);
			final ProdutoDAO produtoDAO = new ProdutoDAO(conn);

			categoriaDAO.listar().forEach((categoria) -> {
				try {
					System.out.println("Categoria: " + categoria.getNome());
					produtoDAO.buscar(categoria).forEach((produto) -> {
						System.out.println("O produto: " + produto.getNome() + ", categoria: " + categoria.getNome());
					});
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
