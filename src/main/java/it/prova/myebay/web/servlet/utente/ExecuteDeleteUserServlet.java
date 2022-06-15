package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/utente/ExecuteDeleteUserServlet")
public class ExecuteDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteDeleteUserServlet() {
        super();
    }

	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");

		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		

		try {
			// faccio tutto all'interno di un'unica transazione
			MyServiceFactory.getUtenteServiceInstance().disabilita(Long.parseLong(idUser));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUserServlet?operationResult=SUCCESS");
	}

}
