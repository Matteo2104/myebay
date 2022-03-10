package it.prova.myebay.service;

import it.prova.myebay.dao.RuoloDAO;

public class RuoloServiceImpl implements RuoloService {
	private RuoloDAO ruoloDAO;
	
	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}
}
