package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.utility.Path;

@WebServlet("/PrepareLoginByAnnuncioServlet")
public class PrepareLoginByAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PrepareLoginByAnnuncioServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// propago l'id
		String idAnnuncio = request.getParameter("idAnnuncio");
		if (idAnnuncio==null || !NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Errore: id non è numerico: pagina prepare");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("idAnnuncio", idAnnuncio);
		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/login.jsp").forward(request, response);
	}

}
