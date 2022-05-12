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
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSearchAnnunciPersonaliServlet
 */
@WebServlet("/annuncio/ExecuteSearchAnnunciPersonaliServlet")
public class ExecuteSearchAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteSearchAnnunciPersonaliServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		// creo un bean
		Annuncio example = UtilityForm.createAnnuncioPersonaleSearchFromParams(testoInput, prezzoInput, categorieIdInput);
		
		System.out.println(example);
		
		try {
			request.setAttribute("annunci_list_attribute",
					MyServiceFactory.getAnnuncioServiceInstance().findByExamplePersonale(example, ((Utente)httpRequest.getSession().getAttribute("userInfo")).getId()));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore nella ricerca!");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/annuncio/list.jsp").forward(request, response);
		//response.getWriter().append("fin qui tutto ok");
	}

}
