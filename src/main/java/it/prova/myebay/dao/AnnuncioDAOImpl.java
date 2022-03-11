package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;



public class AnnuncioDAOImpl implements AnnuncioDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Annuncio> list(long id) throws Exception {
		return entityManager.createQuery("from Annuncio a join fetch a.utenteInserimento u where u.id = :id", Annuncio.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<Annuncio> list() throws Exception {
		return entityManager.createQuery("from Annuncio", Annuncio.class).getResultList();
	}

	@Override
	public Optional<Annuncio> findOne(Long id) throws Exception {
		Annuncio result = entityManager.find(Annuncio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Annuncio input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Annuncio input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Annuncio input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Annuncio a join a.categorie c where a.id = a.id and a.aperto = 1 ");

		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() > 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo",example.getPrezzo());
		}
		if (example.getCategorie() != null) {
			List<Long> listCategorieId = new ArrayList<>();
			for (Categoria categoria : example.getCategorie()) {
				listCategorieId.add(categoria.getId());
			}
			whereClauses.add("c.id in :listCategorieId ");
			paramaterMap.put("listCategorieId", listCategorieId);
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
	@Override
	public List<Annuncio> personalFindByExample(Annuncio example, Long id) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Annuncio a join a.categorie c where a.id = a.id and a.aperto = 1 ");

		if (StringUtils.isNotEmpty(example.getTestoAnnuncio())) {
			whereClauses.add(" a.testoAnnuncio  like :testoAnnuncio ");
			paramaterMap.put("testoAnnuncio", "%" + example.getTestoAnnuncio() + "%");
		}
		if (example.getPrezzo() > 0) {
			whereClauses.add(" a.prezzo >= :prezzo ");
			paramaterMap.put("prezzo",example.getPrezzo());
		}
		
		whereClauses.add(" a.utenteInserimento.id = :id ");
		paramaterMap.put("id", id);
		
		if (example.getCategorie() != null) {
			List<Long> listCategorieId = new ArrayList<>();
			for (Categoria categoria : example.getCategorie()) {
				listCategorieId.add(categoria.getId());
			}
			whereClauses.add("c.id in :listCategorieId ");
			paramaterMap.put("listCategorieId", listCategorieId);
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Annuncio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Annuncio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Optional<Annuncio> findOneEager(Long id) throws Exception {
		return entityManager.createQuery("from Annuncio a left join fetch a.categorie join fetch a.utenteInserimento where a.id=:idAnnuncio", Annuncio.class)
				.setParameter("idAnnuncio", id).getResultList().stream().findFirst();
	}

	
	
}
