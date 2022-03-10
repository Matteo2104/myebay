package it.prova.myebay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.myebay.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception {
		TypedQuery<Ruolo> query = entityManager
				.createQuery("select r from Ruolo r where r.descrizione=?1 and r.codice=?2", Ruolo.class)
				.setParameter(1, descrizione)
				.setParameter(2, codice);
		
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<Ruolo> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ruolo> findOne(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ruolo input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Ruolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(input);
	}

	@Override
	public void delete(Ruolo input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ruolo> findByExample(Ruolo example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
