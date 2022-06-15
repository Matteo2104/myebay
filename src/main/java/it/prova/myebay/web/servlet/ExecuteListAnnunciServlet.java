package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.utility.Example;
import it.prova.myebay.utility.Path;


@WebServlet("/ExecuteListAnnunciServlet")
public class ExecuteListAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteListAnnunciServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if (Example.annuncioExample != null) {
				request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().findByExample(Example.annuncioExample));
			} else {
				request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().getAnnunciAttivi());
			}
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore!");
			request.getRequestDispatcher(Path.pathInterfaccia + "/error.jsp").forward(request, response);
		}
		
		
		request.getRequestDispatcher(Path.pathInterfaccia + "/list.jsp").forward(request, response);
	}

}
