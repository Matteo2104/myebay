package it.prova.myebay.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.exception.DAO.CategoriaDAOException;
import it.prova.myebay.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Categoria> list() throws CategoriaDAOException {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	

	@Override
	public void insert(Categoria input) throws CategoriaDAOException {
		if (input == null) {
			throw new CategoriaDAOException("Problema valore in input");
		}

		entityManager.persist(input);
	}
	
	@Override
	public Categoria findByCodice(String codice) throws CategoriaDAOException {
		TypedQuery<Categoria> query = entityManager.createQuery("from Categoria c where c.codice = :codice", Categoria.class);
		query.setParameter("codice", codice);
		return query.getResultStream().findFirst().orElse(null);
	}
	

}
