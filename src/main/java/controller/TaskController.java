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
	   public String addTask(Model model,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			  
			  if (isValid)
			  {
				   Tasks task = new Tasks();
				   // check is user already assign credibility of his perviuos added tasks
				  // GeneralLogic gl = new GeneralLogic();
					long numCredebilityareAssiged = taskSvc.checkCredibilityAssignmnedOfUser(person);
	
				   boolean isCredebilityareAssiged = gl.checkCredibilityAssignedOfUser(numCredebilityareAssiged);
				   
				   if ( isCredebilityareAssiged )
				   {
					   model.addAttribute("addTask",task);
						  
					  return ("/page/addTask");
	
				   }
				   else
				   {
					   model.addAttribute("info", " First, you must assign credeibility of workers "
					   		+ "assigned to your previous tasks!");
				    	  
				    	  return "/page/userinfo";
				   }
			 }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());
		  }
		  
		  return ("redirect:/login");	
	   } 
	  
	  @RequestMapping(value = "/registerTask", method = RequestMethod.POST)
	   public String addUser(@ModelAttribute ("addTask") Tasks task,
			   Model model ,HttpServletRequest request,BindingResult result, SessionStatus status) {
		  
		try
		{	
		  Person person1 = (Person) request.getSession().getAttribute("person") ;
		 
		  GeneralLogic gl = new GeneralLogic();
		  boolean isValid = gl.sessionValidationUser(person1);
		  
		  if (isValid)
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
				  
				  return ("/page/showUserActiveTasks");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());  
		  }
		  
		  return ("redirect:/login");
	   }
	  
	  @RequestMapping( value="/showPotentialWorkersList/{taskid}")
	   public String showProposalDetail(Model model,@PathVariable("taskid") int taskid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			 
			  if (isValid)
			  {
				  
				 request.getSession().setAttribute("selectedtaskId",taskid) ;
				 Tasks task = taskSvc.getTaskById(taskid);
				 
				 // check if user has already selected and accepted current task workers
				 long numberOfAcceptedWorkers = taskSvc.getNumberOfAcceptedWorkers(task);
				 int maxNeededWorker = task.getMaxWorker();
				 
				 boolean iSMaxWorkerHiredForThisTask = 
						 gl.checkIfMaxWorkersAreHired(numberOfAcceptedWorkers,maxNeededWorker);
				 
				 if ( iSMaxWorkerHiredForThisTask )
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
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			  
			  if (isValid)
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
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationWorker(person);
			 
			  if (isValid)
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
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationWorker(person);
			 
			  if (isValid)
			  {
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
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationWorker(person);
			  
			  if (isValid)
			  {
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
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationWorker(person);
			  
			  if (isValid)
			  {
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
	
	  @RequestMapping( value="/showAllocatedWorkers/{taskid}")
	   public String showAllocatedWorkersList(Model model,@PathVariable("taskid") int taskid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		  try{
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			  
			  if (isValid)
			  {
				 List<TaskWorker> workersList = taskSvc.showUserActiveTasks(taskid);
				 
				 model.addAttribute("taskworker",workersList);
				 
				  return ("/page/userActiveTasksList");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex)
		  {
			  return ("redirect:/login");
		  } 
	   }
	
	  @RequestMapping( value="/assignWorkerCredibility/{twid}")
	   public String assignCredibility(Model model,@PathVariable("twid") int twid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			 
			  if (isValid)
			  {
				  
				   TaskWorker taskworker = taskSvc.getTaskWorkerById(twid);
				   request.getSession().setAttribute("mytaskworker",taskworker) ;
				   boolean IscredibilityAlreadyAssigned = 
						   gl.checkCredibilityAssignment(taskworker.getAppreciatin());
				   
				if ( IscredibilityAlreadyAssigned )
				{
					 model.addAttribute("info", " You already assign Credebility to this Worker for current Task!");
					 
					 return "/page/userinfo";
				}
				else
				{
					 model.addAttribute("taskworker",taskworker);
					  
					  return ("/page/assignCredibility");
				}
				 
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
	  
	  @RequestMapping( value="/finalAssignCredibility", method = RequestMethod.POST)
	   public String finalAssignCredibility(Model model,@ModelAttribute TaskWorker taskworker
			   ,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			  if (isValid)
			  {
				  
				  TaskWorker mainTaskWorker = (TaskWorker) request.getSession().getAttribute("mytaskworker") ;	
				  
				  mainTaskWorker.setAppreciatin(taskworker.getAppreciatin());
				   taskSvc.updateTaskWorker(mainTaskWorker);
				  
				  
				  
				   return ("/page/options");
			  }
			  else
			  {
				  return ("redirect:/login");
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());
		  }
		  
		  return ("redirect:/login");	
	   }
	  
	  @RequestMapping( value="/pay/{twid}")
	   public String pay(Model model,@PathVariable("twid") int twid
			   ,HttpServletRequest request) {
		  Person person = new Person();
		 
		  try
		  {
			  person = (Person) request.getSession().getAttribute("person") ;
			  GeneralLogic gl = new GeneralLogic();
			  boolean isValid = gl.sessionValidationUser(person);
			 
			  if (isValid)
			  {
				   TaskWorker taskworker = taskSvc.getTaskWorkerById(twid);
				   request.getSession().setAttribute("mytaskworker",taskworker) ;
				   boolean isPaymentAlreadyAssigned = 
						   gl.checkPayment(taskworker.getSalaryPaid());		   
				    
				if ( isPaymentAlreadyAssigned )
				{
					 model.addAttribute("info", " You already Paid to this Worker for current Task!");
					
					 return "/page/userinfo";
				}
				else
				{
					
					 taskSvc.paySalary(twid);
					 model.addAttribute("taskworker",taskworker);
					  
					  return ("/page/paypal");
				}
				 
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
}
	
