package it.prova.myebay.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.UserRegisteredException;
import it.prova.myebay.exception.service.UtenteServiceException;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class UtenteServiceImpl implements UtenteService {
	private UtenteDAO utenteDAO;
	private static final String UTENTENOTFOUND = "Si è verificato un errore: utente non trovato";
	
	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}
	
	@Override
	public List<Utente> listAll() throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);
		
		List<Utente> resultList = utenteDAO.list();
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		return resultList;
		
	}
	
	@Override
	public Utente findByUsernameAndPassword(String username, String password) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		Optional<Utente> result = utenteDAO.findByUsernameAndPassword(username, password);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		return result.isPresent() ? result.get() : null;

		
	}
	
	@Override
	public Utente findById(long id) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		Optional<Utente> result = utenteDAO.findOne(id);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		
		return result.isPresent() ? result.get() : null;

	}
	
	@Override
	public void aggiorna(Utente utenteEdit, Utente utenteInstance) throws UtenteServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			entityManager.getTransaction().begin();

			
			// eseguo quello che realmente devo fare
			utenteEdit.setNome(utenteInstance.getNome());
			utenteEdit.setCognome(utenteInstance.getCognome());
			utenteEdit.setUsername(utenteInstance.getUsername());
			utenteEdit.setStato(utenteInstance.getStato());
			utenteEdit.setRuolo(utenteInstance.getRuolo());
			utenteDAO.update(utenteEdit);
			
			entityManager.getTransaction().commit();


		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void inserisciNuovo(Utente utenteInstance) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDAO.insert(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente accedi(String username, String password) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		Optional<Utente> result = utenteDAO.login(username, password);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		return result.isPresent() ? result.get() : null;

		

	}
	
	@Override
	public void disabilita(Long id) throws UtenteServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();

			// eseguo quello che realmente devo fare
			Utente utente = utenteDAO.findOne(id).orElse(null);
			
			if (utente == null) {
				throw new UtenteServiceException(UTENTENOTFOUND);
			}
			
			utente.setStato(StatoUtente.DISABILITATO);
			
			utente = entityManager.merge(utente);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void abilita(Long id, String stato) throws UtenteServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();

			// eseguo quello che realmente devo fare
			Utente utente = utenteDAO.findOne(id).orElse(null);
			
			if (utente == null) {
				throw new UtenteServiceException(UTENTENOTFOUND);
			}
			
			utente.setStato(StatoUtente.fromString(stato));
			
			utente = entityManager.merge(utente);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public List<Utente> findByExample(UtenteSearch example) throws UtenteServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);
		
		List<Utente> resultList = utenteDAO.findByExample(example);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		return resultList;
	
	}
	
	@Override
	public Utente caricaSingoloElemento(Long id) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		
		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		Optional<Utente> utenteFound = utenteDAO.findOne(id);
		
		if (utenteFound.isEmpty())
			throw new UtenteServiceException(UTENTENOTFOUND);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
			
		return utenteFound.get();
	
	}
	
	@Override
	public Utente caricaSingoloElementoEager(Long id) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		// uso l'injection per il dao
		utenteDAO.setEntityManager(entityManager);

		// eseguo quello che realmente devo fare
		Optional<Utente> utenteFound = utenteDAO.findOneEager(id);
		
		if (utenteFound.isEmpty())
			throw new UtenteServiceException(UTENTENOTFOUND);
		
		LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
			
		return utenteFound.get();
	}
	
	@Override
	public void registra(Utente utente) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			// controllo che l'utente non sia già stato inserito precedentemente
			Optional<Utente> utenteExist = utenteDAO
					.findByUsername(utente.getUsername());

			if (utenteExist.isPresent()) {
				throw new UserRegisteredException("Utente già registrato");
			}

			// eseguo quello che realmente devo fare
			utente.setRuolo(Ruolo.ROLE_CLASSIC_USER);
			utente.setStato(StatoUtente.ATTIVO); // in teoria andrebbe eseguita la conferma
			utente.setDateCreated(new Date());
			
			utenteDAO.insert(utente);

			entityManager.getTransaction().commit();
			
		
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void ricarica(long userId, int credito) throws UtenteServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> utente = utenteDAO.findOne(userId);
			if (!utente.isPresent())
				throw new UtenteServiceException("Utente non trovato!");

			Utente utenteInstance = utente.get();
			utenteInstance.setCreditoResiduo(utenteInstance.getCreditoResiduo() + credito);
			utenteDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
