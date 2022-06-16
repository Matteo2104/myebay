package it.prova.myebay.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.exception.service.AnnuncioServiceException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AnnuncioServiceImpl implements AnnuncioService {
	private AnnuncioDAO annuncioDAO;
	
	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}
	
	@Override
	public List<Annuncio> getAnnunciAttivi() throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.listOnlyActive();

		
	}
	
	@Override
	public List<Annuncio> findByExample(Annuncio example) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.findByExample(example);

		
	}
	
	@Override
	public List<Annuncio> findByExamplePersonale(Annuncio example, Long idUserInSession) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();


		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);

		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.personalFindByExample(example, idUserInSession);

	}
	
	@Override
	public Annuncio caricaSingoloElemento(Long id) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.findOne(id).orElse(null);

		
	}
	
	@Override
	public void aggiungi(Annuncio annuncio) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			entityManager.getTransaction().begin();
			
			// eseguo quello che realmente devo fare
			annuncio.setData(new Date());
			annuncioDAO.insert(annuncio);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Annuncio caricaSingoloElementoEager(Long id) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.findOneEager(id).orElse(null);

		
	}
	
	@Override
	public List<Annuncio> listAll() throws AnnuncioServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);

		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		
		// eseguo quello che realmente devo fare
		return annuncioDAO.list();

		
	}
	
	@Override
	public List<Annuncio> listAll(Long id) throws AnnuncioServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		annuncioDAO.setEntityManager(entityManager);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		return annuncioDAO.list(id);

		
	}
	
	@Override
	public void aggiorna(Annuncio annuncio) throws AnnuncioServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			entityManager.getTransaction().begin();

			
			// eseguo quello che realmente devo fare
			annuncioDAO.update(annuncio);
			
			entityManager.getTransaction().commit();


		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void rimuovi(Long id) throws AnnuncioServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();


			// eseguo quello che realmente devo fare
			Annuncio annuncio = annuncioDAO.findOneEager(id).orElse(null);
			
			annuncioDAO.delete(annuncio);

			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
