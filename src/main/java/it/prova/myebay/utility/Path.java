package it.prova.myebay.utility;

public class Path {	
	public static String pathInterfaccia = "";
	
	private Path() {}
	
	// inizializza il path dell'interfaccia
	public static void initPathInterfaccia() {
		if (Interfaccia.type == 0) 
			Interfaccia.type = 1;
		
		pathInterfaccia = Interfaccia.type + "interfaccia";
	}
	
	// esegue un refresh del path dell'interfaccia
	public static void switchPathInterfaccia() {
		if (Interfaccia.type == 1) {
			Interfaccia.type = 2;
		} else {
			Interfaccia.type = 1;
		}
		
		pathInterfaccia = Interfaccia.type + "interfaccia";
	}
}
