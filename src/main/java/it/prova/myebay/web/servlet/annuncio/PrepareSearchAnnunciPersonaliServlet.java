package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/annuncio/PrepareSearchAnnunciPersonaliServlet")
public class PrepareSearchAnnunciPersonaliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public PrepareSearchAnnunciPersonaliServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("list_categorie_attr", MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Errore nell'esecuzione della ricerca");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	

}
