package it.prova.myebay.utility;

import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.model.Annuncio;

public class Example {
	private static UtenteSearch utenteExample;
	private static Annuncio annuncioExample;
	
	private Example() {}

	public static Annuncio getAnnuncioExample() {
		return annuncioExample;
	}

	public static void setAnnuncioExample(Annuncio annuncioExample) {
		Example.annuncioExample = annuncioExample;
	}

	public static UtenteSearch getUtenteExample() {
		return utenteExample;
	}

	public static void setUtenteExample(UtenteSearch utenteExample) {
		Example.utenteExample = utenteExample;
	}
	
	
	
	
}
