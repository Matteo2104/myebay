package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String errorMessage = "errorMessage";
	private static final String loginJsp = "/login.jsp";
       
    
    public LoginServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		// validazione dei campi
		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute(errorMessage, "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher(Path.pathInterfaccia + loginJsp).forward(request, response);
			return;
		}

		String destinazione = null;

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			if (utenteInstance == null) {
				request.setAttribute(errorMessage, "Utente non trovato.");
				destinazione = Path.pathInterfaccia + loginJsp;
			} else {
				request.getSession().setAttribute("userInfo", utenteInstance);
				destinazione = Path.pathInterfaccia + "/areapersonale.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			destinazione = Path.pathInterfaccia + loginJsp;
			request.setAttribute(errorMessage, "Si è verificato un errore");
		}

		request.getRequestDispatcher(destinazione).forward(request, response);
	}
	
}
