package it.prova.myebay.dao;

import java.util.List;

import it.prova.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	public void update(Categoria input) throws Exception;
	
	public List<Categoria> list() throws Exception;
	
	public List<Categoria> findByExample(Categoria example) throws Exception;

	public Categoria findByCodice(String codice) throws Exception;

}
