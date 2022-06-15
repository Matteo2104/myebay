package it.prova.myebay.utility;

public class Path {	
	private static String pathInterfaccia = "";
	
	private Path() {}
	
	public static String getPathInterfaccia() {
		return pathInterfaccia;
	}

	public static void setPathInterfaccia(String pathInterfaccia) {
		Path.pathInterfaccia = pathInterfaccia;
	}
	
	// inizializza il path dell'interfaccia
	public static void initPathInterfaccia() {
		if (Interfaccia.getType() == 0) 
			Interfaccia.setType(1);
		
		pathInterfaccia = Interfaccia.getType() + "interfaccia";
	}
	
	// esegue un refresh del path dell'interfaccia
	public static void switchPathInterfaccia() {
		if (Interfaccia.getType() == 1) {
			Interfaccia.setType(2);
		} else {
			Interfaccia.setType(1);;
		}
		
		pathInterfaccia = Interfaccia.getType() + "interfaccia";
	}
}
