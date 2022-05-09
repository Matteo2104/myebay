package it.prova.myebay.model;

public enum StatoUtente {
	ATTIVO,DISABILITATO,CREATO;
	
	public static StatoUtente fromString(String string) {
		if (string.equalsIgnoreCase(ATTIVO.toString())) {
			return ATTIVO;
		} else if (string.equalsIgnoreCase(DISABILITATO.toString())) {
			return DISABILITATO;
		} else if (string.equalsIgnoreCase(CREATO.toString())) {
			return CREATO;
		} else {
			return null;
		}
	}
}
