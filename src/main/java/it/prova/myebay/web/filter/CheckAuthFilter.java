package it.prova.myebay.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.myebay.model.Utente;
import it.prova.myebay.utility.Interfaccia;
import it.prova.myebay.utility.Path;


@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	//private static final String HOME_PATH = "";
	//private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/"};
	private static final String[] PROTECTED_URLS = {"/utente/", "/acquisto/", "/annuncio/", "/credito"};
	private static final String[] ADMIN_URLS = {"/utente/"};

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();
		
		//vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		//se il tipo di interfaccia è 0 significa che è il primo start dell'app
		//allora la imposto a 1 di default;
		Path.initPathInterfaccia();
		
		//se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			
			// se sono qui dentro significa che ho interrotto la navigazione per accedere e quindi mi salvo il path e l'id
			Path.PATH_RITORNO = request.getParameter("pathRitorno");
			Path.ID = request.getParameter("idAnnuncio");
			
			Utente utenteInSession = (Utente)httpRequest.getSession().getAttribute("userInfo");
			// verifico se utente è in sessione
			if (utenteInSession == null) {
				//System.out.println("utente non in sessione: eseguo redirect");
				//httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);	
				httpResponse.sendRedirect(httpRequest.getContextPath() + "?errorMessage=ERROR");
				return;
			}
			
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("errorMessage", "Non si è autorizzati a proseguire in questa pagina");
				httpRequest.getRequestDispatcher(Path.PATH_INTERFACCIA + "/error.jsp").forward(httpRequest, httpResponse);	
				return;
			}
			
			/*
			if (!utenteInSession.isLoggedIn()) {
				System.out.println("utente in sessione ma non loggato: mando a login");
				httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
				return;
			}
			*/
			
			/*
			//controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if(isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpRequest.setAttribute("errorMessage", "Non si è autorizzati alla navigazione richiesta");
				httpRequest.getRequestDispatcher("/index.jsp").forward(httpRequest, httpResponse);
				return;
			}
			*/
			
			
			
		}
		
		chain.doFilter(request, response);
		
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
