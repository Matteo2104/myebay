package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.dto.UtenteEdit;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Example;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/ExecuteEditUserServlet")
public class ExecuteEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
       
    
    public ExecuteEditUserServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");

		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute(ERRORMESSAGE, "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		// estraggo gli altri input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String statoParam = request.getParameter("stato");
		String ruoloParam = request.getParameter("ruolo");
		
		// preparo un bean 
		UtenteEdit utenteInstance = UtilityForm.createUtenteEditFromParams(nomeParam, cognomeParam, usernameParam, statoParam, ruoloParam);

		try {
			
			
			// eseguo la validazione, se non va a buon fine allora ritorno a edit.jsp
			if (!UtilityForm.validateUtenteBeanForEdit(utenteInstance)) {
								
				request.setAttribute("edit_utente_attr", utenteInstance);
				
				request.setAttribute(ERRORMESSAGE, "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/edit.jsp").forward(request, response);
				return;
			}
			
			// se sono qui l'operazione di validazione è andata a buon fine
			Utente utenteDaModificare = MyServiceFactory.getUtenteServiceInstance().findById(Long.parseLong(idUser)); 
			Utente utenteModel = utenteInstance.toModel();
			
		
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteDaModificare, utenteModel);
			
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, "Attenzione si è verificato un errore: " + e);
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		Example.setUtenteExample(null);
		response.sendRedirect("ExecuteListUserServlet?operationResult=SUCCESS");

	}

}
