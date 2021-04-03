package br.com.dotofcodex.jdbc_sample.mysql.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.dotofcodex.jdbc_sample.mysql.model.Categoria;

public class CategoriaController {

	public List<Categoria> listar() {
		List<Categoria> categorias = 
				new ArrayList<Categoria>();
		categorias.add(new Categoria(1l, "Categoria de teste")); 
		return categorias;
	}
}