package it.prova.myebay.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.exception.service.CategoriaServiceException;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class CategoriaServiceImpl implements CategoriaService {
	private CategoriaDAO categoriaDAO;
	
	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}
	
	@Override
	public List<Categoria> listAll() throws CategoriaServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		// uso l'injection per il dao
		categoriaDAO.setEntityManager(entityManager);
		
		List<Categoria> resultList = categoriaDAO.list();

		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		
		return resultList;
	}
	
	@Override
	public Categoria findByCodice(String codice) throws CategoriaServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		categoriaDAO.setEntityManager(entityManager);
		
		Categoria categoria = categoriaDAO.findByCodice(codice);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		return categoria;

	}
	
	@Override
	public void inserisciNuovo(Categoria categoria) throws CategoriaServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			categoriaDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();

			// eseguo quello che realmente devo fare
			categoriaDAO.insert(categoria);
			
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
