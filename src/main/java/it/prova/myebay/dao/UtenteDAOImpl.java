package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Utente;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Annuncio;
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
		return entityManager.createQuery("from Utente", Utente.class).getResultList();
	}

	@Override
	public Optional<Utente> findOne(Long id) throws Exception {
		Utente result = entityManager.find(Utente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
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
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select u from Utente u join fetch u.ruoli r where u.id = u.id ");

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
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (example.getDateCreated() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		
		if (example.getRuoli() != null) {
			List<Long> idRuoliList = new ArrayList<>();
			for (Ruolo ruolo : example.getRuoli()) {
				idRuoliList.add(ruolo.getId());
			}
		
			whereClauses.add("r.id in :idRuoliList");
			paramaterMap.put("idRuoliList", idRuoliList);
		}
		
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Utente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Utente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
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
	public List<Annuncio> list(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Utente> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Utente u left join fetch u.ruoli where u.id=:idUtente", Utente.class)
				.setParameter("idUtente", id).getResultList().stream().findFirst();
	}
}
