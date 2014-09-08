package id.co.sigma.common.server.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public abstract class CustomLocalContainerEntityManagerFactoryBean extends LocalContainerEntityManagerFactoryBean {

	@Override
	protected void postProcessEntityManagerFactory(EntityManagerFactory emf,
			PersistenceUnitInfo pui) {
		super.postProcessEntityManagerFactory(emf, pui);
		
		
	}
	
	@Override
	protected EntityManagerFactory createEntityManagerFactoryProxy(
			EntityManagerFactory emf) {
		
		return super.createEntityManagerFactoryProxy(emf);
		
	}
}
