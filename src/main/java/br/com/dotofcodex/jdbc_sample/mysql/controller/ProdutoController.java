package br.com.dotofcodex.jdbc_sample.mysql.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.dao.ProdutoDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;
import br.com.dotofcodex.jdbc_sample.mysql.model.Produto;

public class ProdutoController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	private final ProdutoDAO dao;

	public ProdutoController() {
		super();
		this.dao = new ProdutoDAO(ConnectionFactory.getInstance().getConnection());
	}

	public void deletar(Long id) {
		try {
			this.dao.deletar(id);
		} catch (SQLException e) {
			logger.info("Houve uma falha no método deletar de ProdutoController", e);
		}
	}

	public void salvar(Produto produto) {
		try {
			this.dao.salvar(produto);
		} catch (SQLException e) {
			logger.info("Houve uma falha no salvar de ProdutoController", e);
		}
	}

	public List<Produto> listar() {
		try {
			return this.dao.listar();
		} catch (SQLException e) {
			logger.info("Houve uma falha na consulta a listar de ProdutoController", e);
		}

		return null;
	}

	public void alterar(String nome, String descricao, Integer id) {
		try {
			this.dao.alterar(nome, descricao, id);
		} catch (SQLException e) {
			logger.info("Houve uma falha na consulta a alterar de ProdutoController", e);
		}
	}
}
