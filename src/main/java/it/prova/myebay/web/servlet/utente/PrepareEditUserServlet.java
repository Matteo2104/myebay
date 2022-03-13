package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/utente/PrepareEditUserServlet")
public class PrepareEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrepareEditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		
		if (!NumberUtils.isCreatable(idUser)) {
			request.setAttribute("errorMessage", "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		try {
			// carico un utente eager
			Utente utente = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElementoEager(Long.parseLong(idUser));
			
			// carico su request l'utente
			request.setAttribute("edit_utente_attr", utente);
			
			List<Ruolo> listaRuoli = MyServiceFactory.getRuoloServiceInstance().listAll();
			Map<Ruolo, Boolean> mappa = new HashMap<>();
			
			boolean check = false;
			for (Ruolo ruolo : listaRuoli) {
				check = false;
				for (Ruolo ruoloUtente : utente.getRuoli()) {
					if (ruolo.getId() == ruoloUtente.getId()) {
						mappa.put(ruolo, true);
						check=true;
					}
				}
				if (!check) {
					mappa.put(ruolo, false);
				}
			}
			request.setAttribute("mappa_ruoli", mappa);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
	}

	

}
