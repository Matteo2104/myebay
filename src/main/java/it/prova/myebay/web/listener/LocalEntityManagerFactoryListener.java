package it.prova.myebay.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.CategoriaService;
import it.prova.myebay.service.MyServiceFactory;
import it.prova.myebay.service.UtenteService;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {
	
	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("myebay_unit");
			// questa chiamata viene fatta qui per semplicità ma in genere è meglio trovare
			// altri modi per fare init
			//initAdminUserAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}
	
	private void initAdminUserAndRuoli() throws Exception {
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		
		// utente ADMIN
		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			admin.setRuolo(Ruolo.ROLE_ADMIN);
			utenteServiceInstance.inserisciNuovo(admin);	
		}
		
		// utente CLASSIC_USER
		if (utenteServiceInstance.findByUsernameAndPassword("LesPaul2", "1234") == null) {
			Utente classic = new Utente("LesPaul2", "1234", "Matteo", "Scarcella", new Date());
			classic.setStato(StatoUtente.ATTIVO);
			classic.setRuolo(Ruolo.ROLE_CLASSIC_USER);
			classic.setCreditoResiduo(300);
			utenteServiceInstance.inserisciNuovo(classic);	
		}
		
		
		// categoria VESTITI
		if (categoriaServiceInstance.findByCodice("VESTITI") == null) {
			Categoria vestiti = new Categoria("Vestiti", "VESTITI");
			categoriaServiceInstance.inserisciNuovo(vestiti);	
		}
		
		// categoria TECH
		if (categoriaServiceInstance.findByCodice("TECH") == null) {
			Categoria tech = new Categoria("Tech", "TECH");
			categoriaServiceInstance.inserisciNuovo(tech);
		}
		
		// categoria PER LA CASA
		if (categoriaServiceInstance.findByCodice("PER LA CASA") == null) {
			Categoria perLaCasa = new Categoria("Per la casa", "PER LA CASA");
			categoriaServiceInstance.inserisciNuovo(perLaCasa);
		}
		
		
	}
}
