package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/annuncio/ExecuteListAnnunciPersonaliServlet")
public class ExecuteListAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteListAnnunciPersonaliServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");

		try {
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().listAll(utenteInSessione.getId()));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore!");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/annuncio/list.jsp").forward(request, response);
	}

	

}
