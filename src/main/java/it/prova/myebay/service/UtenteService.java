package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public interface UtenteService {

	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Utente findByUsernameAndPassword(String username, String password) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public Utente accedi(String username, String password) throws Exception;

	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElementoEager(Long id) throws Exception;

	public void disabilita(Long id) throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public Utente findById(long id) throws Exception;

	public void aggiorna(Utente utenteEdit, Utente utenteInstance) throws Exception;

	public List<Utente> findByExample(UtenteSearch example) throws Exception;

	public void registra(Utente utente) throws Exception;

	public void abilita(Long id, String stato) throws Exception;

	public void ricarica(long id, int credito) throws Exception;
}
