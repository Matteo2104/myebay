package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.prova.myebay.model.Utente;
import it.prova.myebay.utility.Path;


@WebServlet("/PrepareRegisterUserServlet")
public class PrepareRegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareRegisterUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			request.setAttribute("registra_utente_attr", new Utente());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore.");
			request.getRequestDispatcher(Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher(Path.getPathInterfaccia() + "/register.jsp").forward(request, response);
	}

	

}
