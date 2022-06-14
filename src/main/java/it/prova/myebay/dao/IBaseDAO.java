package it.prova.myebay.dao;

import javax.persistence.EntityManager;

import it.prova.myebay.exception.DAO.IBaseDAOException;

public interface IBaseDAO<T> {

	public void insert(T input) throws IBaseDAOException;
	
	// per l'injection
	public void setEntityManager(EntityManager entityManager);
}
