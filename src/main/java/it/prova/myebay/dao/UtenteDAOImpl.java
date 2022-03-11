package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.model.Utente;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.StatoUtente;

public class UtenteDAOImpl implements UtenteDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Optional<Utente> findByUsernameAndPassword(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username and u.password=:password ", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<Utente> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Utente> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utente input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Utente input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(input);
	}

	@Override
	public void delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utente> findByExample(Utente example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Utente> login(String username, String password) {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u join fetch u.ruoli r "
						+ "where u.username = :username and u.password=:password and u.stato=:statoUtente",
				Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<Acquisto> list(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
