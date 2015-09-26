package service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import model.dao.Auction;
import model.dao.Person;
import model.dao.Proposals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
	
@Service
public class BidService {
	   
	  // An EntityManager will be automatically injected from EntityManagerFactory setup on
	  // spring-context.xml
	  @PersistenceContext
	  private EntityManager em;
	   
	  // Since we've setup <tx:annotation-config> and transaction manager on spring-context.xml,
	  // any bean method annotated with @Transactional will cause Spring to magically call
	  // begin() and commit() at the start/end of the method. If exception occurs it will also
	  // call rollback()
//	  @Transactional
//	  public List<Proposals> getAll() {
//	    List<Person> result = em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
//	    return result;
//	  }
	   
	  @Transactional
	  public List<Proposals> getMyBid(Person person) {
	  try {
        	Query proposalsQuery = em.createQuery("Select p from Proposals p where p.person.id = :"
        			+ "personId");
        	proposalsQuery.setParameter("personId", person.getId());
        	       	
       
    		List<Proposals> proposalsList =  proposalsQuery.getResultList();
    		
    		return proposalsList;
        }catch (Exception exception){
        	return null;
        }
        	
	  }
	  
	  @Transactional
	  public void add(Proposals p) {
	    em.persist(p);
	  }

	  @Transactional
	  public void checkProposalsStatus() {
		  Query activeProposalsQuery = em.createQuery("Select p from Proposals p where p.isActive =:"
	    			+ "isActive");
	    	activeProposalsQuery.setParameter("isActive", true);
	    	List<Proposals> activeProposalsList =  activeProposalsQuery.getResultList();
	    	int i = 0;
	    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	    	while ( i < activeProposalsList.size() ){
	    		
	    		// replace the commanded line after finishing project!
	    		Timestamp updatedTime = activeProposalsList.get(i).getUpdateDate();
	    		//if ( (currentTime.getTime() - updatedTime.getTime()) > 5*60*1000
	    		if ( (currentTime.getTime() - updatedTime.getTime()) > 50*600*10000
	    				&& activeProposalsList.get(i).getCountAuctions() >0){
	    			activeProposalsList.get(i).setActive(false);
	    		}
	    		//else if	(currentTime.getTime() - updatedTime.getTime() > 5*60*1000 
	    		else if	(currentTime.getTime() - updatedTime.getTime() > 50*600*10000
    				&& activeProposalsList.get(i).getCountAuctions() ==0 ){
    			activeProposalsList.get(i).setUpdateDate(currentTime);
	    		}
	    		em.persist(activeProposalsList.get(i));
	    		i++;
	    	}
	    	
			
		}
	  
	  @Transactional
	  public List<Proposals> getActiveBid(Person person) {	
		Query activeProposalsQuery = em.createQuery("Select p from Proposals p where p.isActive =:"
    			+ "isActive and p.person.id !=:personId");
    
    	activeProposalsQuery.setParameter("isActive", true);
    	activeProposalsQuery.setParameter("personId", person.getId());
    	       	
   
		List<Proposals> activeProposalsList =  activeProposalsQuery.getResultList();
		
		return activeProposalsList;
	}

	 @Transactional
	public Auction getMaxProposedPrice(int propid) {
		  Query selectMaxAuctionQuery = em.createQuery("Select a from Auction a where "
		  		+ " a.proposals.id =:propid ORDER BY a.proposedPrice desc");
		  
		  //selectMaxAuctionQuery.setParameter("isActive", true);
		  selectMaxAuctionQuery.setParameter("propid", propid);	
		  selectMaxAuctionQuery.setMaxResults(1);
		  Auction prop = (Auction) selectMaxAuctionQuery.getResultList().get(0);
		return prop;
	}
}
