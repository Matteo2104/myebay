package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/annuncio/PrepareDeleteAnnuncioServlet")
public class PrepareDeleteAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PrepareDeleteAnnuncioServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		try {
			Annuncio annuncio = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio));
			
			if (!annuncio.isAperto()) {
				throw new AnnuncioChiusoException("Annuncio chiuso");
			}
			
			request.setAttribute("delete_annuncio_attr", annuncio);
			
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: " + e);
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/annuncio/delete.jsp").forward(request, response);
	}

	
	
}
