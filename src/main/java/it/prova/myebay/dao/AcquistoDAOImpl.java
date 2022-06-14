package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import it.prova.myebay.exception.DAO.AcquistoDAOException;
import it.prova.myebay.model.Acquisto;

public class AcquistoDAOImpl implements AcquistoDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Acquisto> list(long userId) throws AcquistoDAOException {
		return entityManager
				.createQuery("from Acquisto a left join fetch a.utenteAcquirente u where u.id = :idUtente", Acquisto.class)
				.setParameter("idUtente", userId).getResultList();
	}

	@Override
	public Optional<Acquisto> findOne(Long id) throws AcquistoDAOException {
		Acquisto result = entityManager.find(Acquisto.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void insert(Acquisto input) throws AcquistoDAOException {
		if (input == null) {
			throw new AcquistoDAOException("Problema valore in input");
		}
		entityManager.persist(input);
	}

}
