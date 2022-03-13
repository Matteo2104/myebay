package it.prova.myebay.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/ExecuteRegisterUserServlet")
public class ExecuteRegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteRegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Utente utenteInstance = UtilityForm.createUtenteFromParamsForRegister(nomeParam, cognomeParam, usernameParam, passwordParam);
		try {
			
			// se la validazione non risulta ok
			if (!UtilityForm.validateUtenteBean(utenteInstance)) {
				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
	
			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			utenteInstance.setStato(StatoUtente.CREATO);
			utenteInstance.setDateCreated(new Date());
			utenteInstance.getRuoli().add(new Ruolo(2));
		
		
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}


		//request.setAttribute("inputUsername", usernameParam);
		request.setAttribute("successMessage", "Registrazione avvenuta correttamente");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
