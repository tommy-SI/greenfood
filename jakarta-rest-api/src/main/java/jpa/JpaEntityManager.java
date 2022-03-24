package jpa;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.eclipse.persistence.config.PersistenceUnitProperties;


@WebListener
public class JpaEntityManager implements ServletContextListener {

	private static EntityManagerFactory emFactoryObj;
	private static EntityManager entityManager;
	private static final String PERSISTENCE_UNIT_NAME = "green_food"; // nom de la base de données
	private static final Map<String, String> jdbcProperties = new HashMap<String, String>();
	private static JpaEntityManager jpaEntityManager;
	 
	private JpaEntityManager() {

		jdbcProperties.put(PersistenceUnitProperties.TRANSACTION_TYPE,
				PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
		jdbcProperties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
		jdbcProperties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:5432/green_food");
		jdbcProperties.put(PersistenceUnitProperties.JDBC_USER, "postgres");
		//Renseignez ici le mot de passe de VOTRE serveur postgres que vous avez utilisé lors du :
		//docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -p 5432:5432 -d postgres
		jdbcProperties.put(PersistenceUnitProperties.JDBC_PASSWORD, "1234");

		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, jdbcProperties);

		entityManager = emFactoryObj.createEntityManager();
	}
	// DP singleton
	public static EntityManager getEntityManager() {
		if (jpaEntityManager == null) {
			jpaEntityManager = new JpaEntityManager();
		}
		return entityManager;
	}

	@PreDestroy
	public void destruct() {
		emFactoryObj.close();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		destruct();
	}

}
