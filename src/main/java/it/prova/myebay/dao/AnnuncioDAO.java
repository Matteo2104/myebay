package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.exception.DAO.AnnuncioDAOException;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {
	
	public void update(Annuncio input) throws AnnuncioDAOException;
	
	public void delete(Annuncio input) throws AnnuncioDAOException;
	
	public List<Annuncio> list() throws AnnuncioDAOException;
	
	public List<Annuncio> list(long id) throws AnnuncioDAOException;

	public Optional<Annuncio> findOneEager(Long id) throws AnnuncioDAOException;
	
	public Optional<Annuncio> findOne(Long id) throws AnnuncioDAOException;
	
	public List<Annuncio> findByExample(Annuncio example) throws AnnuncioDAOException;

	public List<Annuncio> personalFindByExample(Annuncio example, Long id) throws AnnuncioDAOException;

	public List<Annuncio> listOnlyActive() throws AnnuncioDAOException;

}
