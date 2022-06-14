package it.prova.myebay.service;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.dao.AcquistoDAOImpl;
import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.AnnuncioDAOImpl;
import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.dao.CategoriaDAOImpl;
import it.prova.myebay.dao.UtenteDAO;
import it.prova.myebay.dao.UtenteDAOImpl;

public class MyServiceFactory {
	private static AnnuncioService annuncioServiceInstance;
	private static AcquistoService acquistoServiceInstance;
	private static CategoriaService categoriaServiceInstance;
	private static UtenteService utenteServiceInstance;
	
	private static AnnuncioDAO annuncioDaoInstance;
	private static AcquistoDAO acquistoDaoInstance;
	private static CategoriaDAO categoriaDaoInstance;
	private static UtenteDAO utenteDaoInstance;
	
	private MyServiceFactory() {}

	public static UtenteService getUtenteServiceInstance() {
		if (utenteServiceInstance == null)
			utenteServiceInstance = new UtenteServiceImpl();

		if (utenteDaoInstance == null)
			utenteDaoInstance = new UtenteDAOImpl();

		utenteServiceInstance.setUtenteDAO(utenteDaoInstance);
		return utenteServiceInstance;
	}



	public static CategoriaService getCategoriaServiceInstance() {
		if (categoriaServiceInstance == null)
			categoriaServiceInstance = new CategoriaServiceImpl();

		if (categoriaDaoInstance == null)
			categoriaDaoInstance = new CategoriaDAOImpl();

		categoriaServiceInstance.setCategoriaDAO(categoriaDaoInstance);

		return categoriaServiceInstance;
	}

	public static AnnuncioService getAnnuncioServiceInstance() {
		if (annuncioServiceInstance == null)
			annuncioServiceInstance = new AnnuncioServiceImpl();

		if (annuncioDaoInstance == null)
			annuncioDaoInstance = new AnnuncioDAOImpl();

		annuncioServiceInstance.setAnnuncioDAO(annuncioDaoInstance);

		return annuncioServiceInstance;
	}
	
	public static AcquistoService getAcquistoServiceInstance() {
		if (acquistoServiceInstance == null)
			acquistoServiceInstance = new AcquistoServiceImpl();

		if (acquistoDaoInstance == null)
			acquistoDaoInstance = new AcquistoDAOImpl();
		
		if (annuncioDaoInstance == null)
			annuncioDaoInstance = new AnnuncioDAOImpl();
		
		if (utenteDaoInstance == null)
			utenteDaoInstance = new UtenteDAOImpl();

		acquistoServiceInstance.setAcquistoDAO(acquistoDaoInstance);
		acquistoServiceInstance.setAnnuncioDAO(annuncioDaoInstance);
		acquistoServiceInstance.setUtenteDAO(utenteDaoInstance);

		return acquistoServiceInstance;
	}
	
}
