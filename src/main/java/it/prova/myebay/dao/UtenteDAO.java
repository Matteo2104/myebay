package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.MissedLoginException;
import it.prova.myebay.exception.DAO.UtenteDAOException;
import it.prova.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> list() throws UtenteDAOException;
		
	public void update(Utente input) throws UtenteDAOException;

	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws UtenteDAOException;

	public Optional<Utente> findByUsername(String username) throws UtenteDAOException;

	public Optional<Utente> login(String username, String password) throws MissedLoginException;

	public Optional<Utente> findOneEager(Long id) throws UtenteDAOException;
	
	public Optional<Utente> findOne(Long id) throws UtenteDAOException;
	
	public List<Utente> findByExample(UtenteSearch example) throws UtenteDAOException;

}
