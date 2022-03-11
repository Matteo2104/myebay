package it.prova.myebay.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

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
	
	public static boolean validateAnnuncioBean(Annuncio annuncio) {
		if (StringUtils.isBlank(annuncio.getTestoAnnuncio())
				|| annuncio.getPrezzo() < 1) {
			return false;
		}
		return true;
	}
}
