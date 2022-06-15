package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/annuncio/PrepareInsertAnnuncioServlet")
public class PrepareInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareInsertAnnuncioServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("insert_annuncio_attr", new Annuncio());
			
			List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
			Map<Categoria, Boolean> mappa = new HashMap<>();
			for (Categoria categoria : listaCategorie) {
				mappa.put(categoria, false);
			}
			request.setAttribute("mappa_categorie", mappa);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/annuncio/insert.jsp").forward(request, response);
	}



}
