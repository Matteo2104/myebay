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
       
 
    public PrepareSearchAnnunciServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			request.setAttribute("list_categorie_attr", MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Errore nell'esecuzione della ricerca");
			request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
		}
		
		// se vengo da una sessione scaduta
		if (request.getParameter("errorMessage") != null && request.getParameter("errorMessage").equals("ERROR"))
			request.setAttribute("errorMessage", "Sessione Scaduta");
		
		// se vengo da un logout
		if (request.getParameter("successMessage") != null && request.getParameter("successMessage").equals("LOGOUT"))
			request.setAttribute("successMessage", "Logout effettuato con successo");
		
		// la funzione di ricerca si trova nella home-page
		request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/index.jsp").forward(request, response);
	}

}
