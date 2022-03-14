package it.prova.myebay.web.servlet.utente;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/utente/PrepareInsertUserServlet")
public class PrepareInsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PrepareInsertUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("insert_utente_attr", new Utente());

			List<Ruolo> listaRuoli = MyServiceFactory.getRuoloServiceInstance().listAll();
			Map<Ruolo, Boolean> mappa = new HashMap<>();
			
			// riempio la mappa con valori tutti settati a false di default
			for (Ruolo ruolo : listaRuoli) {
				mappa.put(ruolo, false);
			}
				
			request.setAttribute("mappa_ruoli", mappa);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Si è verificato un errore.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
	}


}
