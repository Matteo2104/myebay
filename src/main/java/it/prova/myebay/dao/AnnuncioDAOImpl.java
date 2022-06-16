package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import it.prova.myebay.exception.DAO.AnnuncioDAOException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;


public class AnnuncioDAOImpl implements AnnuncioDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Annuncio> list(long userId) throws AnnuncioDAOException {
		return entityManager.createQuery("from Annuncio a join fetch a.utenteInserimento u where u.id = :id", Annuncio.class).setParameter("id", userId).getResultList();
	}
	
	@Override
	public List<Annuncio> listOnlyActive() throws AnnuncioDAOException {
		return entityManager.createQuery("from Annuncio a where a.aperto = 1", Annuncio.class).getResultList();
	}
	
	@Override
	public List<Annuncio> list() throws AnnuncioDAOException {
		return entityManager.createQuery("from Annuncio", Annuncio.class).getResultList();
	}

	@Override
	public Optional<Annuncio> findOne(Long id) throws AnnuncioDAOException {
		Annuncio result = entityManager.find(Annuncio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Annuncio input) throws AnnuncioDAOException {
		entityManager.merge(input);
	}

	@Override
	public void insert(Annuncio input) throws AnnuncioDAOException {
		if (input == null) {
			throw new AnnuncioDAOException("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Annuncio input) throws AnnuncioDAOException {
		if (input == null) {
			throw new AnnuncioDAOException("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) throws AnnuncioDAOException {
		String and = " and ";
		Map<String, Object> paramaterMap = new HashMap<>();
		List<String> whereClauses = new ArrayList<>();

		StringBuilder queryBuilder = new StringBuilder("select distinct a from Annuncio a left join fetch a.categorie c where a.id = a.id and a.aperto = 1 ");

		if (StringUtils.isNotEmpty(example.getTitolo())) {
			whereClauses.add(" a.titolo  like :titolo ");
			paramaterMap.put("titolo", "%" + example.getTitolo() + "%");
		}
		if (example.getPrezzo() > 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo",example.getPrezzo());
		}
		if (!example.getCategorie().isEmpty()) {
			List<Long> listCategorieId = new ArrayList<>();
			for (Categoria categoria : example.getCategorie()) {
				listCategorieId.add(categoria.getId());
			}
			whereClauses.add("c.id in :listCategorieId ");
			paramaterMap.put("listCategorieId", listCategorieId);
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?and:"");
		queryBuilder.append(StringUtils.join(whereClauses, and));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (Map.Entry<String, Object> entry : paramaterMap.entrySet()) {
			typedQuery.setParameter(entry.getKey(), paramaterMap.get(entry.getKey()));
		}
		
		return typedQuery.getResultList();
	}
	
	@Override
	public List<Annuncio> personalFindByExample(Annuncio example, Long id) throws AnnuncioDAOException {
		String and = " and ";
		Map<String, Object> paramaterMap = new HashMap<>();
		List<String> whereClauses = new ArrayList<>();

		StringBuilder queryBuilder = new StringBuilder("select distinct a from Annuncio a left join fetch a.categorie c where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getTitolo())) {
			whereClauses.add(" a.titolo  like :titolo ");
			paramaterMap.put("titolo", "%" + example.getTitolo() + "%");
		}
		if (example.getPrezzo() > 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo",example.getPrezzo());
		}
		
		whereClauses.add(" a.utenteInserimento.id = :id ");
		paramaterMap.put("id", id);
		
		if (!example.getCategorie().isEmpty()) {
			List<Long> listCategorieId = new ArrayList<>();
			for (Categoria categoria : example.getCategorie()) {
				listCategorieId.add(categoria.getId());
			}
			whereClauses.add("c.id in :listCategorieId ");
			paramaterMap.put("listCategorieId", listCategorieId);
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?and:"");
		queryBuilder.append(StringUtils.join(whereClauses, and));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (Map.Entry<String, Object> entry : paramaterMap.entrySet()) {
			typedQuery.setParameter(entry.getKey(), paramaterMap.get(entry.getKey()));
		}
		
		return typedQuery.getResultList();
	}

	@Override
	public Optional<Annuncio> findOneEager(Long id) throws AnnuncioDAOException {
		return entityManager.createQuery("from Annuncio a left join fetch a.categorie join fetch a.utenteInserimento where a.id=:idAnnuncio", Annuncio.class)
				.setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

	
	
}
