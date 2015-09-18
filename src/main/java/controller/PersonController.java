package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import controller.PersonService;

@Controller
@RequestMapping("/")
public class PersonController {

	 
	  @Autowired private PersonService personSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	  @RequestMapping(method = RequestMethod.GET)
	  public String listAll(Model model) {
	    model.addAttribute("persons", personSvc.getAll());
	    return "home";
	  }
	   
	  /**
	   * POST requests to http://localhost:8080/hello/addPerson goes here.
	   * The new person data is passed from HTML from and bound into the
	   * Person object.
	   */
	  @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
	  public String addPerson(@ModelAttribute Person person) {
	    personSvc.add(person);
	    return "redirect:/";
	  }
}
	
