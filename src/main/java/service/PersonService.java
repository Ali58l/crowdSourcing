package service;

import java.util.List;
import model.dao.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
	
@Service
public class PersonService {
	   
	  // An EntityManager will be automatically injected from EntityManagerFactory setup on
	  // spring-context.xml
	  @PersistenceContext
	  private EntityManager em;
	   
	  // Since we've setup <tx:annotation-config> and transaction manager on spring-context.xml,
	  // any bean method annotated with @Transactional will cause Spring to magically call
	  // begin() and commit() at the start/end of the method. If exception occurs it will also
	  // call rollback()
	  @Transactional
	  public List<Person> getAll() {
	    List<Person> result = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
	    return result;
	  }
	   
	  @Transactional
	  public void add(Person p) {
		  System.out.println(p.getName());
	    em.persist(p);
	  }
}
