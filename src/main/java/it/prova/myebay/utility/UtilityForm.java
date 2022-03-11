package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;
import it.prova.myebay.model.Ruolo;

public class UtilityForm {
	public static Annuncio createAnnuncioFromParams(String testoAnnuncio, String prezzo, String[] categorie) {
		Annuncio result = new Annuncio(testoAnnuncio);
		
		if (NumberUtils.isCreatable(prezzo) && !prezzo.equals("0")) {
			result.setPrezzo(Integer.parseInt(prezzo));
		}
		
		result.setAperto(true);
				
		if (categorie == null || categorie.length < 1) {
			result.setCategorie(null);
		} else {
			for (int i=0;i<categorie.length;i++) {
				try {
					result.getCategorie().add(new Categoria(Long.parseLong(categorie[i])));
				} catch (Exception e) {
					
				}
			}
		}
		
		return result;
	}
	
	public static Utente createUtenteFromParams(String nome, String cognome, String username, String campoVariabile, String[] ruoli) {
		Utente result = new Utente(nome, cognome, username);
		
		if (campoVariabile != null) {
			if (parseDateArrivoFromString(campoVariabile) == null) {
				result.setPassword(campoVariabile);
			} else {
				result.setDateCreated(parseDateArrivoFromString(campoVariabile));
			}
		}
		
		if (ruoli == null || ruoli.length < 1) {
			result.setRuoli(null);
		} else {
			for (int i=0;i<ruoli.length;i++) {
				try {
					result.getRuoli().add(new Ruolo(Long.parseLong(ruoli[i])));
				} catch (Exception e) {
					
				}
			}
		}
		
		return result;
	}
	
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername()) 
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateAnnuncioBean(Annuncio annuncio) {
		if (StringUtils.isBlank(annuncio.getTestoAnnuncio())
				|| annuncio.getPrezzo() < 1
				|| annuncio.getCategorie().size() < 1) {
			return false;
		}
		return true;
	}
	
	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
