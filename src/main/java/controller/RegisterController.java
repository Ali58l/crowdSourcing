package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import model.bl.GeneralLogic;
import model.dao.LoginForm;
import model.dao.Person;

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

import service.PersonService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	 
	 @Autowired private PersonService personSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	  @RequestMapping( method = RequestMethod.GET)
	   public String Bid(Model model) {
		  model.addAttribute("person", new Person());
		  
	      return ("/page/register");
	   }
	  
	  @RequestMapping(value = "/add", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute ("person")	Person person,
			   @ModelAttribute ("loginForm") LoginForm loginForm
			   , Model model ,BindingResult result, SessionStatus status) {
		  
		  boolean userNameExistance = personSvc.checkUsernameAvailable(person.getUsername());
	      if (userNameExistance ){		  
	    	  model.addAttribute("error", "Username is availabe try another one please!");
	    	  
	    	  return ("/page/registerError");
	      }
	      else{
	    	  
	    	  person.setActive(true);
		      Timestamp ts = GeneralLogic.addTimeStamo();
		      person.setUpdateDate(ts);
		      person.setCreationDate(ts);
	    	  personSvc.add(person);
		      
	    	  GeneralLogic gLogic = new GeneralLogic();
	  	    //  gLogic.sendEmail("Auction Registration", "Welcome To Big Auction/Bid"); 
	  	      model.addAttribute("loginForm", loginForm);
	  	      //return "redirect:/";
	  	      return "/page/login";
	      }
	   }
	  
	  @RequestMapping(value = "/unregister")
	   public String unregister( Model model ,HttpServletRequest request, SessionStatus status) {
		  
		  Person person = (Person) request.getSession().getAttribute("person") ;
		  boolean hasActiveProposal = personSvc.checkUserProposals(person);
		  boolean hasActiveAuction = personSvc.checkUserAuction(person);
	      if ( !hasActiveProposal && !hasActiveAuction ){		  
	    	  
	    	  personSvc.deleteUser(person);
	    	  request.getSession().setAttribute("person", null);
	    	  
	    	  return ("redirect:/");
	      }
	      else{
	    	  model.addAttribute("error", "Currently, you are not eligible for Unregister!");
 
	    	  return ("/page/error");
	      }
	   }

	  

}
	
