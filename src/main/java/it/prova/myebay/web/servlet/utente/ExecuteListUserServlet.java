package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/utente/ExecuteListUserServlet")
public class ExecuteListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteListUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("users_list_attr", MyServiceFactory.getUtenteServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.pathInterfaccia + "/utente/list.jsp").forward(request, response);
	}

}
