package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.dto.UtenteInsert;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/ExecuteInsertUserServlet")
public class ExecuteInsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteInsertUserServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String ruoloParam = request.getParameter("ruolo");

		// preparo un bean 
		UtenteInsert utenteDTO = UtilityForm.createUtenteInsertFromParams(nomeParam, cognomeParam, usernameParam, passwordParam, ruoloParam);
		
		try {
			
			// se la validazione non risulta ok
			if (!UtilityForm.validateUtenteInsertBean(utenteDTO)) {
				request.setAttribute("insert_utente_attr", utenteDTO);
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/insert.jsp").forward(request, response);
				return;
			}
	
			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			Utente utenteToInsert = utenteDTO.toModel();
			utenteToInsert.setStato(StatoUtente.CREATO);
			utenteToInsert.setDateCreated(new Date());
			utenteToInsert.setCreditoResiduo(0);
		
		
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteToInsert);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/insert.jsp").forward(request, response);
			return;
		}

		// uso il sendRedirect con parametro per evitare il problema del double save on refresh
		response.sendRedirect("ExecuteListUserServlet?operationResult=SUCCESS");
	}

}
