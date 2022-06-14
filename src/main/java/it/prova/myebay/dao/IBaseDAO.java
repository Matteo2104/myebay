package it.prova.myebay.dao;

import javax.persistence.EntityManager;

public interface IBaseDAO<T> {

	public void insert(T input) throws Exception;
	
	// per l'injection
	public void setEntityManager(EntityManager entityManager);
}
