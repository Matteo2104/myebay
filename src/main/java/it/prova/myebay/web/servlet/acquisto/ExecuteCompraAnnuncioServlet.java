package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.exception.InsufficientCreditException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;

@WebServlet("/acquisto/ExecuteCompraAnnuncioServlet")
public class ExecuteCompraAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    public ExecuteCompraAnnuncioServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		/*
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			idAnnuncio = Path.ID;
			
			if (!NumberUtils.isCreatable(idAnnuncio)) {
				request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
				request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
				return;
			}
			
			Path.ID = "";
		}
		*/

		try {
			// esegue l'acquisto
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			MyServiceFactory.getAcquistoServiceInstance().acquista(Long.parseLong(idAnnuncio), utenteInSessione);			
			
		} catch (InsufficientCreditException e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e.getMessage());
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e);
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		} // mettere altre eccezioni che possono avvenire durante la transazione

		response.sendRedirect(request.getContextPath() + "/acquisto/ExecuteListAcquistiServlet?operationResult=SUCCESS");
	}
}
