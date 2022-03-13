package it.prova.myebay.web.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.utility.UtilityForm;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/ExecuteSearchAnnunciServlet")
public class ExecuteSearchAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteSearchAnnunciServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");
		
		// creo un bean
		Annuncio example = UtilityForm.createAnnuncioFromParams(testoInput, prezzoInput, categorieIdInput);
		
		//System.out.println(example);
		
		try {
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore nella ricerca!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		//response.getWriter().append("fin qui tutto ok");
	}

}
