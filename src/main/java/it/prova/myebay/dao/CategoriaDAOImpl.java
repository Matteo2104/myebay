package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class CategoriaDAOImpl implements CategoriaDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Optional<Categoria> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Categoria input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(input);
	}

	@Override
	public void delete(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> findByExample(Categoria example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Acquisto> list(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Categoria findByCodice(String codice) throws Exception {
		TypedQuery<Categoria> query = entityManager.createQuery("from Categoria c where c.codice = :codice", Categoria.class);
		query.setParameter("codice", codice);
		return query.getResultStream().findFirst().orElse(null);
	}

	

}
