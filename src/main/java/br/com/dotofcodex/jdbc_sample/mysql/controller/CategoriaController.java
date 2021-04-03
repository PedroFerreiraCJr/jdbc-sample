package br.com.dotofcodex.jdbc_sample.mysql.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dotofcodex.jdbc_sample.mysql.dao.CategoriaDAO;
import br.com.dotofcodex.jdbc_sample.mysql.datasource.ConnectionFactory;
import br.com.dotofcodex.jdbc_sample.mysql.model.Categoria;

public class CategoriaController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

	private final CategoriaDAO dao;

	public CategoriaController() {
		super();
		this.dao = new CategoriaDAO(ConnectionFactory.getInstance().getConnection());
	}

	public List<Categoria> listar() {
		try {
			return this.dao.listar();
		} catch (SQLException e) {
			logger.info("Falha na consulta a listar do CategoriaDAO", e);
		}

		return null;
	}
}