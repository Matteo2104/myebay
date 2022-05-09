package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {

	public Optional<Utente> findByUsernameAndPassword(String username, String password);

	public Optional<Utente> login(String username, String password);

	public Optional<Utente> findOneEager(Long id) throws Exception;

	List<Utente> findByExample(UtenteDTO example) throws Exception;

	List<Utente> findByExample(UtenteSearch example) throws Exception;
	

}
