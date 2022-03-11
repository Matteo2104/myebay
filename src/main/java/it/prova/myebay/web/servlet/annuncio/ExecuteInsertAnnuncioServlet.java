package it.prova.myebay.web.servlet.annuncio;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.UtilityForm;


@WebServlet("/annuncio/ExecuteInsertAnnuncioServlet")
public class ExecuteInsertAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExecuteInsertAnnuncioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testoInput = request.getParameter("testo");
		String prezzoInput = request.getParameter("prezzo");
		String[] categorieIdInput = request.getParameterValues("categorie");
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		
		// creo un bean
		Annuncio example = UtilityForm.createAnnuncioFromParams(testoInput, prezzoInput, categorieIdInput);
		example.setData(new Date());
		
		if (!UtilityForm.validateAnnuncioBean(example)) {
			request.setAttribute("insert_utente_attr", example);
			//request.setAttribute("list_utente_role_attr", MyServiceFactory.getRuoloServiceInstance().listAll());
			//request.setAttribute("list_utente_rolechecked_attr", Arrays.asList(ruoliParam));
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}
		
		//System.out.println(example);
		// valorizzo il campo utente
		Utente utenteInSessione = (Utente)httpRequest.getSession().getAttribute("userInfo");
		example.setUtenteInserimento(utenteInSessione);

		
		try {
			MyServiceFactory.getAnnuncioServiceInstance().aggiungi(example);
			
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore nella ricerca!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
		//response.getWriter().append("fin qui tutto ok");
	}

}
