package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.bl.GeneralLogic;
import model.dao.Category;
import model.dao.LoginForm;
import model.dao.Person;
import model.dao.Proposals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.BidService;
import service.PersonService;

@Controller
@Scope("session")
@RequestMapping(value = "/bid")
public class MakeBidController {

	 
	 @Autowired private BidService bidSvc;
	 @Autowired private PersonService personSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	 
	 @RequestMapping(method = RequestMethod.GET)
	   public String login(Model model,HttpServletRequest request) {
		  model.addAttribute("bid", new Proposals());
		  initModelList(model);
		  Person person = new Person();
		  person = (Person) request.getSession().getAttribute("person") ;
		  if (person != null || !person.getUsername().equals("")){
			  return ("/page/bid");
		  }else{
			  return ("redirect:/login");
		  }
	     
	   }

//	  @RequestMapping( method = RequestMethod.GET)
//	   public ModelAndView Bid() {
//		  
//		  
//	      return new ModelAndView("/page/bid", "command", new Proposals());
//	   }
	  
	  
	  @RequestMapping(value = "/add", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute Proposals proposal,HttpServletRequest request ,ModelMap model) {
//	      model.addAttribute("Category", proposal.getCategory());
//	      model.addAttribute("Base Price",proposal.getBasedProposedPrice());
//	      model.addAttribute("Item Name", proposal.getDescription());
//	      model.addAttribute("Description", proposal.getDescription());
	      
		 Person person = (Person) request.getSession().getAttribute("person") ;
		  if (person != null || !person.getUsername().equals("")){
		      proposal.setActive(true);
		      Timestamp ts = GeneralLogic.addTimeStamo();
		      proposal.setUpdateDate(ts);
		      proposal.setCreationDate(ts);
		      proposal.setPerson(person);
		    
		      bidSvc.add(proposal);
		      GeneralLogic gLogic = new GeneralLogic();
		      
		      return "redirect:/bid";

		  }else{
			  request.getSession().setAttribute("person",null);
			  return ("redirect:/login");
		  }
		 	      
	   }
	  
	  private void initModelList(Model model) {
		  
		 Category cat = new Category();
//		List<String> catList = new ArrayList<String>();  
//        int i = 0 ;
//		while ( i < catList.size() ){
//			categories.put(catList.get(i), catList.get(i));
//			i++;
//		}
		 
		 List<String> catList = new ArrayList<String>();
		 model.addAttribute("categoryList", cat.returnCategories());
    }
	  
}
	
