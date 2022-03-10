package it.prova.myebay.service;

import it.prova.myebay.dao.AcquistoDAO;

public class AcquistoServiceImpl implements AcquistoService {
	private AcquistoDAO acquistoDAO;
	
	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}
}
