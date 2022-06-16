package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/acquisto/ExecuteListAcquistiServlet")
public class ExecuteListAcquistiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteListAcquistiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = request;

		Utente utenteInSessione = (Utente) httpRequest.getSession().getAttribute("userInfo");
		
		try {
			request.setAttribute("list_acquisti_attr", MyServiceFactory.getAcquistoServiceInstance().listAll(utenteInSessione.getId()));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		} 
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/acquisto/list.jsp").forward(request, response);
	}
    
    

}
