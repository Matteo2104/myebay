package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.dto.UtenteEdit;
import it.prova.myebay.dto.UtenteInsert;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;

public class UtilityForm {
	
	private UtilityForm() {}
	
	public static Annuncio createAnnuncioFromParams(String titolo, String testoAnnuncio, String prezzo, String[] categorie) {
		Annuncio result = new Annuncio(titolo, testoAnnuncio);
		
		if (NumberUtils.isCreatable(prezzo) && Integer.parseInt(prezzo) > 0) 
				result.setPrezzo(Integer.parseInt(prezzo));
		
		result.setAperto(true);
				
		if (categorie == null || categorie.length < 1) {
			
			result.setCategorie(null);
		
		} else {
			
			for (int i = 0; i < categorie.length; i++) {
				
				result.getCategorie().add(new Categoria(Long.parseLong(categorie[i])));
				
			}
			
		}
		
		return result;
	}
	
	public static UtenteSearch createUtenteSearchFromParams(String nome, String cognome, String username, String dateCreated, String[] ruoli) {
		UtenteSearch utenteDTO = new UtenteSearch();
		utenteDTO.setNome(nome);
		utenteDTO.setCognome(cognome);
		utenteDTO.setUsername(username);

		if (parseDateFromString(dateCreated) != null) 
			utenteDTO.setDateCreated(parseDateFromString(dateCreated));
		
		
		if (ruoli == null || ruoli.length < 1) {
			utenteDTO.setRuoli(null);
		} else {
			for (int i=0;i<ruoli.length;i++) {
				
				utenteDTO.getRuoli().add(Ruolo.fromString(ruoli[i]));
				
			}
		}
		
		return utenteDTO;
	}
	
	public static UtenteInsert createUtenteInsertFromParams(String nome, String cognome, String username, String password, String ruolo) {
		UtenteInsert utenteDTO = new UtenteInsert();
		utenteDTO.setNome(nome);
		utenteDTO.setCognome(cognome);
		utenteDTO.setUsername(username);
		utenteDTO.setPassword(password);
		utenteDTO.setRuolo(Ruolo.fromString(ruolo));		
		
		return utenteDTO;
	}
	
	public static UtenteEdit createUtenteEditFromParams(String nome, String cognome, String username, String stato, String ruolo) {
		UtenteEdit utenteDTO = new UtenteEdit();
		utenteDTO.setNome(nome);
		utenteDTO.setCognome(cognome);
		utenteDTO.setUsername(username);
		utenteDTO.setStato(StatoUtente.fromString(stato));
		utenteDTO.setRuolo(Ruolo.fromString(ruolo));
		
		return utenteDTO;
	}
	
	public static Utente createUtenteFromParamsForRegister(String nome, String cognome, String username, String password) {
		return new Utente(nome, cognome, username, password);
	}
	
	
	public static boolean validateUtenteInsertBean(UtenteInsert utenteToBeValidated) {
		return (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername()) 
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())
				|| utenteToBeValidated.getRuolo() == null);
	}
	
	public static boolean validateUtenteBeanForEdit(UtenteEdit utenteToBeValidated) {
		return (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername()) 
				|| utenteToBeValidated.getStato() == null);
	}
	
	public static boolean validateAnnuncioBean(Annuncio annuncio) {
		return (StringUtils.isBlank(annuncio.getTestoAnnuncio())
				|| annuncio.getPrezzo() < 1);
	}
	
	public static Date parseDateFromString(String dateStringParam) {
		if (StringUtils.isBlank(dateStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
