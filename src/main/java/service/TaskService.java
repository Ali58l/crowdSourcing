package service;

import java.util.ArrayList;
import java.util.List;

import model.dao.Auction;
import model.dao.Person;
import model.dao.Proposals;
import model.dao.Skills;
import model.dao.TaskWorker;
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
public class TaskService {
	   
	  // An EntityManager will be automatically injected from EntityManagerFactory setup on
	  // spring-context.xml
	  @PersistenceContext
	  private EntityManager em;
	   
	  // Since we've setup <tx:annotation-config> and transaction manager on spring-context.xml,
	  // any bean method annotated with @Transactional will cause Spring to magically call
	  // begin() and commit() at the start/end of the method. If exception occurs it will also
	  // call rollback()
	  @Transactional
	public void addTask(Tasks task) {
		
		em.persist(task);
		
	}

	public List<Tasks> getUserActiveTask(int id) {
		
		try 
		{
        	Query tasksQuery = em.createQuery("Select t from Tasks t where t.person.id = :personId and t.isActive = :isActive");
        	tasksQuery.setParameter("personId", id);
        	tasksQuery.setParameter("isActive", true);
        	       	
       
    		List<Tasks> tasksList =  tasksQuery.getResultList();
    		
    		return tasksList;
    		
        }
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
        	return null;
        }
	}

	public Tasks getTaskById(int taskid) {
		// TODO Auto-generated method stub
		return em.find(Tasks.class, taskid);
	}

	@Transactional
	public void addNewTaskWorker(TaskWorker tw) {
		em.persist(tw);
		
	}

	public List<TaskWorker> showUserTasksForDecision(int id) {
		
		Query tasksQuery = em.createQuery("Select t from TaskWorker t where t.person.id = :personId "
				+ "and t.isActive = :isActive and t.allocationStatus = :status");
    	tasksQuery.setParameter("personId", id);
    	tasksQuery.setParameter("isActive", true);
    	tasksQuery.setParameter("status", 0);
   
    	List<TaskWorker> tasksList =  tasksQuery.getResultList();
		
		return tasksList;
		
	}

	public TaskWorker getTaskWorkerById(int taskworkerid) {
		
		return em.find(TaskWorker.class, taskworkerid);
		
	}
	
	@Transactional
	public void accepttaskWork(TaskWorker taskworker) {
		try
		{
			taskworker.setAllocationStatus(1);
			em.merge(taskworker);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	@Transactional
	public void rejecttaskWork(TaskWorker taskworker) {
		try
		{
			taskworker.setAllocationStatus(-1);
			em.merge(taskworker);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public long getNumberOfAcceptedWorkers(Tasks task) {
		
		Query tasksQuery = em.createQuery("Select count(t) from TaskWorker t where t.isActive = :isActive"
				+ " and t.allocationStatus = :status and t.task.id = :taskid");
    	tasksQuery.setParameter("isActive", true);
    	tasksQuery.setParameter("status",1 );
    	tasksQuery.setParameter("taskid",task.getId() );
    	
    	long nemuberOfAcceptedWorker = 0;
    	
    	try
    	{
    		nemuberOfAcceptedWorker =  (Long) tasksQuery.getSingleResult();
    		
    	}
		catch( Exception ex)
    	{
			System.out.println(ex.getMessage());
			
    	}
    	
    	return nemuberOfAcceptedWorker;
    	
	}

	@Transactional
	public List<TaskWorker> showUserActiveTasks(int taskid) {
		
		List<TaskWorker> tasksList = new ArrayList<TaskWorker>();
	try
	{
		Query tasksQuery = em.createQuery("Select t from TaskWorker t where t.task.id = :taskid "
				+ "and t.isActive = :isActive and t.allocationStatus = :status");
    	tasksQuery.setParameter("taskid", taskid);
    	tasksQuery.setParameter("isActive", true);
    	tasksQuery.setParameter("status", 1);
	   tasksList =  tasksQuery.getResultList();   
   }
   catch( Exception ex)
   {
	   System.out.println(ex.getMessage());
   }
    
		return tasksList;	
	}
}
