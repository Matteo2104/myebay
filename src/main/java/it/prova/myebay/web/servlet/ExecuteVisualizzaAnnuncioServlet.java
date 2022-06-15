package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/ExecuteVisualizzaAnnuncioServlet")
public class ExecuteVisualizzaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String errorMessage = "errorMessage";
	private static final String errorJsp = "/error.jsp";
       
   
    public ExecuteVisualizzaAnnuncioServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAnnuncio = request.getParameter("idAnnuncio");

		if (!NumberUtils.isCreatable(idAnnuncio)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute(errorMessage, "Attenzione si è verificato un errore: id non è numerico");
			request.getRequestDispatcher(Path.pathInterfaccia + "/error.jsp").forward(request, response);
			return;
		}

		try {
			Annuncio annuncioInstance = MyServiceFactory.getAnnuncioServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idAnnuncio));

			if (annuncioInstance == null) {
				request.setAttribute(errorMessage, "Elemento non trovato.");
				request.getRequestDispatcher(Path.pathInterfaccia + errorJsp).forward(request, response);
				return;
			}

			request.setAttribute("show_annuncio_attr", annuncioInstance);
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute(errorMessage, "Si è verificato un errore nella visualizzazione dettagli");
			request.getRequestDispatcher(Path.pathInterfaccia + errorJsp).forward(request, response);
			return;
		}

		request.getRequestDispatcher(Path.pathInterfaccia + "/show.jsp").forward(request, response);
	}

}
