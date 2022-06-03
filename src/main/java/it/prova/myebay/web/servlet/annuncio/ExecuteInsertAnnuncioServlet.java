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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titoloInput = request.getParameter("titolo");
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String idAnnuncio = request.getParameter("idAnnuncio");

		
		// creo un bean
		Annuncio example = UtilityForm.createAnnuncioFromParams(titoloInput, testoInput, prezzoInput, categorieIdInput);
		
		try {
		
			// se la validazione non risulta ok
			if (!UtilityForm.validateAnnuncioBean(example)) {
				request.setAttribute("insert_annuncio_attr", example);
				
				List<Categoria> listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
				Map<Categoria, Boolean> mappa = new HashMap<>();
				
				
				boolean check = false;
				for (Categoria categoria : listaCategorie) {
					// se sono state selezionate categorie valorizzo la mappa, altrimenti la riempi con tutti valori settati a false
					if (categorieIdInput != null) {
						check = false;
						for (int i=0;i<categorieIdInput.length;i++) {
							if (categoria.getId().toString().equals(categorieIdInput[i])) {
								mappa.put(categoria, true);
								check=true;
							}
						}
						if (!check) {
							mappa.put(categoria, false);
						}
						
						
					} else {
						mappa.put(categoria, false);
					}
					
				}
				request.setAttribute("mappa_categorie", mappa);
				
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/annuncio/insert.jsp").forward(request, response);
				return;
			}
			
		
			// valorizzo il campo utente
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			example.setUtenteInserimento(utenteInSessione);

		
			System.out.println(example);
			MyServiceFactory.getAnnuncioServiceInstance().aggiungi(example);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore nell'inserimento");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/annuncio/ExecuteListAnnunciPersonaliServlet?operationResult=SUCCESS");
		//response.getWriter().append("fin qui tutto ok");
	}

}
