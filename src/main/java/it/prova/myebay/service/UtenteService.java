package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.service.UtenteServiceException;
import it.prova.myebay.model.Utente;

public interface UtenteService {

	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Utente findByUsernameAndPassword(String username, String password) throws UtenteServiceException;

	public void inserisciNuovo(Utente utenteInstance) throws UtenteServiceException;

	public Utente accedi(String username, String password) throws UtenteServiceException;

	public List<Utente> listAll() throws UtenteServiceException;

	public Utente caricaSingoloElementoEager(Long id) throws UtenteServiceException;

	public void disabilita(Long id) throws UtenteServiceException;

	public Utente caricaSingoloElemento(Long id) throws UtenteServiceException;

	public Utente findById(long id) throws UtenteServiceException;

	public void aggiorna(Utente utenteEdit, Utente utenteInstance) throws UtenteServiceException;

	public List<Utente> findByExample(UtenteSearch example) throws UtenteServiceException;

	public void registra(Utente utente) throws UtenteServiceException;

	public void abilita(Long id, String stato) throws UtenteServiceException;

	public void ricarica(long id, int credito) throws UtenteServiceException;
}
