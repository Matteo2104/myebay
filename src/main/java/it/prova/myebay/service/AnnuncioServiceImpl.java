package it.prova.myebay.service;


import it.prova.myebay.dao.AnnuncioDAO;

public class AnnuncioServiceImpl implements AnnuncioService {
	private AnnuncioDAO annuncioDAO;
	
	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}
}
