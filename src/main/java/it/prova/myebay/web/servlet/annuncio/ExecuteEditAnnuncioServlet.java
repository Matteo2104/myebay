package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/annuncio/ExecuteEditAnnuncioServlet")
public class ExecuteEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

   
    public ExecuteEditAnnuncioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");

		
		// se non ricevo correttamente l'id vado in errore 
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}

		
		// estraggo input
		String titoloInput = request.getParameter("titolo");
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieInput = request.getParameterValues("categorie");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Annuncio annuncio = UtilityForm.createAnnuncioFromParams(titoloInput, testoInput, prezzoInput, categorieInput);

		
		// se la validazione non risulta ok
		if (!UtilityForm.validateAnnuncioBean(annuncio)) {
			request.setAttribute("edit_annuncio_attr", annuncio);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/annuncio/edit.jsp").forward(request, response);
			return;
		}
		
		try {
			// carico l'annuncio originale
			Annuncio annuncioOriginale = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio));
			
			// assegno l'id, la data e l'apertura a true, e anche l'utente in sessione
			annuncio.setId(Long.parseLong(idAnnuncio));
			annuncio.setData(annuncioOriginale.getData());
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			
			if (utenteInSessione.getId() == annuncioOriginale.getUtenteInserimento().getId())
				annuncio.setUtenteInserimento(utenteInSessione); 
			
			
			// aggiorno
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncio);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/annuncio/ExecuteListAnnunciPersonaliServlet?operationResult=SUCCESS");
	}

}
