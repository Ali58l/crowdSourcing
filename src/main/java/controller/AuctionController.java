package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.bl.GeneralLogic;
import model.dao.Auction;
import model.dao.LoginForm;
import model.dao.Person;
import model.dao.Proposals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import service.BidService;
import service.PersonService;

@Controller
@RequestMapping("/auction")
@Scope("session")
public class AuctionController {

	 
	 @Autowired private PersonService personSvc;
	 @Autowired private BidService bidSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	  @RequestMapping( value="myProposal",method = RequestMethod.GET)
	   public String getMyProposals(Model model,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				    bidSvc.checkProposalsStatus();  //show  if based on Time/number of auction is active or disactive
				
				  List<Proposals> proposalsList= bidSvc.getMyBid(person);
				//Find winner if it is inactive(first max)
				  
				  //Winner is null if it is active + just show maximum
				  
				  model.addAttribute("proposalsList",proposalsList);
				  
				  return ("/page/myproposals");
			  }else{
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex){
			  
		  }
		  
		  return ("redirect:/login");
	   }
	  
	  @RequestMapping( value="activeProposal",method = RequestMethod.GET)
	   public String getActiveProposals(Model model,HttpServletRequest request) {
		  Person person = new Person();
		  List<Proposals> proposalsList = new ArrayList<Proposals>();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
					 bidSvc.checkProposalsStatus();
					 proposalsList= bidSvc.getActiveBid(person);
					  
					  model.addAttribute("proposalsList",proposalsList);
					  
					  return ("/page/activeProposals");
				  }else{
					  return ("redirect:/login");
				  }
		  }
		  catch(Exception ex){
			  return ("redirect:/login");
		  }
	   }
	  
	  @RequestMapping( value="proposalDetails/{propid}")
	   public String showProposalDetail(Model model,@PathVariable("propid") int propid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				 bidSvc.checkProposalsStatus();
				// proposalsList= bidSvc.getActiveBid(person);
				 Auction maxPriceProposal = new Auction();
				 maxPriceProposal = bidSvc.getMaxProposedPrice(propid);
				
				
				  request.getSession().setAttribute("oldProposal",maxPriceProposal );
				  request.getSession().setAttribute("propId",propid );
				  model.addAttribute("maxPrice",maxPriceProposal);
				  model.addAttribute("newAuction",new Auction());
				 
				
				  return ("/page/proposalDetails");
			  }else{
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  }
		 
	   }
	  
	  @RequestMapping( value="myProposalDetails/{propid}")
	   public String showMyProposalDetail(Model model,@PathVariable("propid") int propid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				// bidSvc.checkProposalsStatus();
				// proposalsList= bidSvc.getActiveBid(person);
				 List<Auction> getAllAuctionOfThisProposal = bidSvc.getAuctions(propid);
				
				//  request.getSession().setAttribute("oldProposal",maxPriceProposal );
				  model.addAttribute("auctions",getAllAuctionOfThisProposal);
				  //model.addAttribute("newAuction",new Auction());
				 
				
				  return ("/page/myProposalDetails");
			  }else{
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  }
		  
	   }
	  
	  @RequestMapping( value="submitNewAuction")
	   public String addNewAuction(Model model, @ModelAttribute("newAuction")Auction newAuction,
			   HttpServletRequest request
			   ,BindingResult result) {
		  Person person = new Person();
		  List<Proposals> proposalsList = new ArrayList<Proposals>();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				  Timestamp ts = GeneralLogic.addTimeStamo();
				  Auction auction = new Auction();
				  auction.setActive(true);
				  auction.setCreationDate(ts);
				  auction.setUpdateDate(ts);
				  auction.setPersonPriceProposed(person);
				  auction.setProposedPrice(newAuction.getProposedPrice());
				//  Auction oldAuction = (Auction)request.getSession().getAttribute("oldProposal");
				 int  propid = (Integer) request.getSession().getAttribute("propId");
				  //auction.setProposals(oldAuction.getProposals());
				  auction.setProposals(bidSvc.findProposal(propid));
				  boolean isRegister = bidSvc.addNewAuction(auction);
			
				  return ("redirect:/options");
			  }else{
				  return ("redirect:/login");
			  }
		  } catch  (Exception ex){
			  System.out.println(ex.getMessage());
			  return ("redirect:/login");
		  }
		 
	   }
	  
}
	
