package it.prova.myebay.web.servlet.credito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.utility.Path;


@WebServlet("/credito/PrepareRicaricaServlet")
public class PrepareRicaricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareRicaricaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/credito/ricarica.jsp").forward(request, response);
	}

	
	

}
