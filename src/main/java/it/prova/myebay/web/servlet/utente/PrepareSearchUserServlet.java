package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.model.Utente;
import it.prova.myebay.utility.Path;


@WebServlet("/utente/PrepareSearchUserServlet")
public class PrepareSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PrepareSearchUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("search_utente_attr", new Utente());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/utente/search.jsp").forward(request, response);
	}

}
