package it.prova.myebay.service;

import java.util.List;
import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.exception.service.AcquistoServiceException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Utente;

public interface AcquistoService {

	public void setAcquistoDAO(AcquistoDAO acquistoDAO);
	
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
	
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public void acquista(Long id, Utente utenteInSessione) throws AcquistoServiceException;
	
	public List<Acquisto> listAll(Long id) throws AcquistoServiceException;
	
	public Acquisto caricaSingoloElemento(Long id) throws AcquistoServiceException;
	

}
