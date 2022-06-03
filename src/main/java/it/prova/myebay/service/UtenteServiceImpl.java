package it.prova.myebay.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.UserRegisteredException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;

public class UtenteServiceImpl implements UtenteService {
	private UtenteDAO utenteDAO;
	
	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}
	
	@Override
	public List<Utente> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente findByUsernameAndPassword(String username, String password) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> result = utenteDAO.findByUsernameAndPassword(username, password);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente findById(long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> result = utenteDAO.findOne(id);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void aggiorna(Utente utenteEdit, Utente utenteInstance) throws Exception {
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
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void inserisciNuovo(Utente utenteInstance) throws Exception {
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
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente accedi(String username, String password) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			Optional<Utente> result = utenteDAO.login(username, password);
			return result.isPresent() ? result.get() : null;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}
	
	@Override
	public void disabilita(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();

			// eseguo quello che realmente devo fare
			Utente utente = utenteDAO.findOne(id).orElse(null);
			
			utente.setStato(StatoUtente.DISABILITATO);
			
			utente = entityManager.merge(utente);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void abilita(Long id, String stato) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);
			
			entityManager.getTransaction().begin();

			// eseguo quello che realmente devo fare
			Utente utente = utenteDAO.findOne(id).orElse(null);
			
			utente.setStato(StatoUtente.fromString(stato));
			
			utente = entityManager.merge(utente);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public List<Utente> findByExample(UtenteSearch example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findByExample(example);
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOne(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Utente caricaSingoloElementoEager(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDAO.findOneEager(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void registra(Utente utente) throws Exception {
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
			
		} catch (UserRegisteredException e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public void ricarica(long userId, int credito) throws Exception {
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
				throw new Exception("Utente non trovato!");

			Utente utenteInstance = utente.get();
			utenteInstance.setCreditoResiduo(utenteInstance.getCreditoResiduo() + credito);
			utenteDAO.update(utenteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
