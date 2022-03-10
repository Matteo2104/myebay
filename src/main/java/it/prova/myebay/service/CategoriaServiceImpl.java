package it.prova.myebay.service;

import it.prova.myebay.dao.CategoriaDAO;

public class CategoriaServiceImpl implements CategoriaService {
	private CategoriaDAO categoriaDAO;
	
	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}
}
