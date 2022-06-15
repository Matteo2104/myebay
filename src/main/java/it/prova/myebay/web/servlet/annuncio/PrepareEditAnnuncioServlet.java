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

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.exception.AnnuncioChiusoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/annuncio/PrepareEditAnnuncioServlet")
public class PrepareEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
    public PrepareEditAnnuncioServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");
		
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		try {
			Annuncio annuncio = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio));
			
			if (!annuncio.isAperto()) {
				throw new AnnuncioChiusoException("Annuncio chiuso");
			}
			
			request.setAttribute("edit_annuncio_attr", annuncio);
			
			List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
			Map<Categoria, Boolean> mappa = new HashMap<>();
			
			boolean check = false;
			for (Categoria categoria : listaCategorie) {
				check = false;
				for (Categoria categoriaAnnuncio : annuncio.getCategorie()) {
					if (categoria.getId().equals(categoriaAnnuncio.getId())) {
						mappa.put(categoria, true);
						check=true;
					}
				}
				if (!check) {
					mappa.put(categoria, false);
				}
			}
			request.setAttribute("mappa_categorie", mappa);
			
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.pathInterfaccia + "/annuncio/edit.jsp").forward(request, response);
	}

	

}
