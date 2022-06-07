package it.prova.myebay.utility;

public class Path {
	public static String PATH_RITORNO = "";
	
	public static String PATH_INTERFACCIA = "";
	
	// inizializza il path dell'interfaccia
	public static void initPathInterfaccia() {
		if (Interfaccia.TYPE == 0) 
			Interfaccia.TYPE = 1;
		
		PATH_INTERFACCIA = Interfaccia.TYPE + "interfaccia";
	}
	
	// esegue un refresh del path dell'interfaccia
	public static void switchPathInterfaccia() {
		if (Interfaccia.TYPE == 1) {
			Interfaccia.TYPE = 2;
		} else {
			Interfaccia.TYPE = 1;
		}
		
		PATH_INTERFACCIA = Interfaccia.TYPE + "interfaccia";
		
		System.out.println(Interfaccia.TYPE);
	}
}
