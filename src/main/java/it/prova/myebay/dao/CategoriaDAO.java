package it.prova.myebay.dao;

import java.util.List;
import it.prova.myebay.exception.CategoriaDAOException;
import it.prova.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
		
	public List<Categoria> list() throws CategoriaDAOException;
	
	public Categoria findByCodice(String codice) throws CategoriaDAOException;

}
