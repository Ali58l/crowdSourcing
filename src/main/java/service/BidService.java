package service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	  public List<Proposals> getBidUserParticipate(Person person) {
			// TODO Auto-generated method stub
		  try {
	        	Query proposalsQuery = em.createQuery("Select p from Proposals p where p.id in "
	        			+ "(Select a.proposals.id from Auction a where a.personPriceProposed.id = :personId)");
	        	proposalsQuery.setParameter("personId", person.getId());
	        	       	
	       
	    		List<Proposals> proposalsList =  proposalsQuery.getResultList();
	    		
	    		return proposalsList;
	        }catch (Exception exception){
	        	System.out.println(exception.getMessage());
	        	return null;
	        }
		}
	  
	  
	// return final proposal which user has participated!
	  @Transactional
	  public List<Proposals> getFinalBidUserParticipated(Person person) {
		  try {
	        	Query proposalsQuery = em.createQuery("Select p from Proposals p where p.isActive = :isActive and "
	        			+ "p.id in (Select a.proposals.id from Auction a where a.personPriceProposed.id = :personId)");
	        	proposalsQuery.setParameter("personId", person.getId());
	        	proposalsQuery.setParameter("isActive", false);
	        	       	
	       
	    		List<Proposals> proposalsList =  proposalsQuery.getResultList();
	    		
	    		return proposalsList;
	        }catch (Exception exception){
	        	System.out.println(exception.getMessage());
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
	    		long countAuctionOfProposal = getCountAuctionOfProposal(activeProposalsList.get(i));
	    
	    		// replace the commanded line after finishing project!
	    		Timestamp updatedTime = activeProposalsList.get(i).getUpdateDate();
	    		//if ( (currentTime.getTime() - updatedTime.getTime()) > 5*60*1000
	    		System.out.println((currentTime.getTime()- updatedTime.getTime()));
	    		System.out.println((currentTime.getTime()- updatedTime.getTime())/(1000*60*5));
	    		System.out.println((currentTime.getTime()- updatedTime.getTime())/(1000*60));
	    		/*	    		System.out.println(System.currentTimeMillis()+1000*5*60);*/
	    		
	    		
	    		if (( (currentTime.getTime()- updatedTime.getTime())/(1000*60)) >= 5
	    				&& countAuctionOfProposal >0){
	    			activeProposalsList.get(i).setActive(false);
	    		}
	    		else if	(((currentTime.getTime()- updatedTime.getTime())/(1000*60))>= 5
    				&& countAuctionOfProposal ==0 ){
    			activeProposalsList.get(i).setUpdateDate(currentTime);
	    		}
	    		else if (((currentTime.getTime()- updatedTime.getTime())/(1000*60))< 5 ){
	    			//activeProposalsList.get(i).setUpdateDate(currentTime);
	    		}
	    		em.persist(activeProposalsList.get(i));
	    		i++;
	    	}
	    	
			
		}
	  
	  @Transactional
	  private long getCountAuctionOfProposal(Proposals proposals) {
		  Query countAuctionQuery = em.createQuery("Select count(a) from Auction a where a.proposals.id =:propid");
		  countAuctionQuery.setParameter("propid", proposals.getId());
		  
		return (Long) countAuctionQuery.getSingleResult();
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
		 
		 Auction prop = null;
		  Query selectMaxAuctionQuery = em.createQuery("Select a from Auction a where "
		  		+ " a.proposals.id =:propid ORDER BY a.proposedPrice desc");
		  
		  //selectMaxAuctionQuery.setParameter("isActive", true);
		  selectMaxAuctionQuery.setParameter("propid", propid);	
		  selectMaxAuctionQuery.setMaxResults(1);
		  if ( selectMaxAuctionQuery.getResultList().size() > 0  ){
			  
			  prop = (Auction) selectMaxAuctionQuery.getResultList().get(0);
		  }
		  
		 
		return prop;
	}
	 
	 @Transactional
	public boolean addNewAuction(Auction auction) {
		boolean result = false;
		try{
			em.persist(auction);
			result = true;
		}
		catch(Exception ex){
			ex.getMessage();
		}
		
		return result ;
	}

	 @Transactional
	public List<Auction> getAuctions(int propid) {
		Query selectAuctionList = em.createQuery("Select a from Auction a where "
		  		+ " a.proposals.id =:propid ORDER BY a.proposedPrice desc");
		  
		  //selectMaxAuctionQuery.setParameter("isActive", true);
		  selectAuctionList.setParameter("propid", propid);	
		  List<Auction> auctions = selectAuctionList.getResultList();
			
		return auctions;
	}
	 
	 @Transactional
	 public Proposals findProposal(int propid){
		 
		 Proposals prop = em.find(Proposals.class,propid);
		 
		 return prop;
	 }

	@Transactional
	public List<Auction> findUserAuctions(int userId) {
		Query selectAuctionList = em.createQuery("Select a from Auction a where "
		  		+ " a.personPriceProposed.id = :userId ORDER BY a.proposedPrice desc");
		  
		List<Auction> auctions = new ArrayList<Auction>();
		try{
			//selectMaxAuctionQuery.setParameter("isActive", true);
			  selectAuctionList.setParameter("userId", userId);	
			  auctions = selectAuctionList.getResultList();  
		  }
		  catch(Exception ex){
			  System.out.println(ex.getMessage());
		  }
		  
		  
		return auctions;
	}
}
