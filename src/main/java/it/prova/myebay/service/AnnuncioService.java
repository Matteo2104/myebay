package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.exception.service.AnnuncioServiceException;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {

	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);

	public List<Annuncio> findByExample(Annuncio example) throws AnnuncioServiceException;

	public Annuncio caricaSingoloElementoEager(Long id) throws AnnuncioServiceException;

	public List<Annuncio> listAll() throws AnnuncioServiceException;

	public List<Annuncio> findByExamplePersonale(Annuncio example, Long idUserInSession) throws AnnuncioServiceException;

	public void aggiorna(Annuncio annuncio) throws AnnuncioServiceException;

	public List<Annuncio> listAll(Long id) throws AnnuncioServiceException;

	public Annuncio caricaSingoloElemento(Long id) throws AnnuncioServiceException;

	public void rimuovi(Long id) throws AnnuncioServiceException;

	public void aggiungi(Annuncio annuncio) throws AnnuncioServiceException;

	public List<Annuncio> getAnnunciAttivi() throws AnnuncioServiceException;
}
