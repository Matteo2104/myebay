package it.prova.myebay.web.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {
	// prima era static
	private static EntityManagerFactory entityManagerFactory;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
	private static void setEntityManagerFactory(EntityManagerFactory emf) {
		entityManagerFactory = emf;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			setEntityManagerFactory(Persistence.createEntityManagerFactory("myebay_unit"));
			
		} catch (Exception ex) {
			
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
				// do nothing
			} catch (Exception ex) {
				// do nothing
			}
		}
	}
}
