package it.prova.myebay.web.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.utility.Example;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/ExecuteSearchAnnunciServlet")
public class ExecuteSearchAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteSearchAnnunciServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titoloInput = request.getParameter("titolo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");
		
		// creo un bean
		// il secondo parametro è vuoto perche la ricerca avviene per titolo
		Annuncio example = UtilityForm.createAnnuncioFromParams(titoloInput, "", prezzoInput, categorieIdInput);
				
		try {
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore nella ricerca!");
			request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		Example.annuncioExample = example;
		request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/list.jsp").forward(request, response);
		//response.getWriter().append("fin qui tutto ok");
	}

}
