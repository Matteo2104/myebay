package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public List<Categoria> listAll() throws Exception;

}
