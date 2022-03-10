package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Categoria> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
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

	

}
