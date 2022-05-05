package it.prova.myebay.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.utility.Path;

@WebServlet("/SwitchInterface")
public class SwitchInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SwitchInterface() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path.switchPathInterfaccia();
		request.getRequestDispatcher(Path.PATH_INTERFACCIA + "/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
