package it.prova.myebay.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/utente/ExecuteVisualizzaUserServlet")
public class ExecuteVisualizzaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteVisualizzaUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilityForm.showUtenteOperation(request.getParameter("idUser"), "visualizza_utente_attr", request, response);
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/show.jsp").forward(request, response);
	}

	

}
