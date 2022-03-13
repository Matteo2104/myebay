package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exception.InsufficientCreditException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.PathRitorno;


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
		
		//System.out.println("SONO TORNATO IN COMPRA ANNUNCIO");
		
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			idAnnuncio = PathRitorno.ID;
			
			if (!NumberUtils.isCreatable(idAnnuncio)) {
				request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
				request.getRequestDispatcher("error.jsp").forward(request, response);
				return;
			}
			
			PathRitorno.ID = "";
		}

		try {
			// esegue l'acquisto
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			MyServiceFactory.getAcquistoServiceInstance().acquista(Long.parseLong(idAnnuncio), utenteInSessione);			
			
		} catch (InsufficientCreditException e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		} // mettere altre eccezioni che possono avvenire durante la transazione

		response.sendRedirect(request.getContextPath() + "/acquisto/ExecuteListAcquistiServlet?operationResult=SUCCESS");
		//request.getRequestDispatcher("ExecuteListAcquistiServlet").forward(request, response);
		
		//System.out.println("STO ANDANDO A LIST");
	}
}
