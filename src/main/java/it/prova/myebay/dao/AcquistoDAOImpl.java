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
	public Optional<Acquisto> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Acquisto input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Acquisto input) throws Exception {
		// TODO Auto-generated method stub
		
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
