package it.prova.myebay.dao;

import java.util.Optional;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {

	public Optional<Annuncio> findOneEager(Long id) throws Exception;

}
