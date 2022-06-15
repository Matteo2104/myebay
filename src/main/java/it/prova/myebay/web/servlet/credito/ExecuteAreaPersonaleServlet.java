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

@WebServlet("/credito/ExecuteAreaPersonaleServlet")
public class ExecuteAreaPersonaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteAreaPersonaleServlet() {
        super();
    }
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("operationResult", "Operazione effettuata con successo");
		Utente utenteInSessione = (Utente) request.getSession().getAttribute("userInfo");
		
		try {
			request.getSession().setAttribute("userInfo", MyServiceFactory.getUtenteServiceInstance().findById(utenteInSessione.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/areapersonale.jsp").forward(request, response);
	}
}
