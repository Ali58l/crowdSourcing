package service;

import java.util.List;

import model.dao.Auction;
import model.dao.Person;
import model.dao.Proposals;
import model.dao.Skills;
import model.dao.Tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	
@Service
public class SkillService {
	   
	  // An EntityManager will be automatically injected from EntityManagerFactory setup on
	  // spring-context.xml
	  @PersistenceContext
	  private EntityManager em;
	   
	  // Since we've setup <tx:annotation-config> and transaction manager on spring-context.xml,
	  // any bean method annotated with @Transactional will cause Spring to magically call
	  // begin() and commit() at the start/end of the method. If exception occurs it will also
	  // call rollback()
		  @Transactional
		public void addSkill(Skills skill) {
			
			em.persist(skill);
			
		}
	  
	  public Skills findSkillById(int skillid) {
			
			return em.find(Skills.class,skillid);
		}

	public List<Skills> getPotentialWorkersForOpenTask(Tasks task) {
		
		try 
		{
			String query = "Select s from Skills s where s.skill = :skill and s.isActive = :isActive and"
					+ " s.credibility >= :credibility and s.experience >= :experience and "
					+ "s.person NOT IN (select t.person.id from TaskWorker t where t.task.id = :taskId )";
        	Query skillsQuery = em.createQuery(query);
        	skillsQuery.setParameter("skill", task.getSkill());
        	skillsQuery.setParameter("isActive", true);
        	skillsQuery.setParameter("experience", task.getLevelSkill());
        	skillsQuery.setParameter("credibility", task.getCredibility());
        	skillsQuery.setParameter("taskId", task.getId());
        	       	
       
    		List<Skills> skillsList =  skillsQuery.getResultList();
    		
    		return skillsList;
    		
        }
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
        	return null;
        }
	}
}
