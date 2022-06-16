package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/ExecuteLoginByAnnuncioServlet")
public class ExecuteLoginByAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ERRORMESSAGE = "errorMessage";
    
    public ExecuteLoginByAnnuncioServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		if (idAnnuncio==null || !NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute(ERRORMESSAGE, "Errore: id non è numerico: pagina execute");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}

		// validazione dei campi
		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute(ERRORMESSAGE, "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/login.jsp").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			if (utenteInstance == null) {
				request.setAttribute(ERRORMESSAGE, "Utente non trovato.");
				request.getRequestDispatcher(Path.getPathInterfaccia() + "/login.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("userInfo", utenteInstance);
				request.setAttribute("show_annuncio_attr", MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio)));
			}
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, "Si è verificato un errore");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
		}

		request.getRequestDispatcher(Path.getPathInterfaccia() + "/show.jsp").forward(request, response);
	}

}
