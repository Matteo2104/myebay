package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("")
public class PrepareSearchAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String SUCCESSMESSAGE = "successMessage";
       
 
    public PrepareSearchAnnunciServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			request.setAttribute("list_categorie_attr", MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, "Errore nella visualizzazione delle categorie");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
		}
		
		// se vengo da una sessione scaduta
		if (request.getParameter(ERRORMESSAGE) != null && request.getParameter(ERRORMESSAGE).equals("ERROR"))
			request.setAttribute(ERRORMESSAGE, "Sessione Scaduta");
		
		// se vengo da un logout
		if (request.getParameter(SUCCESSMESSAGE) != null && request.getParameter(SUCCESSMESSAGE).equals("LOGOUT"))
			request.setAttribute(SUCCESSMESSAGE, "Logout effettuato con successo");
		
		// la funzione di ricerca si trova nella home-page
		request.getRequestDispatcher(Path.getPathInterfaccia() + "/index.jsp").forward(request, response);
	}

}
