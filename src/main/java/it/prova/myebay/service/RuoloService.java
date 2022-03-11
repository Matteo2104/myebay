package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.RuoloDAO;
import it.prova.myebay.model.Ruolo;

public interface RuoloService {

	public void setRuoloDAO(RuoloDAO ruoloDAO);

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public List<Ruolo> listAll() throws Exception;

}
