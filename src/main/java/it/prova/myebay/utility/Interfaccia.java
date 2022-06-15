package it.prova.myebay.utility;

public class Interfaccia {
	private static int type;
	
	private Interfaccia() {}

	public static int getType() {
		return type;
	}

	public static void setType(int type) {
		Interfaccia.type = type;
	}
}
