package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import it.prova.myebay.model.Acquisto;

public class AcquistoDAOImpl implements AcquistoDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Acquisto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Acquisto> list(long userId) throws Exception {
		return entityManager
				.createQuery("from Acquisto a left join fetch a.utenteAcquirente u where u.id = :idUtente", Acquisto.class)
				.setParameter("idUtente", userId).getResultList();
	}

	@Override
	public Optional<Acquisto> findOne(Long id) throws Exception {
		Acquisto result = entityManager.find(Acquisto.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Acquisto input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Acquisto input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Acquisto input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
