package controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import model.bl.GeneralLogic;
import model.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.PersonService;

@Controller
//@RequestMapping("/")
@Scope("session")
public class LoginController {

	 
	  @Autowired private PersonService personSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	 
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	   public String login(Model model) {
		  model.addAttribute("loginForm", new LoginForm());
		  
	      return ("/page/login");
	   }
	  
	  @RequestMapping(value = "/options", method = RequestMethod.GET)
	   public String goOptionsPage(Model model,HttpServletRequest request) {
		 // model.addAttribute("loginForm", new LoginForm());
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				  GeneralLogic gl = new GeneralLogic();
		    	  return gl.isLoginWorker(person);
				  
			  }
			  else
			  {
				  return ("redirect:/login");
			  }

		  } catch(Exception ex){
			  return ("redirect:/login");
		  }
		}
	  
	  @RequestMapping(value = "/login", method = RequestMethod.POST)
	   public String login(@ModelAttribute LoginForm loginForm,HttpServletRequest request,ModelMap model) {
		  model.addAttribute("loginForm", loginForm);	      
	      
		  Person p = new Person();
			if ( loginForm.getUsername().equals("") || loginForm.getPassword().equals("")){
				return "redirect:/login";
			}
			else
			{
				 p =personSvc.findPersonByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
			}
			if ( p == null || p.getUsername() == null ||p.getUsername().equals(""))
			{
		    	  return "redirect:/login";
		      }
		      else
		      {
		    	  request.getSession().setAttribute("person",p);
		    	  GeneralLogic gl = new GeneralLogic();
		    	  return gl.isLoginWorker(p);
		      }		 	    
	   }
		   	  
	  @RequestMapping(value = "/logout", method = RequestMethod.GET)
	   public String logout(Model model,HttpServletRequest request) {
		 request.getSession().setAttribute("person", null);
		  
	      return ("redirect:/");
	   }
	  
	  @RequestMapping( method = RequestMethod.GET)
	   public ModelAndView person() {
	      return new ModelAndView("index", "command", new Person());
	   }
}
	
