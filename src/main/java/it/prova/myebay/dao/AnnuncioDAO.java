package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {
	
	public List<Annuncio> list(long id) throws Exception;

	public Optional<Annuncio> findOneEager(Long id) throws Exception;

	public List<Annuncio> personalFindByExample(Annuncio example, Long id) throws Exception;

	public List<Annuncio> listOnlyActive() throws Exception;

}
