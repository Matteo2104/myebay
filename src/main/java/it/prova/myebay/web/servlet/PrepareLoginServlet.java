package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.utility.Path;


@WebServlet("/PrepareLoginServlet")
public class PrepareLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PrepareLoginServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Path.pathInterfaccia + "/login.jsp").forward(request, response);
	}

	
	

}
