package it.prova.myebay.utility;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public class Example {
	private static Utente utenteExample;
	private static Annuncio annuncioExample;
	
	private Example() {}

	public static Annuncio getAnnuncioExample() {
		return annuncioExample;
	}

	public static void setAnnuncioExample(Annuncio annuncioExample) {
		Example.annuncioExample = annuncioExample;
	}

	public static Utente getUtenteExample() {
		return utenteExample;
	}

	public static void setUtenteExample(Utente utenteExample) {
		Example.utenteExample = utenteExample;
	}
	
	
	
	
}
