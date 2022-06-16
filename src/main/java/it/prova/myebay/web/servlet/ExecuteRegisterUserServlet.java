package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.dto.UtenteInsert;
import it.prova.myebay.exception.UserRegisteredException;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/ExecuteRegisterUserServlet")
public class ExecuteRegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
       
   
    public ExecuteRegisterUserServlet() {
        super();
    }
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");

		// preparo un bean 
		UtenteInsert utenteInstance = UtilityForm.createUtenteInsertFromParams(nomeParam, cognomeParam, usernameParam, passwordParam, "ROLE_CLASSIC_USER");
		Utente utenteToRegister = null;
		Utente utenteInSession = null;
		
		try {
			
			// se la validazione non risulta ok
			if (!UtilityForm.validateUtenteInsertBean(utenteInstance)) {
				request.setAttribute("insert_utente_attr", utenteInstance);
				request.setAttribute(ERRORMESSAGE, "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher(Path.getPathInterfaccia() + "/register.jsp").forward(request, response);
				return;
			}
	
			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			utenteToRegister = utenteInstance.toModel();
			MyServiceFactory.getUtenteServiceInstance().registra(utenteToRegister);
			
			// se sono qui l'utente è stato registrato e salvato nel DB
			// quindi posso effettuare simultaneamente il login
			utenteInSession = MyServiceFactory.getUtenteServiceInstance().accedi(usernameParam, passwordParam);
			
			if (utenteInSession == null) {
				request.setAttribute(ERRORMESSAGE, "Si è verificato un errore in fase di login");
				request.getRequestDispatcher(Path.getPathInterfaccia() + "/login.jsp").forward(request, response);
			}
			
			request.getSession().setAttribute("userInfo", utenteInSession);
			
		} catch (UserRegisteredException e) {
			request.setAttribute(ERRORMESSAGE, "L'utente inserito è già esistente");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/login.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, "Attenzione si è verificato un errore.");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher(Path.getPathInterfaccia() + "/areapersonale.jsp").forward(request, response);
	}

}
