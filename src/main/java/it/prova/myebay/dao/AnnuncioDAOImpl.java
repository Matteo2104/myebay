package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.model.Annuncio;

public class AnnuncioDAOImpl implements AnnuncioDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Annuncio> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Annuncio> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Annuncio input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Annuncio input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Annuncio input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
