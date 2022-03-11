package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public interface AnnuncioService {

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public List<Annuncio> findByExample(Annuncio example) throws Exception;

	public Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	public List<Annuncio> listAll() throws Exception;


	public List<Annuncio> findByExamplePersonale(Annuncio example, Long idUserInSession) throws Exception;

	public void aggiorna(Annuncio annuncio) throws Exception;

	public List<Annuncio> listAll(Long id) throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;

	public void rimuovi(Long id) throws Exception;

	public void aggiungi(Annuncio annuncio) throws Exception;

}
