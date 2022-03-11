package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public interface UtenteService {

	public void setUtenteDAO(UtenteDAO utenteDAO);

	public Utente findByUsernameAndPassword(String username, String password) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;

	public Utente accedi(String username, String password) throws Exception;

	public List<Utente> findByExample(Utente example) throws Exception;

	public List<Utente> listAll() throws Exception;

}
