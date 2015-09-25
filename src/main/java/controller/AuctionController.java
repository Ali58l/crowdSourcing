package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.bl.GeneralLogic;
import model.dao.LoginForm;
import model.dao.Person;
import model.dao.Proposals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import service.BidService;
import service.PersonService;

@Controller
@RequestMapping("/auction")
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
		  person = (Person) request.getSession().getAttribute("person") ;
		  if (person != null || !person.getUsername().equals("")){
			  
			  List<Proposals> proposalsList= bidSvc.getMyBid(person);
			  
			  model.addAttribute("proposalsList",proposalsList);
			  
			  return ("/page/myproposals");
		  }else{
			  return ("redirect:/login");
		  }
	   }
	  
	  @RequestMapping( value="activeProposal",method = RequestMethod.GET)
	   public String getActiveProposals(Model model,HttpServletRequest request) {
		  Person person = new Person();
		  List<Proposals> proposalsList = new ArrayList<Proposals>();
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
	  
}
	
