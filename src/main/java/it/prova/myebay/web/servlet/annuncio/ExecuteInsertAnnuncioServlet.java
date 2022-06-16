package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/annuncio/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteInsertAnnuncioServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = request;
		
		// estraggo gli input
		String titoloInput = request.getParameter("titolo");
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");

		
		// creo un bean
		Annuncio example = UtilityForm.createAnnuncioFromParams(titoloInput, testoInput, prezzoInput, categorieIdInput);
		
		try {
		
			// se la validazione non risulta ok preparo una mappa
			if (!UtilityForm.validateAnnuncioBean(example)) {
				
				List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
				Map<Categoria, Boolean> mappa = new HashMap<>();
				List<String> listaIdSelezionati = new ArrayList<>();
				
				if (categorieIdInput != null && categorieIdInput.length > 0) {
					listaIdSelezionati = Arrays.asList(categorieIdInput);
				}
								
				mappa = UtilityForm.creaMappa(listaIdSelezionati, listaCategorie);

				request.setAttribute("mappa_categorie", mappa);
				request.setAttribute("insert_annuncio_attr", example);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/annuncio/insert.jsp").forward(request, response);
				return;
			}
			
		
			// valorizzo il campo utente
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			example.setUtenteInserimento(utenteInSessione);

		
			MyServiceFactory.getAnnuncioServiceInstance().aggiungi(example);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore nell'inserimento");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/annuncio/ExecuteListAnnunciPersonaliServlet?operationResult=SUCCESS");
	}

}
