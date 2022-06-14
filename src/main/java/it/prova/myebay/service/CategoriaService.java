package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.exception.service.CategoriaServiceException;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {

	public void setCategoriaDAO(CategoriaDAO categoriaDAO);

	public List<Categoria> listAll() throws CategoriaServiceException;

	public Categoria findByCodice(String codice) throws CategoriaServiceException;

	public void inserisciNuovo(Categoria categoria) throws CategoriaServiceException;

}
