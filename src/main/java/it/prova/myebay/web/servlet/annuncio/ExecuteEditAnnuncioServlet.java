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
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/annuncio/ExecuteEditAnnuncioServlet")
public class ExecuteEditAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

   
    public ExecuteEditAnnuncioServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");

		
		
		if (!NumberUtils.isCreatable(idAnnuncio)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		
		// estraggo input
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieInput = request.getParameterValues("categorie");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		

		
		
		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Annuncio annuncio = UtilityForm.createAnnuncioFromParams(testoInput, prezzoInput, categorieInput);

		
		// se la validazione non risulta ok
		if (!UtilityForm.validateAnnuncioBean(annuncio)) {
			request.setAttribute("edit_annuncio_attr", annuncio);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			return;
		}
		
		try {
		
			// assegno l'id, la data e l'apertura a true, e anche l'utente in sessione
			Annuncio annuncioOriginale = MyServiceFactory.getAnnuncioServiceInstance().caricaSingoloElementoEager(Long.parseLong(idAnnuncio));
			annuncio.setId(Long.parseLong(idAnnuncio));
			annuncio.setData(annuncioOriginale.getData());
			
			Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
			annuncio.setUtenteInserimento(utenteInSessione);
			
			MyServiceFactory.getAnnuncioServiceInstance().aggiorna(annuncio);
			/*
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().listAll(Long.parseLong(idAnnuncio)));
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			*/
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/annuncio/ExecuteListAnnunciPersonaliServlet?operationResult=SUCCESS");

		//request.getRequestDispatcher("/annuncio/list.jsp").forward(request, response);
	}

}
