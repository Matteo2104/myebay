package it.prova.myebay.model;


public enum Ruolo {
	ROLE_ADMIN, ROLE_CLASSIC_USER;
	
	public static Ruolo fromString(String string) {
		if (string.equalsIgnoreCase(ROLE_ADMIN.toString())) {
			return ROLE_ADMIN;
		} else if (string.equalsIgnoreCase(ROLE_CLASSIC_USER.toString())) {
			return ROLE_CLASSIC_USER;
		} else {
			return null;
		}
	}
}
