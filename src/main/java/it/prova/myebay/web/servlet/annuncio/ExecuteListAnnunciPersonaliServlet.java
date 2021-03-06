package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Example;
import it.prova.myebay.utility.Path;


@WebServlet("/annuncio/ExecuteListAnnunciPersonaliServlet")
public class ExecuteListAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteListAnnunciPersonaliServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = request;

		Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");

		try {
						
			Annuncio example = Example.getAnnuncioExample();
			if (example != null) {
				request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExamplePersonale(example, utenteInSessione.getId()));
			} else {
				request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().listAll(utenteInSessione.getId()));
			}
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore!");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/annuncio/list.jsp").forward(request, response);
	}

	

}
