package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.dto.UtenteEdit;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/ExecuteEditUserServlet")
public class ExecuteEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteEditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");

		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String statoParam = request.getParameter("stato");
		String ruoloParam = request.getParameter("ruolo");

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		UtenteEdit utenteInstance = UtilityForm.createUtenteEditFromParams(nomeParam, cognomeParam, usernameParam, statoParam, ruoloParam);

		try {
			
			
			// eseguo la validazione (i campi non possono essere vuoti) se non va a buon fine allora ritorno all'edit
			if (!UtilityForm.validateUtenteBeanForEdit(utenteInstance)) {
				
				Utente utente = MyServiceFactory.getUtenteServiceInstance().findById(Long.parseLong(idUser));
				
				request.setAttribute("edit_utente_attr", utenteInstance);
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/utente/edit.jsp").forward(request, response);
				return;
			}
			
			// se sono qui l'operazione di validazione è andata a buon fine...
			// ...carico l'utente corrispondente alla modifica
			Utente utenteDaModificare = MyServiceFactory.getUtenteServiceInstance().findById(Long.parseLong(idUser)); 
			Utente utenteModel = utenteInstance.toModel();
			
		
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteDaModificare, utenteModel);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: " + e);
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		//request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
		response.sendRedirect("ExecuteListUserServlet?operationResult=SUCCESS");

	}

}
