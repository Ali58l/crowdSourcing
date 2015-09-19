package controller;

import model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.PersonService;

@Controller
@RequestMapping("/")
public class LoginController {

	 
	  @Autowired private PersonService personSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
//	  @RequestMapping(method = RequestMethod.GET)
//	  public String listAll(Model model) {
//	    model.addAttribute("persons", personSvc.getAll());
//	    return "Register";
//	  }
	 
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	   public ModelAndView login() {
	      return new ModelAndView("/page/login");
	   }
	  
	  @RequestMapping( method = RequestMethod.GET)
	   public ModelAndView student() {
	      return new ModelAndView("index", "command", new Person());
	   }
		   
//	  /**
//	   * POST requests to http://localhost:8080/hello/addPerson goes here.
//	   * The new person data is passed from HTML from and bound into the
//	   * Person object.
//	   */
//	  @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
//	  public String addPerson(@ModelAttribute Person person) {
//	    personSvc.add(person);
//	    return "redirect:/";
//	  }
	  
	  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute Person person, ModelMap model) {
	      model.addAttribute("name", person.getName());
	      model.addAttribute("username",person.getUsername());
	      model.addAttribute("password", person.getPassword());
	  
	      
	      personSvc.add(person);
	      
	      return "redirect:/";
	   }

}
	
