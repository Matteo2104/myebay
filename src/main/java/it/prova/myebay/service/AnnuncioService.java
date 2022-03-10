package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public List<Annuncio> findByExample(Annuncio example) throws Exception;

	public Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	public List<Annuncio> listAll() throws Exception;

}
