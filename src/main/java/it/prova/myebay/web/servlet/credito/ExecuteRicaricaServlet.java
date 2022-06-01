package it.prova.myebay.web.servlet.credito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/credito/ExecuteRicaricaServlet")
public class ExecuteRicaricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteRicaricaServlet() {
        super();
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String credito = request.getParameter("credito");
		
		try {
			if (credito.isEmpty())
				throw new Exception("Si è verificato un errore");
			
			// controllo se l'utente è in sessione
			Utente utenteInSession = (Utente) request.getSession().getAttribute("userInfo");			
			if (utenteInSession == null) {
				response.sendRedirect(request.getContextPath() + "?errorMessage=ERROR");
				return;
			}
			
			// se sono qui va tutto ok - procedo con l'operazione
			MyServiceFactory.getUtenteServiceInstance().ricarica(utenteInSession.getId(), Integer.parseInt(credito));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/areapersonale.jsp").forward(request, response);
		//response.sendRedirect("ExecuteAreaPersonaleServlet?operationResult=SUCCESS");
	}

}
