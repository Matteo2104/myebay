package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;
import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.AnnuncioDAOException;
import it.prova.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	public List<Utente> list() throws Exception;
	
	public void delete(Utente input) throws Exception;
	
	public void update(Utente input) throws Exception;

	public Optional<Utente> findByUsernameAndPassword(String username, String password);

	public Optional<Utente> findByUsername(String username);

	public Optional<Utente> login(String username, String password);

	public Optional<Utente> findOneEager(Long id) throws Exception;
	
	public Optional<Utente> findOne(Long id) throws AnnuncioDAOException;
	
	public List<Utente> findByExample(Utente example) throws Exception;

	public List<Utente> findByExample(UtenteDTO example) throws Exception;

	public List<Utente> findByExample(UtenteSearch example) throws Exception;

}
