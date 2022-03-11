package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/acquisto/ExecuteListAcquistiServlet")
public class ExecuteListAcquistiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteListAcquistiServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
		try {
			request.setAttribute("list_acquisti_attr", MyServiceFactory.getAcquistoServiceInstance().listAll(utenteInSessione.getId()));
		} catch (Exception e) {
			
		} 
		
		request.getRequestDispatcher("/acquisto/list.jsp").forward(request, response);
	}
    
    

}
