package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.exception.MissedLoginException;
import it.prova.myebay.exception.DAO.UtenteDAOException;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {
	private EntityManager entityManager;
	private static final String USERNAME = "username";
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws UtenteDAOException  {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username and u.password=:password ", Utente.class);
		query.setParameter(USERNAME, username);
		query.setParameter("password", password);
		return query.getResultStream().findFirst();
	}
	
	@Override
	public Optional<Utente> findByUsername(String username) throws UtenteDAOException {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u  " + "where u.username = :username", Utente.class);
		query.setParameter(USERNAME, username);
		return query.getResultStream().findFirst();
	}

	@Override
	public List<Utente> list() throws UtenteDAOException {
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Optional<Utente> findOne(Long id) throws UtenteDAOException {
		Utente result = entityManager.find(Utente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();

	}

	@Override
	public void update(Utente input) throws UtenteDAOException {
		entityManager.merge(input);
	}

	@Override
	public void insert(Utente input) throws UtenteDAOException {
		if (input == null) {
			throw new UtenteDAOException("Problema valore in input");
		}

		entityManager.persist(input);
	}

	@Override
	public List<Utente> findByExample(UtenteSearch example) throws UtenteDAOException {
		Map<String, Object> paramaterMap = new HashMap<>();
		List<String> whereClauses = new ArrayList<>();

		StringBuilder queryBuilder = new StringBuilder("select u from Utente u where u.id = u.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" u.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username like :username ");
			paramaterMap.put(USERNAME, "%" + example.getUsername() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		
		if (example.getRuoli() != null) {
			List<Ruolo> ruoliList = new ArrayList<>();
			for (Ruolo ruolo : example.getRuoli()) {
				ruoliList.add(ruolo);
			}
		
			whereClauses.add("u.ruolo in :ruoliList");
			paramaterMap.put("ruoliList", ruoliList);
		}
		
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (Map.Entry<String, Object> entry : paramaterMap.entrySet()) {
			typedQuery.setParameter(entry.getKey(), paramaterMap.get(entry.getKey()));
		}
		/*
		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}
		*/
		return typedQuery.getResultList();
	}

	@Override
	public Optional<Utente> login(String username, String password) throws MissedLoginException  {
		TypedQuery<Utente> query = entityManager.createQuery(
				"select u FROM Utente u where u.username = :username and u.password=:password and u.stato=:statoUtente",
				Utente.class);
		query.setParameter(USERNAME, username);
		query.setParameter("password", password);
		query.setParameter("statoUtente", StatoUtente.ATTIVO);
		return query.getResultStream().findFirst();
	}

	
	
	@Override
	public Optional<Utente> findOneEager(Long id) throws UtenteDAOException {
		return entityManager.createQuery("from Utente u left join fetch u.ruoli where u.id=:idUtente", Utente.class)
				.setParameter("idUtente", id).getResultList().stream().findFirst();
	}

}
