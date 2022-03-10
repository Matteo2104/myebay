package it.prova.myebay.service;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.model.Utente;

public interface AcquistoService {

	public void setAcquistoDAO(AcquistoDAO acquistoDAO);
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
	public void setUtenteDAO(UtenteDAO utenteDAO);

	public void acquista(Long id, Utente utenteInSessione) throws Exception;
	

}
