package it.prova.myebay.dao;

import it.prova.myebay.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {

	Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
