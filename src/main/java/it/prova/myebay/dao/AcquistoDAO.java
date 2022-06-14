package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.exception.DAO.AcquistoDAOException;
import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {
	public List<Acquisto> list(long userId) throws AcquistoDAOException;
	
	public Optional<Acquisto> findOne(Long id) throws AcquistoDAOException;
}
