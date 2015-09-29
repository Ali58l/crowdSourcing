package service;

import java.util.List;

import model.dao.Auction;
import model.dao.Person;
import model.dao.Proposals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	    em.persist(p);
	  }
	  
	  @Transactional
	  public Person findPerson(int id) {
	    Person person = em.find(Person.class,id);
	    
	    return person;
	  }
	@Transactional
	public Person findPersonByUsernameAndPassword(String username,String password) {

		Person person = null;
		try{
		person = new Person();
        try {
        	Query personQuery = em.createQuery("Select p from Person p where p.username = :username and"
    				+ " p.password = :password and p.isActive = :isActive");
        	personQuery.setParameter("username", username);
        	personQuery.setParameter("password", password);
        	personQuery.setParameter("isActive", true);
        	
        	
    		person = (Person) personQuery.getResultList().get(0);
        } finally {
        }
		
		return person;
				
		}
		catch(Exception ex){
			System.out.println(ex.getMessage()+"hi");
			
			return person;
		}
		
	}

	@Transactional
	public boolean checkUsernameAvailable(String username) {
		Query personQuery = em.createQuery("Select p from Person p where p.username = :username and"
				+ " p.isActive = :isActive");
    	personQuery.setParameter("username", username);
    	personQuery.setParameter("isActive", true);
    
    	if ( personQuery.getResultList().size() >0 ){
    		return true;
    	}else{
    		return false;
    	}
	}

	@Transactional
	public boolean checkUserProposals(Person person) {
		Query proposalsQuery = em.createQuery("Select p from Proposals p where p.person.id =:personId"
				+ " and p.isActive =:isActive");
    	proposalsQuery.setParameter("personId", person.getId());
    	proposalsQuery.setParameter("isActive", true);
    	       	
   
		List<Proposals> proposalsList =  proposalsQuery.getResultList();
		if ( proposalsList.size() > 0 ){
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public boolean checkUserAuction(Person person) {
		  Query selectMaxAuctionQuery = em.createQuery("Select a from Auction a where"
		  		+ " a.isActive =:isActive and a.personPriceProposed.id =:personId");
			  
			  selectMaxAuctionQuery.setParameter("isActive", true);
			  selectMaxAuctionQuery.setParameter("personId", person.getId());	
			  List<Auction> auctionList = selectMaxAuctionQuery.getResultList();
			  int i = 0;
			  int countActiveProposalIncludeUser=0;
			  if ( auctionList.size() > 0 ){
				  while ( i < auctionList.size() ){
					  if (auctionList.get(i).getProposals().getisActive() == true )
					  {
						  countActiveProposalIncludeUser = 1;
								  break;
					  }
					  i++;
				  }
			  }
			  
			  if ( countActiveProposalIncludeUser > 0 ){
				return true;  
			  }else{
				  return false;
			  }
	}
	
	@Transactional
	public void deleteUser(Person person) {
		
		em.remove(em.merge(person));
		
	}
}
