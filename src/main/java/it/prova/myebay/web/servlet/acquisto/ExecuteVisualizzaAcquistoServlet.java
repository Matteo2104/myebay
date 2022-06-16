package it.prova.myebay.web.servlet.acquisto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Path;


@WebServlet("/acquisto/ExecuteVisualizzaAcquistoServlet")
public class ExecuteVisualizzaAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERRORMESSAGE = "errorMessage";
	private static final String ERRORJSP = "/error.jsp";
    
    public ExecuteVisualizzaAcquistoServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAcquisto = request.getParameter("idAcquisto");

		if (!NumberUtils.isCreatable(idAcquisto)) {
			request.setAttribute(ERRORMESSAGE, "Si è verificato un errore: id non è numerico");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
			return;
		}

		try {
			Acquisto acquistoInstance = MyServiceFactory.getAcquistoServiceInstance().caricaSingoloElemento(Long.parseLong(idAcquisto));

			if (acquistoInstance == null) {
				request.setAttribute(ERRORMESSAGE, "Elemento non trovato.");
				request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
				return;
			}

			request.setAttribute("show_acquisto_attr", acquistoInstance);
		} catch (Exception e) {
			request.setAttribute(ERRORMESSAGE, "Si è verificato un errore nella visualizzazione dettagli");
			request.getRequestDispatcher("/" + Path.getPathInterfaccia() + ERRORJSP).forward(request, response);
			return;
		}

		request.getRequestDispatcher("/" + Path.getPathInterfaccia() + "/acquisto/show.jsp").forward(request, response);
	}

}
