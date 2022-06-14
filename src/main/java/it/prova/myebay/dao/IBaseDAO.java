package it.prova.myebay.dao;

import java.util.Optional;
import javax.persistence.EntityManager;

public interface IBaseDAO<T> {

	public Optional<T> findOne(Long id) throws Exception;

	public void insert(T input) throws Exception;
	
	// per l'injection
	public void setEntityManager(EntityManager entityManager);
}
