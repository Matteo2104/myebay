package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.service.MyServiceFactory;


@WebServlet("/ExecuteListAnnunciServlet")
public class ExecuteListAnnunciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteListAnnunciServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("annunci_list_attribute", MyServiceFactory.getAnnuncioServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Si è verificato un errore!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

}
