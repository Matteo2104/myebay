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
import it.prova.myebay.utility.PathRitorno;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		String destinazione = null;

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Utente non trovato.");
				destinazione = "login.jsp";
			} else {
				request.getSession().setAttribute("userInfo", utenteInstance);
				
				// se ho interrotto la navigazione per loggarmi ritorno al punto in cui stavo
				System.out.println(PathRitorno.ID);
				
				destinazione = PathRitorno.PATH_RITORNO==""?"areapersonale.jsp":PathRitorno.PATH_RITORNO;
				
				// ripristino il path di ritorno a una stringa vuota
				PathRitorno.PATH_RITORNO = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			destinazione = "login.jsp";
			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
		}

		request.getRequestDispatcher(destinazione).forward(request, response);
	}
	
}
