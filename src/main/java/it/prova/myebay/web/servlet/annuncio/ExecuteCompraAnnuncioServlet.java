package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exception.MissedLoginException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/ExecuteCompraAnnuncioServlet")
public class ExecuteCompraAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteCompraAnnuncioServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		response.getWriter().append(idAnnuncio);
		
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		try {
			// esegue l'acquisto
			/*
			Utente utenteInSessione = request.getParameter("userInfo");
			if (utenteInSessione == null) {
				throw new MissedLoginException("Per effettuare acquisti bisogna essere iscritti");
			}
			MyServiceFactory.getAcquistoServiceInstance().acquista(Long.parseLong(idAnnuncio, utenteInSessione));
			request.setAttribute("list_acquisti_attr", MyServiceFactory.getAcquistoServiceInstance().listAll(utenteInSessione.getId()));
			*/
			
			response.getWriter().append("QUI ESEGUO L'ACQUISTO");
		} catch (MissedLoginException e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Non è stato possibile effettuare l'acquisto: " + e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		} // mettere altre eccezioni che possono avvenire durante la transazione

		//response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
		request.getRequestDispatcher("/acquisto/list.jsp").forward(request, response);
	}

}
