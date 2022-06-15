package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.exception.AdminCannotBuyException;
import it.prova.myebay.exception.InsufficientCreditException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;

@WebServlet("/acquisto/ExecuteCompraAnnuncioServlet")
public class ExecuteCompraAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String ACQUISTONONRIUSCITO = "Non è stato possibile effettuare l'acquisto: ";
	private static final String ERRORJSP = "/error.jsp";
	
    
    public ExecuteCompraAnnuncioServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = request;
		String idAnnuncio = request.getParameter("idAnnuncio");

		try {
			// esegue l'acquisto
			Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");
			MyServiceFactory.getAcquistoServiceInstance().acquista(Long.parseLong(idAnnuncio), utenteInSessione);			
			
		} catch (AdminCannotBuyException e) {
			request.setAttribute(ERRORMESSAGE, ACQUISTONONRIUSCITO + e.getMessage());
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
			return;
		} catch (InsufficientCreditException e) {
			request.setAttribute(ERRORMESSAGE, ACQUISTONONRIUSCITO + e.getMessage());
			request.setAttribute("insufficientCredit", 1);
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, ACQUISTONONRIUSCITO + e);
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
			return;
		} 

		response.sendRedirect(request.getContextPath() + "/acquisto/ExecuteListAcquistiServlet?operationResult=SUCCESS");
	}
}
