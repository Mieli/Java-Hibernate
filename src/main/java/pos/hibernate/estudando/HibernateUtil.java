package pos.hibernate.estudando;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		init();
	}
	
	private static void init() {
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("projeto");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//prove a persistência 
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	//captura a chave primaria 
	public static Object getPrimaryKey(Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}
	
	
}
