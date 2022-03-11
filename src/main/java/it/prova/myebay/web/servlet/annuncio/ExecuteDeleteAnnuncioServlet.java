package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;


import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/annuncio/ExecuteDeleteAnnuncioServlet")
public class ExecuteDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ExecuteDeleteAnnuncioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idAnnuncio)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getAnnuncioServiceInstance().rimuovi(Long.parseLong(idAnnuncio));
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		//System.out.println("elemento eliminato correttamente");

		//request.getRequestDispatcher("list.jsp").forward(request, response);
		response.sendRedirect("ExecuteListAnnunciServlet?operationResult=SUCCESS");
	}

}