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


@WebServlet("/utente/PrepareDeleteUserServlet")
public class PrepareDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUser));

			// da testare
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListUserServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			if (utenteInstance.getStato() == StatoUtente.DISABILITATO) {
				request.setAttribute("errorMessage", "Utente già disabilitato");
				request.getRequestDispatcher("/utente/ExecuteListUserServlet").forward(request, response);
				return;
			}

			request.setAttribute("delete_utente_attr", utenteInstance);
			
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/error.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/" + Path.PATH_INTERFACCIA + "/utente/delete.jsp").forward(request, response);
	}

	

}
