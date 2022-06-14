package it.prova.myebay.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.exception.AdminCannotBuyException;
import it.prova.myebay.exception.InsufficientCreditException;
import it.prova.myebay.exception.service.AcquistoServiceException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AcquistoServiceImpl implements AcquistoService {
	private AcquistoDAO acquistoDAO;
	private AnnuncioDAO annuncioDAO;
	private UtenteDAO utenteDAO;
	
	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}
	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}
	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}
	
	@Override
	public void acquista(Long id, Utente utenteInSessione) throws AcquistoServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			// uso l'injection per i vari DAO
			acquistoDAO.setEntityManager(entityManager);
			annuncioDAO.setEntityManager(entityManager);
			utenteDAO.setEntityManager(entityManager);

			// comincio la sequenza di azioni per l'acquisto caricando l'annuncio 
			Annuncio annuncioDaAcquistare = annuncioDAO.findOne(id).orElse(null);
			if (annuncioDaAcquistare == null) {
				throw new AcquistoServiceException("Errore nel caricamento dell'annuncio");
			}
			
			// verifico che l'utente sia abilitato all'acquisto
			if (utenteInSessione.getRuolo() == Ruolo.ROLE_ADMIN) 
				throw new AdminCannotBuyException("Non si hanno i permessi per effettuare l'acquisto");
			
			// verifico che il credito sia sufficiente all'acquisto
			if (utenteInSessione.getCreditoResiduo() < annuncioDaAcquistare.getPrezzo()) {
				throw new InsufficientCreditException("Credito Insufficiente");
			}
			
			// se il credito è sufficiente sottraggo l'importo
			utenteInSessione.sottraiDaCreditoResiduo(annuncioDaAcquistare.getPrezzo());	
			
			// ... e chiudo l'annuncio
			annuncioDaAcquistare.setAperto(false);
			
			// effettuo le modifiche 
			utenteDAO.update(utenteInSessione);
			annuncioDAO.update(annuncioDaAcquistare);
			
			// creo una nuova istanza di acquisto e lo aggiungo al DB collegandolo all'utente 
			Acquisto acquisto = new Acquisto(annuncioDaAcquistare.getTitolo(), annuncioDaAcquistare.getPrezzo(), new Date(), utenteInSessione);
			acquistoDAO.insert(acquisto);
			
			// se si è arrivati fin qui l'acquisto è andato a buon fine e posso eseguire il commit
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public List<Acquisto> listAll(Long id) throws AcquistoServiceException {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			
			// uso l'injection per i vari DAO
			acquistoDAO.setEntityManager(entityManager);
			
			return acquistoDAO.list(id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
	
	@Override
	public Acquisto caricaSingoloElemento(Long id) throws AcquistoServiceException {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}
}
