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
import model.dao.TaskWorker;
import model.dao.Tasks;

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

import service.SkillService;
import service.TaskService;
import service.PersonService;

@Controller
@RequestMapping("/task")
@Scope("session")
public class TaskController {

	 
	 @Autowired private PersonService personSvc;
	 @Autowired private TaskService taskSvc;
	 @Autowired private SkillService skillSvc;
	  /**
	   * Requests to http://localhost:8080/hello will be mapped here.
	   * Everytime invoked, we pass list of all persons to view
	   */
	  @RequestMapping( value="/addTask",method = RequestMethod.GET)
	   public String getMyProposals(Model model,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				   Tasks task = new Tasks();
				  model.addAttribute("addTask",task);
				  
				  return ("/page/addTask");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex){
			  
		  }
		  
		  return ("redirect:/login");	
	   } 
	  
	  @RequestMapping(value = "/registerTask", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute ("addTask") Tasks task,
			   Model model ,HttpServletRequest request,BindingResult result, SessionStatus status) {
		  
		try
		{	
		  Person person1 = (Person) request.getSession().getAttribute("person") ;
		  if (person1 != null || !person1.getUsername().equals(""))
		  {
			  Timestamp ts = GeneralLogic.addTimeStamo();
			  	  task.setActive(true);
		    	  task.setPerson(person1);
				  task.setCreationDate(ts);
				  task.setUpdateDate(ts);
		    	  taskSvc.addTask(task);
		    	  
		    	  return ("/page/options");  
		  }
		  else
		  {
			  model.addAttribute("error", "Username is availabe try another one please!");
	    	  
	    	  return ("/page/registerError");
		  }
		}
		catch(Exception ex)
		{
		   return "/page/login";
	   }
	  }
	  
	  @RequestMapping( value="/showUserActiveTasks",method = RequestMethod.GET)
	   public String showUserActiveTasks(Model model,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals("")){
				  
				  List<Tasks> tasks = taskSvc.getUserActiveTask(person.getId());
				  model.addAttribute("tasks",tasks);
				  
				  return ("/page/userActiveTasksList");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex){
			  
		  }
		  
		  return ("redirect:/login");
	   }
	  
	  @RequestMapping( value="/showPotentialWorkersList/{taskid}")
	   public String showProposalDetail(Model model,@PathVariable("taskid") int taskid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				  
				 request.getSession().setAttribute("selectedtaskId",taskid) ;
				 Tasks task = taskSvc.getTaskById(taskid);
				 
				 // check if user has already selected and accepted current task workers
				 long numberOfAcceptedWorkers = taskSvc.getNumberOfAcceptedWorkers(task);
				 
				 if ( numberOfAcceptedWorkers >=  task.getMaxWorker() )
				 {
					  model.addAttribute("info", " You already hired the number of necessaray workers for this task!");
			    	  
			    	  return "/page/userinfo";
				 }
				 else
				 {
					 List<Skills> skillsList = skillSvc.getPotentialWorkersForOpenTask(task);
						
					 model.addAttribute("skillWorkers",skillsList);
						
					  return ("/page/showPotentialWorkersForTask");
				 }
				 
				 
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }
	  
	  @RequestMapping( value="/sendRequestToUser/{skillid}")
	   public String sendRequestToUser(Model model,@PathVariable("skillid") int skillid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				  int taskid = (Integer) request.getSession().getAttribute("selectedtaskId") ;
				  
				 Skills skill = skillSvc.findSkillById(skillid);
				 Tasks task = taskSvc.getTaskById(taskid);
				 
				 Timestamp ts = GeneralLogic.addTimeStamo();
				 TaskWorker tw = new TaskWorker();
				 tw.setActive(true);
				 tw.setPerson(skill.getPerson());
				 tw.setAllocationStatus(0);
				 tw.setAppreciatin(0);
				 tw.setTask(task);
				 tw.setCreationDate(ts);
				 tw.setUpdateDate(ts);
				 
				 taskSvc.addNewTaskWorker(tw);
				 
				 
				  return ("redirect:/task/showUserActiveTasks");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }
	  
	  @RequestMapping( value="/seeRequestedTaskForDecision",method = RequestMethod.GET)
	   public String seeRequestedTaskForDecision(Model model,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				  
				 List<TaskWorker> tasks = taskSvc.showUserTasksForDecision(person.getId());
				 
				  model.addAttribute("tasks",tasks);
				 
				  return ("/page/showWorkerTaskForDecision");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }

	  @RequestMapping( value="/taskDetailForDecision/{taskWorkerid}")
	   public String showTaskDetailForDecision(Model model,@PathVariable("taskWorkerid") int taskworkerid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				//  int taskid = (Integer) request.getSession().getAttribute("selectedtaskId") ;
				  
				// Skills skill = skillSvc.findSkillById(skillid);
				 TaskWorker taskworker = taskSvc.getTaskWorkerById(taskworkerid);
				 
				 model.addAttribute("taskworker",taskworker);
				 
				  return ("/page/showWorkerDetailTaskForDecision");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }
	  
	  @RequestMapping( value="/acceptTask/{taskWorkerid}")
	   public String acceptTask(Model model,@PathVariable("taskWorkerid") int taskworkerid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				//  int taskid = (Integer) request.getSession().getAttribute("selectedtaskId") ;
				  
				// Skills skill = skillSvc.findSkillById(skillid);
				 TaskWorker taskworker = taskSvc.getTaskWorkerById(taskworkerid);
				 
				 taskSvc.accepttaskWork(taskworker);
				 
				  return ("/page/optionsWorker");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }
	  
	  @RequestMapping( value="/rejectTask/{taskWorkerid}")
	   public String rejectTask(Model model,@PathVariable("taskWorkerid") int taskworkerid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  if (person != null || !person.getUsername().equals(""))
			  {
				//  int taskid = (Integer) request.getSession().getAttribute("selectedtaskId") ;
				  
				// Skills skill = skillSvc.findSkillById(skillid);
				 TaskWorker taskworker = taskSvc.getTaskWorkerById(taskworkerid);
				 
				 taskSvc.rejecttaskWork(taskworker);
				 
				  return ("/page/optionsWorker");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }catch(Exception ex){
			  return ("redirect:/login");
		  } 
	   }
	  
	}
	
