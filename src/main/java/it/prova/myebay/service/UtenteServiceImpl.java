package it.prova.myebay.service;

import it.prova.myebay.dao.UtenteDAO;

public class UtenteServiceImpl implements UtenteService {
	private UtenteDAO utenteDAO;
	
	@Override
	public void setUtenteDAO(UtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}
}
