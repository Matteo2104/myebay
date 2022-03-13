package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;


@WebServlet("")
public class PrepareSearchAnnunciSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public PrepareSearchAnnunciSerlvet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("list_categorie_attr", MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Errore nell'esecuzione della ricerca");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		// la funzione di ricerca si trova nella home-page
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
