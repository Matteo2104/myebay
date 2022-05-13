package it.prova.myebay.dao;

import it.prova.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {

	public Categoria findByCodice(String codice) throws Exception;

}
