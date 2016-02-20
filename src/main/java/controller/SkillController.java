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
import model.dao.Skills;

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

import service.PersonService;
import service.SkillService;

@Controller
@Scope("session")
public class SkillController {

	 
	 @Autowired private PersonService personSvc;
	 @Autowired private SkillService skillSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	  @RequestMapping( value="/showSkill",method = RequestMethod.GET)
	   public String getMyProposals(Model model,HttpServletRequest request) {
		  Person person = new Person();
		  person = (Person) request.getSession().getAttribute("person") ;
		  	
		  try
		  {
			  GeneralLogic gl = new GeneralLogic();
			  String page = "";
			  boolean isValid = gl.sessionValidationWorker(person);
			  
			  if (isValid)
			  {
				  Skills skills = new Skills();
				  model.addAttribute("skillForm", skills);
				  page = "/page/addSkill";
			  }
			  
			  else
			  {
				  page = "redirect:/login";
			  }
			  
			  
			  return page;
		  }
		  catch(Exception ex)
		  {
			  return ("redirect:/login");
		  }
	  }
	  
	  @RequestMapping(value = "/addSkill", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute ("skillForm") Skills skills,
			   Model model ,HttpServletRequest request,BindingResult result, SessionStatus status) {
		  
		try
		{	
		  Person person1 = (Person) request.getSession().getAttribute("person") ;
		
		long numSkillAlreadyAvailableForWorker =  skillSvc
				.checkAvailabilityOfSkillForUser(person1 , skills.getSkill());
		 GeneralLogic gl = new GeneralLogic();
		boolean isSkillAlreadyAvailable = gl.checkIfSkillAlreadyAvailable( numSkillAlreadyAvailableForWorker);
				
		if ( isSkillAlreadyAvailable )
		{
			model.addAttribute("info", " You already register this skill!");
	    	  
	    	 return "/page/userinfo";	
		}
		else if( skills.getSkill().equals("") )
		{
				model.addAttribute("info", " Skill must not be empty!");
		    	  
		    	 return "/page/userinfo";	
		}
		else
		{
			skills.setActive(true);
		    skills.setPerson(person1);
		    skills.setCredibility(0.5);
			skillSvc.addSkill(skills);
			
			return ("redirect:/showSkill");
		}
		    	   
		    	  
	   }
		catch(Exception ex)
		{
		   return "/page/login";
	   }
	  }	
}
