package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/utente/PrepareEditUserServlet")
public class PrepareEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareEditUserServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		try {
			// carico un utente
			Utente utente = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(idUser));
						
			// carico su request l'utente
			request.setAttribute("edit_utente_attr", utente);
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("/" + Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/" + Path.pathInterfaccia + "/utente/edit.jsp").forward(request, response);
	}

	

}
