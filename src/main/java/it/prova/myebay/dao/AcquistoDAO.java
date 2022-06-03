package it.prova.myebay.dao;

import java.util.List;

import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {
	public List<Acquisto> list(long userId) throws Exception;
}
