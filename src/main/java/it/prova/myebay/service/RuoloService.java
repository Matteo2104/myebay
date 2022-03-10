package it.prova.myebay.service;

import it.prova.myebay.dao.RuoloDAO;
import it.prova.myebay.model.Ruolo;

public interface RuoloService {

	public void setRuoloDAO(RuoloDAO ruoloDAO);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

}
