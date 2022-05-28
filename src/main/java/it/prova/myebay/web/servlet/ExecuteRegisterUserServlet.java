package it.prova.myebay.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.dto.UtenteInsert;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/ExecuteRegisterUserServlet")
public class ExecuteRegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteRegisterUserServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		
		String idAnnuncio = Path.ID;
		Path.ID = "";

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		UtenteInsert utenteInstance = UtilityForm.createUtenteInsertFromParams(nomeParam, cognomeParam, usernameParam, passwordParam, "ROLE_CLASSIC_USER");
		Utente utenteToRegister = null;
		Utente utenteInSession = null;
		String destinazione = null;
		
		try {
			
			// se la validazione non risulta ok
			if (!UtilityForm.validateUtenteInsertBean(utenteInstance)) {
				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/register.jsp").forward(request, response);
				return;
			}
	
			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			utenteToRegister = utenteInstance.toModel();
			MyServiceFactory.getUtenteServiceInstance().registra(utenteToRegister);
			
			// se sono qui l'utente è registrato e salvato nel DB
			// quindi posso effettuare simultaneamente il login
			utenteInSession = MyServiceFactory.getUtenteServiceInstance().accedi(usernameParam, passwordParam);
			
			
			if (utenteInSession == null) {
				request.setAttribute("errorMessage", "Si è verificato un errore in fase di login");
				destinazione = Path.PATH_INTERFACCIA + "/login.jsp";
			} else {
				request.getSession().setAttribute("userInfo", utenteInSession);
			
				if (idAnnuncio.isEmpty()) {
					destinazione = Path.PATH_INTERFACCIA + "/areapersonale.jsp";
				} else {
					request.setAttribute("show_annuncio_attr", MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio)));
					destinazione = Path.PATH_INTERFACCIA + "/show.jsp";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher(destinazione).forward(request, response);
	}

}
