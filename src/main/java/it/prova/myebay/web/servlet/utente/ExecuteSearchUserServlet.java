package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Example;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/ExecuteSearchUserServlet")
public class ExecuteSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteSearchUserServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String dataCreazioneParam = request.getParameter("dateCreated");
		String[] ruoliParam = request.getParameterValues("ruoli");

		// creo un bean
		UtenteSearch example = UtilityForm.createUtenteSearchFromParams(nomeParam, cognomeParam, usernameParam, dataCreazioneParam, ruoliParam);
		
		try {
			request.setAttribute("users_list_attr", MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		Example.setUtenteExample(example);
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/list.jsp").forward(request, response);
	}

}
