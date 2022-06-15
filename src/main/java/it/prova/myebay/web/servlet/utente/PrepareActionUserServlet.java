package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/utente/PrepareActionUserServlet")
public class PrepareActionUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
    
    public PrepareActionUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		String path = "";
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute(ERRORMESSAGE, "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUser));

			// da testare
			if (utenteInstance == null) {
				request.setAttribute(ERRORMESSAGE, "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListUserServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}
			
			// se l'utente è disabilitato lo abilito 
			if (utenteInstance.getStato() == StatoUtente.DISABILITATO)
				path += "/" + Path.getPathInterfaccia() + "/utente/abilita.jsp";
				
			// se l'utente è già abilitato lo disabilito
			if (utenteInstance.getStato() != StatoUtente.DISABILITATO)
				path += "/" + Path.getPathInterfaccia() + "/utente/delete.jsp";
			
			request.setAttribute("delete_utente_attr", utenteInstance);

			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ERRORMESSAGE, "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/error.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	

}
